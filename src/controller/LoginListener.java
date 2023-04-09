package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.SignupView;

public class LoginListener implements ActionListener{
	private LoginView loginView;
	private SignupView signupView;
	
	public LoginListener(LoginView loginView) {
		super();
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Log In")) 
			loginView.LoginAction();
		if (src.equals("Sign Up"))
			loginView.SwitchSignUpAction(signupView);
	}
}
