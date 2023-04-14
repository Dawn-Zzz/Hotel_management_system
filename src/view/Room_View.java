package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import view.editComponent.Button;
import view.editComponent.TextField;

public class Room_View extends JPanel{
	public Room_View() {
//		this.Init();
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
//		subBarSection();
//		mainSection();
		this.add(subBar);
		this.add(mainContent);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(0,1,1,1,new Color(204,204,204)));
		subBar.add(editRoomButton);
		subBar.add(roomSearch);
		subBar.add(statusSearch);

		editRoomButton.setBounds(10,25,129,40);
		editRoomButton.setText("Edit Room");
		editRoomButton.setForeground(Color.WHITE);
		editRoomButton.setBackground(new Color(39,162,187));
		editRoomButton.setLayout(null);
		editRoomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editRoomButton.setFocusable(false);
		
		roomSearch.setBounds(10,85,129,120);
		roomSearch.setForeground(Color.WHITE);
		roomSearch.setBackground(new Color(241,243,255));
		roomSearch.setBorder(null);
		roomSearch.add(roomType);
		roomSearch.add(vipRoom);
		roomSearch.add(popularRoom);
		roomSearch.add(bedList);
		
		roomType.setBounds(0,0,32,100);
		roomType.setText("Room Type");
		roomType.setPreferredSize(new Dimension(129,25));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(new Color(241,243,255));
		roomType.setBorder(null);
		
		vipRoom.setBounds(00, 40, 20, 90);
		vipRoom.setBackground(new Color(241,243,255));
		vipRoom.setPreferredSize(new Dimension(137,25));
		
		popularRoom.setBounds(00, 60, 20, 90);
		popularRoom.setBackground(new Color(241,243,255));
		popularRoom.setPreferredSize(new Dimension(137,25));
		
		bedList.setBounds(0,30,20,20);
		bedList.setPreferredSize(new Dimension(129,25));
		bedList.setBackground(Color.WHITE);
		
		statusSearch.setBounds(10, 210, 129, 150);
		statusSearch.setForeground(Color.WHITE);
		statusSearch.setBackground(new Color(241,243,255));
		statusSearch.setBorder(null);
		statusSearch.add(currentsStatus);
		statusSearch.add(statusList);
		
		currentsStatus.setBounds(0,0,32,100);
		currentsStatus.setText("Current Status");
		currentsStatus.setPreferredSize(new Dimension(129,25));
		currentsStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentsStatus.setForeground(Color.BLACK);
		currentsStatus.setBackground(new Color(241,243,255));
		currentsStatus.setBorder(null);
		
		statusList.setBounds(0,20,32,20);
		statusList.setPreferredSize(new Dimension(129,25));
		statusList.setBackground(Color.WHITE);
		
		addRoom(mainRoomList);
		
		
		mainContent.setBounds(150,0,1020-150-64,720);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(mainRoomList);
		mainContent.add(searchBar);
		
		mainRoomList.setBounds(0,85,1020-150-84,720-120);
		mainRoomList.setLayout(new GridLayout(6,6));
		mainRoomList.setBorder(null);
		
		searchBar.setBounds(0,0,1020-150-64,85);
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
	
	String bedType[] = {"All" ,"1 Single Bed", "1 Double Bed", "2 Single Bed", "2 Double Bed"};
	String statusType[] = {"All", "Vacant", "Occupied", "Room off" , "Book in advance"};
	
	//sub bar
	private JPanel subBar = new JPanel();
	private JButton editRoomButton = new Button();
	private JPanel roomSearch = new JPanel();
	private JLabel roomType = new JLabel();
	private JCheckBox vipRoom = new JCheckBox("Vip");
	private JCheckBox popularRoom = new JCheckBox("Popular");
	private JComboBox bedList = new JComboBox(bedType);
	
	private JPanel statusSearch = new JPanel();
	private JLabel currentsStatus = new JLabel();
	private JComboBox statusList = new JComboBox(statusType);
//	private JComboBox yearList = new JComboBox(YearList);
//	private JComboBox monthList = new JComboBox(MonthList);
//	private JComboBox dayList = new JComboBox(DayList);
	
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel mainRoomList = new JPanel();
	private JPanel searchBar = new JPanel();
	private JButton logOutButton = new Button();
	private JTextField searchBox = new TextField();
	
	private JButton RoomList[] = new JButton[36];
	
	public void addRoom(JPanel mainRoomList) {
		for(int i = 0; i < 36; i++) {
			if(i < 6) {
				RoomList[i] = new JButton("10" + (i+1));
			}
			else if(i < 12){
				RoomList[i] = new JButton("20" + (i-6+1));
			}
			else if(i < 18){
				RoomList[i] = new JButton("30" + (i-12+1));
			}
			else if(i < 24){
				RoomList[i] = new JButton("40" + (i-18+1));
			}
			else if(i < 30){
				RoomList[i] = new JButton("50" + (i-24+1));
			}
			else {
				RoomList[i] = new JButton("60" + (i-30+1));
			}
		}
		for(int i = 0; i < 36; i++) {
			RoomList[i].setBackground(new Color(241,243,255));
			RoomList[i].setBorder(BorderFactory.createMatteBorder(0,0,1,1,new Color(221,221,221)));
			RoomList[i].setForeground(new Color(102,0,204));
			RoomList[i].setFont(new Font("Inter", Font.BOLD, 16));
			RoomList[i].setFocusable(false);
			RoomList[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			mainRoomList.add(RoomList[i]);
		}
	}
}