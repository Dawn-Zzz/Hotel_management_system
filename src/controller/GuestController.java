package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;

import view.AddGuestView;
import view.GuestView;

public class GuestController implements ActionListener {
	private GuestView guestView;
	private AddGuestView addGuestView =new AddGuestView();
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
		if(e.getActionCommand().equals("Add Guest")) {
			addGuestView.setVisible(true);
		}
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
}
