package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.w3c.dom.events.MouseEvent;

import view.AddGuestView;
import view.BookRoomView;
import view.RoomInfor;
import view.RoomView;

public class RoomController implements ActionListener, MouseListener {
	private RoomView roomView;
	private BookRoomView addRoomView;
	
	public RoomController(RoomView roomView){
		super();
		this.roomView = roomView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Book Room")) {
			addRoomView = new BookRoomView();
			addRoomView.setVisible(true);
		} 
		else if(e.getActionCommand().equals("Room History")) {
			roomView.getHistoryRoomList().setVisible(true);
			roomView.getMainRoomList().setVisible(false);
			roomView.getRoomSearch().setVisible(true);
			roomView.getStatusSearch().setVisible(true);
			roomView.getSearchBox().setVisible(true);
			roomView.setHistoryRoomList(roomView.getHistoryRoomList());
			roomView.setMainRoomList(roomView.getMainRoomList());
		}
		else if(e.getActionCommand().equals("Back Button")) {
			roomView.getHistoryRoomList().setVisible(false);
			roomView.getMainRoomList().setVisible(true);
			roomView.getRoomSearch().setVisible(false);
			roomView.getStatusSearch().setVisible(false);
			roomView.getSearchBox().setVisible(false);
			roomView.setHistoryRoomList(roomView.getHistoryRoomList());
			roomView.setMainRoomList(roomView.getMainRoomList());
		} 
		else {
			for(int i = 0; i < roomView.getRoomButtonList().length; i++) {
			    JButton button = roomView.getRoomButtonList()[i];
			    if (e.getSource() == button) {
			        String roomNumber = button.getText();
			        RoomInfor roomInfor;
			        if (roomView.getRoomList().get(i).getCurrentStatus().equals("0"))
			        	roomInfor = new RoomInfor(roomNumber, null, null, "Trống", null);
			        else if (roomView.getRoomList().get(i).getCurrentStatus().equals("1"))
			        	roomInfor = new RoomInfor(roomNumber, null, null, "Đã cho thuê", null);
			        else
			        	roomInfor = new RoomInfor(roomNumber, null, null, "Phòng không được phép sử dụng", null);
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
		List<RowFilter<Object,Object>> filters = new ArrayList<>();

        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + rentalType,2);
		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + currentStatus,6);
		filters.add(filter1);
		filters.add(filter2);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
				filters.clear();
	            RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + input, 0, 1,3,4);
	            filters.add(filter1);
	            filters.add(filter2);
	            filters.add(filter3);
	            sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isRightMouseButton(e)) { // kiểm tra chuột phải
		      int row = roomView.getRoomTable().rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
		      roomView.getRoomTable().setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột
		      JPopupMenu popupMenu = new JPopupMenu();
		      
		      JMenuItem menuItem = new JMenuItem("Chỉnh sửa thông tin");
		      JMenuItem menuItem1 = new JMenuItem("Nhận phòng");
		      JMenuItem menuItem2 = new JMenuItem("Trả phòng");
		      JMenuItem menuItem3 = new JMenuItem("Huỷ phòng");
		      
		      menuItem.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	
		         }
		      });
				if (roomView.getRoomTable().getValueAt(row, 6).equals("Chưa nhận phòng")) {
					popupMenu.add(menuItem);
					popupMenu.add(menuItem1);
					popupMenu.add(menuItem3);
				} else if (roomView.getRoomTable().getValueAt(row, 6).equals("Đã nhận phòng")) {
					popupMenu.add(menuItem2);
				}
		      popupMenu.show(roomView.getRoomTable(), e.getX(), e.getY()); // hiển thị menu
		   }
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
