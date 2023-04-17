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
			buttonView.dv.setVisible(true);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("1");
		}
		else if(nguonSuKien.equals("2")) {
			buttonView.temp = 2;
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(true);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("2");
		}
		else if(nguonSuKien.equals("3")) {
			buttonView.temp = 3;
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(true);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(false);
			System.out.println("3");
		}
		else if(nguonSuKien.equals("4")) {
			buttonView.temp = 4;
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(true);
			buttonView.bv.setVisible(false);
			System.out.println("4");
		}
		else if(nguonSuKien.equals("5")) {
			buttonView.temp = 5;
			buttonView.dv.setVisible(false);
			buttonView.gv.setVisible(false);
			buttonView.rv.setVisible(false);
			buttonView.sv.setVisible(false);
			buttonView.bv.setVisible(true);
			System.out.println("5");
		}
	}
	
}
