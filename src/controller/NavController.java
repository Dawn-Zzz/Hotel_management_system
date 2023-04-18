package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BillView;
import view.DashBoardView;
import view.GuestView;
import view.RoomView;
import view.ServiceView;
import view.View;

public class NavController implements ActionListener{
	
	private View buttonView;
	private DashBoardView dv;
	private GuestView gv;
	private RoomView rv;
	private ServiceView sv;
	private BillView bv;

	public NavController(View buttonView, DashBoardView dv, GuestView gv, RoomView rv, ServiceView sv, BillView bv) {
		this.buttonView = buttonView;
		this.dv = dv;
		this.gv = gv;
		this.rv = rv;
		this.sv = sv;
		this.bv = bv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nguonSuKien = e.getActionCommand();
		if(nguonSuKien.equals("1")) {
//			buttonView.temp = 1;
			dv.setVisible(true);
			gv.setVisible(false);
			rv.setVisible(false);
			sv.setVisible(false);
			bv.setVisible(false);
			System.out.println("1");
		}
		else if(nguonSuKien.equals("2")) {
//			temp = 2;
			dv.setVisible(false);
			gv.setVisible(true);
			rv.setVisible(false);
			sv.setVisible(false);
			bv.setVisible(false);
			System.out.println("2");
		}
		else if(nguonSuKien.equals("3")) {
//			temp = 3;
			dv.setVisible(false);
			gv.setVisible(false);
			rv.setVisible(true);
			sv.setVisible(false);
			bv.setVisible(false);
			System.out.println("3");
		}
		else if(nguonSuKien.equals("4")) {
//			temp = 4;
			dv.setVisible(false);
			gv.setVisible(false);
			rv.setVisible(false);
			sv.setVisible(true);
			bv.setVisible(false);
			System.out.println("4");
		}
		else if(nguonSuKien.equals("5")) {
//			temp = 5;
			dv.setVisible(false);
			gv.setVisible(false);
			rv.setVisible(false);
			sv.setVisible(false);
			bv.setVisible(true);
			System.out.println("5");
		}
	}
}
