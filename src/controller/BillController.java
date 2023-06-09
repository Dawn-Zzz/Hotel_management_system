package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.BillDAO;
import DAO.OrderRoomDAO;
import DAO.OrderServiceDAO;
import ultils.Bill;
import view.BillDetailView;
import view.BillView;

public class BillController implements ActionListener, MouseListener{
	private BillView billView;
	private BillDetailView billDetailView;
	private Calendar calendar;

	public BillController(BillView billView) {
		super();
		this.billView = billView;
		calendar = Calendar.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == billView.getYearList()) {
			if (billView.getYearList().getSelectedItem() !="All") {
				billView.getMonthList().setSelectedIndex(-1);
				billView.getDayList().setSelectedIndex(-1);
				billView.getMonthList().setEnabled(true);
				billView.getDayList().setEnabled(false);
			} 
			else {
				billView.getMonthList().setSelectedIndex(-1);
				billView.getDayList().setSelectedIndex(-1);
				billView.getMonthList().setEnabled(false);
				billView.getDayList().setEnabled(false);
			}
		} 
		else if (e.getSource() == billView.getMonthList() && billView.getMonthList().getSelectedIndex() != -1) {
			updateDayComboBox();
			billView.getDayList().setSelectedIndex(-1);
	        billView.getDayList().setEnabled(true);
		}
		searchEvent(billView.getBillTable(), billView.getSearchBox());
    }
	
	private void updateDayComboBox() {
        int year = (Integer) billView.getYearList().getSelectedItem();
        int month = (Integer) billView.getMonthList().getSelectedItem();
        
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        int maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        billView.getDayList().removeAllItems();

        for (int day = 1; day <= maxDaysInMonth; day++) {
        	billView.getDayList().addItem(day);
        }
    }
	
	private void searchEvent(JTable table, JTextField textField) {
		String time = "";
		Integer year;
		if (billView.getYearList().getSelectedItem() != null && billView.getYearList().getSelectedItem()!="All") {
			year = (Integer) billView.getYearList().getSelectedItem();
			time = time + year.toString();
		}
		Integer month;
		if (billView.getMonthList().getSelectedItem() != null) {
			month = (Integer) billView.getMonthList().getSelectedItem();
			if (month < 10)
				time = time + "-0" + month.toString();
			else
				time = time + "-" + month.toString();
		}
		Integer day;
		if (billView.getDayList().getSelectedItem() != null) {
			day = (Integer) billView.getDayList().getSelectedItem();
			if (day < 10)
				time = time + "-0" + day.toString();
			else
				time = time + "-" + day.toString();
		}

		String nameStaff = "";
		if (billView.getStaffList().getSelectedItem() != null) {
			if (billView.getStaffList().getSelectedItem() == "All")
				nameStaff = "";
			else
				nameStaff = (String) billView.getStaffList().getSelectedItem();
		}

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowFilter<Object,Object>> filters = new ArrayList<>();

        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + nameStaff,4);
		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + time,2);
		filters.add(filter1);
		filters.add(filter2);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
	            filters.clear();
	            RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + input, 0, 1, 3);
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
		if (e.getClickCount() == 2) {
			// Lấy ra dòng được chọn trong JTable
            int row = billView.getBillTable().getSelectedRow();
            billDetailView = new BillDetailView();
            String id = (String) billView.getBillTable().getValueAt(row, 0);
            Bill bill = BillDAO.getInstance().selectBillByID(id);
            
            DecimalFormat df = new DecimalFormat("#,###");
            
            billDetailView.getBillID().setText("ID Bill: " + billView.getBillTable().getValueAt(row, 0));
            billDetailView.getGuestName().setText("Guest: " + bill.getNameGuest());
	        billDetailView.getDate().setText("Date: " + bill.getDateCreate());
	        billDetailView.getTotalMoney().setText("Total Money: " + df.format(bill.getTotal()));
	        billDetailView.getInvoicingStaff().setText("Invoicing Staff: " + bill.getNameStaff());
	        billDetailView.getTotalRoom().setText("Total: " + df.format(bill.getTotalRoom()));
	        billDetailView.getTotalService().setText("Total: " + df.format(bill.getTotalService()));
	        OrderServiceDAO.getInstance().selectOrderServiceByID(id, billDetailView.getServiceInforTable());
	        OrderRoomDAO.getInstance().selectOrderRoomByID(id, billDetailView.getRoomInforTable());
            billDetailView.setVisible(true);
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
