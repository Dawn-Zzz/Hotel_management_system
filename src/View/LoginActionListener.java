package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginActionListener implements ActionListener{
	private Login view;
	
	public LoginActionListener(Login view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserModel user = view.getUser();
		if (user.getUserName().equals("admin") && user.getPassword().equals("admin")){
			JOptionPane.showMessageDialog(view, "Success");
		} 
		else {
			JOptionPane.showMessageDialog(view, "Fail");
		}
		view.dispose();
	}

}
