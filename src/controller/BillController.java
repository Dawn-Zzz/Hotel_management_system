package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import view.BillView;

public class BillController implements ActionListener{
	private BillView billView;
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
			billView.getMonthList().setSelectedIndex(-1);
			billView.getDayList().setSelectedIndex(-1);
			billView.getMonthList().setEnabled(true);
	        billView.getDayList().setEnabled(false);
		} 
		else if (e.getSource() == billView.getMonthList() && billView.getMonthList().getSelectedIndex() != -1) {
			updateDayComboBox();
			billView.getDayList().setSelectedIndex(-1);
	        billView.getDayList().setEnabled(true);
		}
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
}
