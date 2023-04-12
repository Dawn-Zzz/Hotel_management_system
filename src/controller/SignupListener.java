package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SignupView;

public class SignupListener implements ActionListener {
	private SignupView signupView;
	
	public SignupListener(SignupView signupView) {
		super();
		this.signupView = signupView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Sign Up")) {
			signupView.SignupAction();
		}
	}
}
