package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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

import DAO.EquipmentDAO;
import DAO.RoomDAO;
import view.EquipmentView;

public class EquipmentController implements ActionListener,MouseListener {
	private EquipmentView equipmentView;
//	private RoomView roomView = RoomView.getInstance();
	public EquipmentController(EquipmentView equipmentView) {
		super();
		this.equipmentView = equipmentView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		searchEvent(equipmentView.getEquipmentTable(), equipmentView.getSearchBox());
	}
	
	private void searchEvent(JTable table, JTextField textField) {
		String equipment = "";
		if (equipmentView.getEquipmentList().getSelectedItem() != "All") {
			equipment = equipment + equipmentView.getEquipmentList().getSelectedItem().toString();
		}
		String status = "";
		if (equipmentView.getStatusList().getSelectedItem() != "All") {
			status = status + equipmentView.getStatusList().getSelectedItem().toString();
		}
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowFilter<Object,Object>> filters = new ArrayList<>();

		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + equipment,1);
		RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + status,2);
		filters.add(filter2);
		filters.add(filter3);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
	            filters.clear();
	            RowFilter<Object,Object> filter4 = RowFilter.regexFilter("(?i)" + input);
	            //filters.add(filter1);
	            filters.add(filter2);
	            filters.add(filter3);
	            filters.add(filter4);
	            sorter.setRowFilter(RowFilter.andFilter(filters));
			}
      });
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isRightMouseButton(e)) { // kiểm tra chuột phải
		      int row = equipmentView.getEquipmentTable().rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
		      equipmentView.getEquipmentTable().setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột
		      String id =  (String) equipmentView.getEquipmentTable().getValueAt(row, 0);
		      String nameService =  (String) equipmentView.getEquipmentTable().getValueAt(row, 1);
		      JPopupMenu popupMenu = new JPopupMenu();
		      JMenuItem menuItem;
		      if (equipmentView.getEquipmentTable().getValueAt(row, 2).equals("Tốt"))
		    	  menuItem = new JMenuItem("Xác nhận thiết bị hỏng");
		      else
		    	  menuItem = new JMenuItem("Xác nhận thiết bị tốt");
		      menuItem.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if (equipmentView.getEquipmentTable().getValueAt(row, 2).equals("Tốt")) {
		        		 if (RoomDAO.getInstance().getRoomByID(id).getCurrentStatus().equals("1"))
		        			 JOptionPane.showMessageDialog(null, "Chỉ được chuyển thiết bị sang trạng thái 'hỏng' khi phòng đang trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        		 else { 
		        			 EquipmentDAO.getInstance().updateStatusEquipment(id, nameService, "Hỏng");
		        			 ((DefaultTableModel) equipmentView.getEquipmentTable().getModel()).setRowCount(0);
				        	 EquipmentDAO.getInstance().selectAll(equipmentView.getEquipmentTable());
		        		 }
		        	 }	
		        	 else {
		        		 EquipmentDAO.getInstance().updateStatusEquipment(id, nameService, "Tốt");
		        		 ((DefaultTableModel) equipmentView.getEquipmentTable().getModel()).setRowCount(0);
			        	 EquipmentDAO.getInstance().selectAll(equipmentView.getEquipmentTable());
		        	 }
		         }
		      });
		      popupMenu.add(menuItem);
		      popupMenu.show(equipmentView.getEquipmentTable(), e.getX(), e.getY()); // hiển thị menu
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