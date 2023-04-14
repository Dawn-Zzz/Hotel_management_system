package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.editComponent.Button;
import view.editComponent.TextField;

public class Service_View extends JPanel {
	public Service_View() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
//		subBarSection();
//		mainSection();
		this.add(subBar);
		this.add(mainContent);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(editServiceButton);
		subBar.add(roomSearch);
		subBar.add(serviceTypeSearch);

		editServiceButton.setBounds(10,25,129,40);
		editServiceButton.setText("Edit Service");
		editServiceButton.setForeground(Color.WHITE);
		editServiceButton.setBackground(new Color(39,162,187));
		editServiceButton.setLayout(null);
		editServiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editServiceButton.setFocusable(false);
		
		roomSearch.setBounds(10,85,129,60);
		roomSearch.setForeground(Color.WHITE);
		roomSearch.setBackground(new Color(241,243,255));
		roomSearch.setBorder(null);
		roomSearch.add(roomType);
		roomSearch.add(bedList);
		
		roomType.setBounds(0,0,32,100);
		roomType.setText("Room Type");
		roomType.setPreferredSize(new Dimension(129,25));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(new Color(241,243,255));
		roomType.setBorder(null);
		
		bedList.setBounds(0,30,20,20);
		bedList.setPreferredSize(new Dimension(129,25));
		bedList.setBackground(Color.WHITE);
		
		serviceTypeSearch.setBounds(10, 150, 129, 150);
		serviceTypeSearch.setForeground(Color.WHITE);
		serviceTypeSearch.setBackground(new Color(241,243,255));
		serviceTypeSearch.setBorder(null);
		serviceTypeSearch.add(serviceTypeList);
		serviceTypeSearch.add(serviceList);
		
		serviceTypeList.setBounds(0,0,32,100);
		serviceTypeList.setText("Service");
		serviceTypeList.setPreferredSize(new Dimension(129,25));
		serviceTypeList.setFont(new Font("Arial",Font.BOLD,14));
		serviceTypeList.setForeground(Color.BLACK);
		serviceTypeList.setBackground(new Color(241,243,255));
		serviceTypeList.setBorder(null);
		
		serviceList.setBounds(0,20,32,20);
		serviceList.setPreferredSize(new Dimension(129,25));
		serviceList.setBackground(Color.WHITE);
		
		
		
		
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);
		searchBar.add(logOutButton);
//				logOutButton.setFont(new Font("Arial",Font.BOLD,14));
		logOutButton.setText("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBounds(1020-174-150,25,80,40);
		logOutButton.setLayout(null);
		logOutButton.setBackground(new Color(39,162,187));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOutButton.setFocusable(false);
//				logOutButton.setBorder(new RoundedBorder(10));
		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
	}
	String RoomList[] = {"All" ,"1 Single Bed", "1 Double Bed", "2 Single Bed", "2 Double Bed"};
	String ServiceList[] = {"All"};
	
	//sub bar
	private JPanel subBar = new JPanel();
	private JButton editServiceButton = new Button();
	private JPanel roomSearch = new JPanel();
	private JLabel roomType = new JLabel();
	private JComboBox bedList = new JComboBox(RoomList);
	
	private JPanel serviceTypeSearch = new JPanel();
	private JLabel serviceTypeList = new JLabel();
	private JComboBox serviceList = new JComboBox(ServiceList);
//	private JComboBox yearList = new JComboBox(YearList);
//	private JComboBox monthList = new JComboBox(MonthList);
//	private JComboBox dayList = new JComboBox(DayList);
	
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel searchBar = new JPanel();
	private JButton logOutButton = new Button();
	private JTextField searchBox = new TextField();
	
}
