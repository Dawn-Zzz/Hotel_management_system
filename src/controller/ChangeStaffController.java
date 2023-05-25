package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import view.ChangeStaffView;
import view.AdminView;

public class ChangeStaffController implements ActionListener {
	private ChangeStaffView changeStaffView;
	private AdminView adminView = AdminView.getInstance();
	
	public ChangeStaffController(ChangeStaffView changeStaffView) {
		super();
		this.changeStaffView = changeStaffView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.changeStaffView.changeStaffAction();
		this.adminView.resetStaffTable();
	}	
}
