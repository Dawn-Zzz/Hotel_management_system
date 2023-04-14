package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BTLModel;
import test.Test;
import view.Guest_View;
import view.Room_View;
import view.View;

public class BTLController implements ActionListener{
	
	private View buttonView;
	
	

	public BTLController(View buttonView) {
		this.buttonView = buttonView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nguonSuKien = e.getActionCommand();
		if(nguonSuKien.equals("1")) {
			buttonView.temp = 1;
//			buttonView.otherBar.removeAll();
//			buttonView.otherBar.add(new Guest_View());
			buttonView.dv.setVisible(true);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("1");
//			this.buttonView.changToGuest();
		}
//		else if(nguonSuKien.equals("2") && buttonView.dv.isVisible() && buttonView.rv.isVisible() && buttonView.sv.isVisible() && buttonView.bv.isVisible()) {
		else if(nguonSuKien.equals("2")) {
			buttonView.temp = 2;
//			buttonView.otherBar.removeAll();
//			buttonView.otherBar.add(new Guest_View());
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(true);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("2");
//			this.buttonView.changToGuest();
		}
//		else if(nguonSuKien.equals("3") && buttonView.dv.isVisible() && buttonView.gv.isVisible() && buttonView.sv.isVisible() && buttonView.bv.isVisible()) {
		else if(nguonSuKien.equals("3")) {
			buttonView.temp = 3;
//			buttonView.otherBar.removeAll();
//			buttonView.otherBar.add(new Room_View());
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(true);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("3");
//			this.buttonView.changToRoom();
		}
		else if(nguonSuKien.equals("4")) {
			buttonView.temp = 4;
//			buttonView.otherBar.removeAll();
//			buttonView.otherBar.add(new Room_View());
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(true);
			buttonView.bv.setVisible(false);
			System.out.println("4");
//			this.buttonView.changToRoom();
		}
		else if(nguonSuKien.equals("5")) {
			buttonView.temp = 5;
//			buttonView.otherBar.removeAll();
//			buttonView.otherBar.add(new Room_View());
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(true);
			System.out.println("5");
//			this.buttonView.changToRoom();
		}
	}
	
}
