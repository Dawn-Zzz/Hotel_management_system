package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import view.AddGuestView;
import view.GuestView;

public class AddGuestController implements ActionListener {
	private AddGuestView addGuestView;
	private GuestView guestView = GuestView.getInstance();
	
	public AddGuestController(AddGuestView addGuestView) {
		super();
		this.addGuestView = addGuestView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		 TODO Auto-generated method stub
		this.addGuestView.addGuestAction();

	     ((DefaultTableModel) guestView.getGuestTable().getModel()).setRowCount(0);
		this.guestView.resetGuestTable();
	}		
}
