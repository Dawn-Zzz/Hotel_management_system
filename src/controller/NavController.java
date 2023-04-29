package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BillView;
import view.DashBoardView;
import view.GuestView;
import view.RoomView;
import view.EquipmentView;
import view.View;

public class NavController implements ActionListener{
	
	private View buttonView;
	private DashBoardView dashBoardView = new DashBoardView();
	private GuestView guestView = new GuestView();
	private RoomView roomView = new RoomView();
	private EquipmentView serviceView = new EquipmentView();
	private BillView billView = new BillView();

	public NavController(View buttonView) {
		this.buttonView = buttonView;
		this.buttonView.getOtherBar().add(dashBoardView);
		this.buttonView.getOtherBar().add(guestView);
		this.buttonView.getOtherBar().add(roomView);
		this.buttonView.getOtherBar().add(serviceView);
		this.buttonView.getOtherBar().add(billView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nguonSuKien = e.getActionCommand();
		if(nguonSuKien.equals("1")) {
			dashBoardView.setVisible(true);
			guestView.setVisible(false);
			roomView.setVisible(false);
			serviceView.setVisible(false);
			billView.setVisible(false);
			System.out.println("1");
		}
		else if(nguonSuKien.equals("2")) {
			dashBoardView.setVisible(false);
			guestView.setVisible(true);
			roomView.setVisible(false);
			serviceView.setVisible(false);
			billView.setVisible(false);
			System.out.println("2");
		}
		else if(nguonSuKien.equals("3")) {
			dashBoardView.setVisible(false);
			guestView.setVisible(false);
			roomView.setVisible(true);
			serviceView.setVisible(false);
			billView.setVisible(false);
			System.out.println("3");
		}
		else if(nguonSuKien.equals("4")) {
			dashBoardView.setVisible(false);
			guestView.setVisible(false);
			roomView.setVisible(false);
			serviceView.setVisible(true);
			billView.setVisible(false);
			System.out.println("4");
		}
		else if(nguonSuKien.equals("5")) {
			dashBoardView.setVisible(false);
			guestView.setVisible(false);
			roomView.setVisible(false);
			serviceView.setVisible(false);
			billView.setVisible(true);
			System.out.println("5");
		}
	}
}
