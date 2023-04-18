package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SignupView;

public class SignupController implements ActionListener {
	private SignupView signupView;
	
	public SignupController(SignupView signupView) {
		super();
		this.signupView = signupView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Sign Up")) {
			signupView.signupAction();
		}
	}
}
