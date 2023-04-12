package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.LoginView;
import view.SignupView;

public class LoginListener implements MouseListener{
	private LoginView loginView;
	private SignupView signupView = new SignupView();
	
	public LoginListener(LoginView loginView) {
		super();
		this.loginView = loginView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method
		if (e.getSource().equals(loginView.getLogin_button())) 
			loginView.LoginAction();
		if (e.getComponent().equals(loginView.getSignup_button())) 
			if (loginView.isCheckSignUp_button() == false) {
				this.signupView.setVisible(true);
				loginView.setCheckSignUp_button(true); 
		} 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (this.signupView.isVisible()==false && loginView.isCheckSignUp_button() == true) {
			loginView.setCheckSignUp_button(false); 
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
