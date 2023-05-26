package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.SignupView;

public class LoginController implements ActionListener{
	private LoginView loginView;
	private SignupView signupView = new SignupView();
	
	public LoginController(LoginView loginView) {
		super();
		this.loginView = loginView;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Log In")) 
			loginView.loginAction();
		if (e.getActionCommand().equals("Sign Up"))
			if (this.signupView.isVisible() == false) {
				this.signupView.setVisible(true);
				this.signupView.resetFormSignup();
			} 
	}
}
