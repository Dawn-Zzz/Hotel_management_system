package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.GuestView;

public class GuestController implements ActionListener {
	private GuestView guestView;
	private Calendar calendar;
	public GuestController(GuestView guestView) {
		super();
		this.guestView = guestView;
		calendar = Calendar.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == guestView.getYearList()) {
			guestView.getMonthList().setSelectedIndex(-1);
			guestView.getDayList().setSelectedIndex(-1);
			guestView.getMonthList().setEnabled(true);
	        guestView.getDayList().setEnabled(false);
		} 
		else if (e.getSource() == guestView.getMonthList() && guestView.getMonthList().getSelectedIndex() != -1) {
			updateDayComboBox();
			guestView.getDayList().setSelectedIndex(-1);
	        guestView.getDayList().setEnabled(true);
		}
		searchEvent(guestView.getGuestTable(),guestView.getSearchBox());
    }
	
	private void updateDayComboBox() {
        int year = (Integer) guestView.getYearList().getSelectedItem();
        int month = (Integer) guestView.getMonthList().getSelectedItem();
        
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        int maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        guestView.getDayList().removeAllItems();

        for (int day = 1; day <= maxDaysInMonth; day++) {
        	guestView.getDayList().addItem(day);
        }
    }
	
	private void searchEvent(JTable table, JTextField textField) {
		String time = "";
		Integer year;
		if (guestView.getYearList().getSelectedItem() != null) {
			year = (Integer) guestView.getYearList().getSelectedItem();
			time = time + year.toString();
		}
		Integer month;
		if (guestView.getMonthList().getSelectedItem() != null) {
			month = (Integer) guestView.getMonthList().getSelectedItem();
			if (month < 10)
				time = time + "-0" + month.toString();
			else
				time = time + "-" + month.toString();
		}
		Integer day;
		if (guestView.getDayList().getSelectedItem() != null) {
			day = (Integer) guestView.getDayList().getSelectedItem();
			if (day < 10)
				time = time + "-0" + day.toString();
			else
				time = time + "-" + day.toString();
		}
		
		String type = "";
		if (guestView.getGuestList().getSelectedItem() != null) {
			if (guestView.getGuestList().getSelectedItem() == "All")
				type = "";
			else
				type = (String) guestView.getGuestList().getSelectedItem();
		}
		
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowFilter<Object,Object>> filters = new ArrayList<>();
        
        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + type,2);
		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + time,3);
		filters.add(filter1);
		filters.add(filter2);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
	            filters.clear();
	            RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + input, 0, 1, 4);
	            filters.add(filter1);
	            filters.add(filter2);
	            filters.add(filter3);
	            sorter.setRowFilter(RowFilter.andFilter(filters));
			}
      });
	}
}
