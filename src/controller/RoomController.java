package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.BillDAO;
import DAO.ReservationDAO;
import DAO.RoomDAO;
import view.AddServiceView;
import view.BookRoomView;
import view.RoomInfor;
import view.RoomView;

public class RoomController implements ActionListener, MouseListener {
	private RoomView roomView;
	private BookRoomView addRoomView;

	public RoomController(RoomView roomView) {
		super();
		this.roomView = roomView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Book Room")) {
			addRoomView = new BookRoomView();
			addRoomView.setVisible(true);
		} else if (e.getActionCommand().equals("Room History")) {
			roomView.getButtonPanel().setVisible(true);
			roomView.getHistoryRoomList().setVisible(true);
			roomView.getMainRoomList().setVisible(false);
			roomView.getRoomSearch().setVisible(true);
			roomView.getStatusSearch().setVisible(true);
			roomView.getSearchBox().setVisible(true);
		} else if (e.getActionCommand().equals("Back Button")) {
			roomView.getMainRoomList().removeAll();
			roomView.addRoom(roomView.getMainRoomList());
			roomView.getMainRoomList().setVisible(true);
			roomView.getButtonPanel().setVisible(false);
			roomView.getHistoryRoomList().setVisible(false);
			roomView.getRoomSearch().setVisible(false);
			roomView.getStatusSearch().setVisible(false);
			roomView.getSearchBox().setVisible(false);
		} else {
			for (int i = 0; i < roomView.getRoomButtonList().length; i++) {
				JButton button = roomView.getRoomButtonList()[i];
				if (e.getSource() == button) {
					RoomInfor roomInfor = new RoomInfor(roomView.getRoomList().get(i), null);
					roomInfor.setVisible(true);
					break;
				}
			}
		}
		SearchTable(roomView.getRoomTable(), roomView.getSearchBox());
	}

	private void SearchTable(JTable table, JTextField textField) {
		String rentalType = "";
		if (roomView.getRentalTypeList().getSelectedItem() != null) {
			if (roomView.getRentalTypeList().getSelectedItem() == "All")
				rentalType = "";
			else
				rentalType = (String) roomView.getRentalTypeList().getSelectedItem();
		}

		String currentStatus = "";
		if (roomView.getStatusList().getSelectedItem() != null) {
			if (roomView.getStatusList().getSelectedItem() == "All")
				currentStatus = "";
			else
				currentStatus = (String) roomView.getStatusList().getSelectedItem();
		}

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowFilter<Object, Object>> filters = new ArrayList<>();

		RowFilter<Object, Object> filter1 = RowFilter.regexFilter("(?i)" + rentalType, 3);
		RowFilter<Object, Object> filter2 = RowFilter.regexFilter("(?i)" + currentStatus, 7);
		filters.add(filter1);
		filters.add(filter2);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");

		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
				filters.clear();
				RowFilter<Object, Object> filter3 = RowFilter.regexFilter("(?i)" + input, 0, 1, 2, 4, 5);
				filters.add(filter1);
				filters.add(filter2);
				filters.add(filter3);
				sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isRightMouseButton(e)) { // kiểm tra chuột phải
			int row = roomView.getRoomTable().rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
			roomView.getRoomTable().setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột
			JPopupMenu popupMenu = new JPopupMenu();

//			JMenuItem menuItem = new JMenuItem("Chỉnh sửa thông tin");
			JMenuItem menuItem1 = new JMenuItem("Nhận phòng");
			JMenuItem menuItem2 = new JMenuItem("Trả phòng");
			JMenuItem menuItem3 = new JMenuItem("Huỷ phòng");
			JMenuItem menuItem4 = new JMenuItem("Thêm dịch vụ");
			
//			menuItem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					
//				}
//			});
			
			menuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String checkInTime = roomView.getRoomTable().getValueAt(row, 4).toString();

					// Chuyển đổi giờ check-in thành đối tượng Date
					SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date checkInDateTime = null;
					try {
						checkInDateTime = dateTimeFormat.parse(checkInTime);
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
					Date currentDateTime = new Date();
					if (checkInDateTime != null && checkInDateTime.after(currentDateTime)) {
			            // Hiển thị cảnh báo không cho phép nhận phòng
			            JOptionPane.showMessageDialog(null, "Không thể nhận phòng trước giờ checkin!");
			        } else if (!(RoomDAO.getInstance().getRoomByID(roomView.getRoomTable().getValueAt(row, 2).toString()).getCurrentStatus().equals("0"))) 
			        	JOptionPane.showMessageDialog(null, "Không thể nhận phòng vì phòng đang chưa sẵn sàng cho thuê!");
					else {
//			             Thực hiện cập nhật trạng thái nhận phòng
			            ReservationDAO.getInstance().updateStatusReservation(roomView.getRoomTable().getValueAt(row, 0).toString(), "Đã nhận phòng");
			            
			            ((DefaultTableModel) roomView.getRoomTable().getModel()).setRowCount(0);
			            ReservationDAO.getInstance().selectAll(roomView.getRoomTable());
			        }
				}
			});
			
			menuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Chuyển đổi giờ check-in thành đối tượng Date
			        // Thực hiện cập nhật trạng thái nhận phòng
			        ReservationDAO.getInstance().updateStatusReservation(roomView.getRoomTable().getValueAt(row, 0).toString(), "Đã trả phòng");
			        BillDAO.getInstance().updateIdStaff(BillDAO.getInstance().getMaHoaDonByMaPhieu(roomView.getRoomTable().getValueAt(row, 0).toString()));
			        ((DefaultTableModel) roomView.getRoomTable().getModel()).setRowCount(0);
			        ReservationDAO.getInstance().selectAll(roomView.getRoomTable());
				}
			});
			
			menuItem3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ReservationDAO.getInstance().updateStatusReservation(roomView.getRoomTable().getValueAt(row, 0).toString(), "Đã huỷ phòng");
		            ((DefaultTableModel) roomView.getRoomTable().getModel()).setRowCount(0);
		            ReservationDAO.getInstance().selectAll(roomView.getRoomTable());
				}
			});
			menuItem4.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
		        	 AddServiceView addServiceView = new AddServiceView((String) roomView.getRoomTable().getValueAt(row, 0));
		        	 addServiceView.getGuestName().setText("Guest Name: " + roomView.getRoomTable().getValueAt(row, 1));
		        	 addServiceView.getRoomNumber().setText("Room Number: " + roomView.getRoomTable().getValueAt(row, 2));
		        	 addServiceView.setVisible(true);
		         }
			});
			if (roomView.getRoomTable().getValueAt(row, 7).equals("Chưa nhận phòng")) {
//				popupMenu.add(menuItem);
				popupMenu.add(menuItem1);
				popupMenu.add(menuItem3);
			} else if (roomView.getRoomTable().getValueAt(row, 7).equals("Đã nhận phòng")) {
				popupMenu.add(menuItem2);
				popupMenu.add(menuItem4);
			}
			popupMenu.show(roomView.getRoomTable(), e.getX(), e.getY()); // hiển thị menu
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}