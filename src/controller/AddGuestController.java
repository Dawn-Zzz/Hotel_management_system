package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddGuestView;

public class AddGuestController implements ActionListener {
	private AddGuestView addGuestView;
	
	public AddGuestController(AddGuestView addGuestView) {
		super();
		this.addGuestView = addGuestView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.addGuestView.addGuestAction();
		this.addGuestView.getParentView().resetGuestTable();
		this.addGuestView.dispose();
	}
	
	
}
