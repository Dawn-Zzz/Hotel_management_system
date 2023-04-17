package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Bill_View;
import view.DashBoard_View;
import view.Guest_View;
import view.Room_View;
import view.Service_View;
import view.View;

public class NavController implements ActionListener{
	
	private View buttonView;
	private DashBoard_View dv;
	private Guest_View gv;
	private Room_View rv;
	private Service_View sv;
	private Bill_View bv;

	public NavController(View buttonView, DashBoard_View dv, Guest_View gv, Room_View rv, Service_View sv, Bill_View bv) {
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
