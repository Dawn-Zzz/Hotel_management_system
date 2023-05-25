package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.StaffDAO;
import view.ChangeStaffView;
import view.AddGuestView;
import view.AdminView;

public class AdminController implements ActionListener, MouseListener {
	private AdminView adminView;
	private ChangeStaffView addStaffView;
	
	public AdminController(AdminView adminView) {
		super();
		this.adminView = adminView;
	}
	
	public ChangeStaffView getAddStaffView() {
		return addStaffView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Staff Infor")) {
			addStaffView = new ChangeStaffView();
			addStaffView.setVisible(true);
		}
		else {
			searchEvent(adminView.getStaffTable(),adminView.getSearchBox());
		}
    }

	private void searchEvent(JTable table, JTextField textField) {

		String role = "";
		if (adminView.getStaffList().getSelectedItem() != null) {
			if (adminView.getStaffList().getSelectedItem() == "All")
				role = "";
			else 
				role = (String) adminView.getStaffList().getSelectedItem();
				
		}
		List<RowFilter<Object, Object>> filters = new ArrayList<>();

//		// Tạo một điều kiện lọc khởi đầu (với vai trò là rỗng)
//		RowFilter<Object, Object> roleFilter = RowFilter.regexFilter("(?i)");
//
//		// Thêm điều kiện lọc khởi đầu vào danh sách
//		filters.add(roleFilter);
		
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
//        List<RowFilter<Object,Object>> filters = new ArrayList<>();

        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + role,4);
		filters.add(filter1);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
				filters.clear();
				RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + input,0,1,2);
				filters.add(filter1);
				filters.add(filter2);
				sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int a = 0;
		if (SwingUtilities.isRightMouseButton(e)) { // kiểm tra chuột phải
		      int row = adminView.getStaffTable().rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
		      adminView.getStaffTable().setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột
		      JPopupMenu popupMenu = new JPopupMenu();
		      JMenuItem menuItem = new JMenuItem("Chỉnh sửa thông tin");
		      String selectedRole = adminView.getStaffTable().getValueAt(row, 4).toString();
		      String selectedName = adminView.getStaffTable().getValueAt(row, 0).toString();
		      menuItem.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
			    	 int a;
		        	 ChangeStaffView changeStaffView = new ChangeStaffView();
		        	 changeStaffView.getStaffNameBox().setSelectedItem(selectedName);;
		        	 changeStaffView.getStaffNameBox().setFocusable(false);
		        	 changeStaffView.getStaffNameBox().setEnabled(false);
		        	 changeStaffView.getIdentificationNumberField().setText((String) adminView.getStaffTable().getValueAt(row, 1));
		        	 changeStaffView.getIdentificationNumberField().setEnabled(false);
		        	 changeStaffView.getStaffRole().setSelectedItem(selectedRole);
		        	 if(row == 0) {
			        	 changeStaffView.getStaffRole().addItem("Giám đốc");
			        	 changeStaffView.getStaffRole().setSelectedItem("Giám đốc");
		        	 }
		        	 if(selectedRole.equals("Giám đốc")) {
		        		 changeStaffView.getStaffRole().setEnabled(false);
		        	 }
		        	 changeStaffView.getStaffPhoneField().setText((String) adminView.getStaffTable().getValueAt(row, 2));
		        	 changeStaffView.getBirthDay().setDate((Date) adminView.getStaffTable().getValueAt(row, 3));
		        	 changeStaffView.setVisible(true);
		         }
		      });
		      popupMenu.add(menuItem);
		      popupMenu.show(adminView.getStaffTable(), e.getX(), e.getY());
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