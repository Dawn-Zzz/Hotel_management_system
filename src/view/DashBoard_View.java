package view;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.editComponent.Button;

public class DashBoard_View extends JPanel{
	public DashBoard_View() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(mainContent);
		mainContent.setBounds(0,0,1020-84,690);
		mainContent.setLayout(null);
		mainContent.setBackground(new Color(241,243,255));
		mainContent.add(searchBar);
		
		searchBar.setBounds(0,0,1020-84,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(logOutButton);
		logOutButton.setText("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBounds(1020-174,25,80,40);
		logOutButton.setLayout(null);
		logOutButton.setBackground(new Color(39,162,187));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOutButton.setFocusable(false);
		
		this.setVisible(true);
	}
	
	private JPanel mainContent = new JPanel();
	private JPanel searchBar = new JPanel();
	private JButton logOutButton = new Button();
}
