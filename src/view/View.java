package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.BTLController;
import model.BTLModel;
import test.Test;
import view.editComponent.Button;
import view.editComponent.TextField;

public class View extends JFrame{
	
	private BTLModel btlModel;
	
	public View() {
		this.btlModel = new BTLModel();
		initView();
		dv.setVisible(true);
		gv.setVisible(false);
		rv.setVisible(false);
		sv.setVisible(false);
		bv.setVisible(false);
	}
	
	
//	
	public void initView( ) {
		leftBarSection();
		otherSection();
//		subMainSection();
//		Room_View v = new Room_View();
//		v.setVisible(true);
		this.setTitle("HOTEL MANAGEMENT");
		this.setSize(1020, 720);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(leftBar);
		this.add(otherBar);
	}
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public JPanel leftBar = new JPanel();
	public JPanel otherBar = new JPanel();
	public JButton dashBoardButton = new JButton("1");
	public JButton guestButton = new JButton("2");
	public JButton roomButton = new JButton("3");
	public JButton serviceButton = new JButton("4");
	public JButton billButton = new JButton("5");
	BTLController btlController = new BTLController(this);

	public int temp = 1;

	public DashBoard_View dv = new DashBoard_View();
	public Guest_View gv = new Guest_View();
	public Room_View rv = new Room_View();
	public Service_View sv = new Service_View();
	public Bill_View bv = new Bill_View();
	
	public void leftBarSection() {
		//Left bar section
		leftBar.setBounds(0,0,64,720);
		leftBar.setLayout(null);
		leftBar.setBackground(new Color(39,162,187));
		leftBar.add(dashBoardButton);
		leftBar.add(guestButton);
		leftBar.add(roomButton);
		leftBar.add(serviceButton);
		leftBar.add(billButton);
		
		dashBoardButton.setBounds(0, 30, 64, 40);
		dashBoardButton.setLayout(null);
		dashBoardButton.setBorder(null);
		dashBoardButton.setBackground(new Color(39,162,187));
		dashBoardButton.setForeground(new Color(39,162,187));
		dashBoardButton.setIcon(new ImageIcon("./Images/stats1.png"));
		dashBoardButton.setHorizontalTextPosition(0);
		dashBoardButton.setFont(new Font("Arial", Font.PLAIN, 0));
		dashBoardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dashBoardButton.setFocusable(false);
		dashBoardButton.addActionListener(btlController);
		
		guestButton.setBounds(0, 90, 64, 40);
		guestButton.setLayout(null);
		guestButton.setBorder(null);
		guestButton.setBackground(new Color(39,162,187));
		guestButton.setForeground(new Color(39,162,187));
		guestButton.setIcon(new ImageIcon("./Images/users.png"));
		guestButton.setHorizontalTextPosition(0);
		guestButton.setFont(new Font("Arial", Font.PLAIN, 0));
		guestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(temp == 2) {
			guestButton.setBorder(BorderFactory.createMatteBorder(0,0,4,0,new Color(220,248,185)));
		}
		guestButton.setFocusable(false);
		
		guestButton.addActionListener(btlController);
		
		roomButton.setBounds(0, 150, 64, 40);
		roomButton.setLayout(null);
		roomButton.setBorder(null);
		roomButton.setBackground(new Color(39,162,187));
		roomButton.setForeground(new Color(39,162,187));
		roomButton.setIcon(new ImageIcon("./Images/bed2.png"));
		roomButton.setHorizontalTextPosition(0);
		roomButton.setFont(new Font("Arial", Font.PLAIN, 0));
		roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		roomButton.setFocusable(false);
		
		roomButton.addActionListener(btlController);
		
		serviceButton.setBounds(0, 210, 64, 40);
		serviceButton.setLayout(null);
		serviceButton.setBorder(null);
		serviceButton.setBackground(new Color(39,162,187));
		serviceButton.setForeground(new Color(39,162,187));
		serviceButton.setIcon(new ImageIcon("./Images/service2.png"));
		serviceButton.setHorizontalTextPosition(0);
		serviceButton.setFont(new Font("Arial", Font.PLAIN, 0));
		serviceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		serviceButton.setFocusable(false);
		serviceButton.addActionListener(btlController);
		
		billButton.setBounds(0, 270, 64, 40);
		billButton.setLayout(null);
		billButton.setBorder(null);
		billButton.setBackground(new Color(39,162,187));
		billButton.setForeground(new Color(39,162,187));
		billButton.setIcon(new ImageIcon("./Images/bill2.png"));
		billButton.setHorizontalTextPosition(0);
		billButton.setFont(new Font("Arial", Font.PLAIN, 0));
		billButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		billButton.setFocusable(false);
		billButton.addActionListener(btlController);
	}
	
	public void changToDashboard() {
		this.btlModel.setInterface_1();
		temp = 1;
	}
	
	public void changToGuest() {
		this.btlModel.setInterface_2();
		temp = 2;
		
//		otherBar.validate();
//		otherBar.add(gv);
//		otherBar.repaint();
//		System.out.println("ok1");
//		otherBar.getParent().remove(otherBar);
//		this.pack();
//		otherBar.add(gv);
//		this.pack();
	}
	public void changToRoom() {
		this.btlModel.setInterface_3();
		temp = 3;
//		otherBar.removeAll();
//		otherBar.validate();
//		otherBar.add(rv);
//		otherBar.repaint();
////		System.out.println("ok2");
//		otherBar.getParent().remove(otherBar);
//		this.pack();
//		otherBar.add(rv);
//		this.pack();
	}
	
	public void changToService() {
		this.btlModel.setInterface_4();
		temp = 4;
	}
	
	public void changToBill() {
		this.btlModel.setInterface_5();
		temp = 5;
	}
	
	
	public void otherSection() {
//		Room_View rv = new Room_View();
		otherBar.setVisible(true);
		otherBar.setBounds(64,0,1020-84,720);
		otherBar.setLayout(null);
		otherBar.add(dv);
		otherBar.add(gv);
		otherBar.add(rv);
		otherBar.add(sv);
		otherBar.add(bv);
//		if(temp == 2 && otherBar.isVisible()) {
//			otherBar.removeAll();
//			otherBar.revalidate();
//			otherBar.repaint();
//			otherBar.setVisible(false);
//			otherBar.add(gv);
//		}
//		else if(temp == 3) {
//			otherBar.removeAll();
//			otherBar.revalidate();
//			otherBar.repaint();
//			otherBar.setVisible(true);
//			otherBar.add(rv);
//		}
//		otherBar.add(gv);
//		otherBar.remove(gv);
//		otherBar.
//		otherBar.remove(rv);
	}
	
	
	
//	public JPanel subMain = new JPanel();
	
	
//	public void subMain() {
////		otherBar.setBounds(84,0,1020-84,720);
////		otherBar.setLayout(null);
////		otherBar.add(subMain);
//		
//		subMain.setBounds(0,0,1020-84,720);
//		subMain.setLayout(null);
////		subBarSection();
////		mainSection();
//		subMain.add(subBar);
//		subMain.add(mainContent);
//		
//		subBar.setBounds(0,0,190,690);
//		subBar.setLayout(null);
//		subBar.setBackground(new Color(241,243,255));
//		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
//		subBar.add(editRoomButton);
//		subBar.add(roomSearch);
//		subBar.add(statusSearch);
//
//		editRoomButton.setBounds(22,25,149,40);
//		editRoomButton.setText("Edit Room");
//		editRoomButton.setForeground(Color.WHITE);
//		editRoomButton.setBackground(new Color(39,162,187));
//		editRoomButton.setLayout(null);
//		editRoomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		editRoomButton.setFocusable(false);
//		
//		roomSearch.setBounds(22,85,149,130);
//		roomSearch.setForeground(Color.WHITE);
//		roomSearch.setBackground(new Color(241,243,255));
//		roomSearch.setBorder(null);
//		roomSearch.add(roomType);
//		roomSearch.add(vipRoom);
//		roomSearch.add(popularRoom);
//		roomSearch.add(roomList);
//		
//		roomType.setBounds(0,0,32,100);
//		roomType.setText("Room Type");
//		roomType.setPreferredSize(new Dimension(149,25));
//		roomType.setFont(new Font("Arial",Font.BOLD,14));
//		roomType.setForeground(Color.BLACK);
//		roomType.setBackground(new Color(241,243,255));
//		roomType.setBorder(null);
//		
//		vipRoom.setBounds(0, 40, 20, 100);
//		vipRoom.setBackground(new Color(241,243,255));
//		vipRoom.setPreferredSize(new Dimension(155,25));
//		
//		popularRoom.setBounds(0, 60, 20, 100);
//		popularRoom.setBackground(new Color(241,243,255));
//		popularRoom.setPreferredSize(new Dimension(155,25));
//		
//		roomList.setBounds(0,30,20,20);
//		roomList.setPreferredSize(new Dimension(149,25));
//		roomList.setBackground(Color.WHITE);
//		
//		statusSearch.setBounds(22, 250, 149, 150);
//		statusSearch.setForeground(Color.WHITE);
//		statusSearch.setBackground(new Color(241,243,255));
//		statusSearch.setBorder(null);
//		statusSearch.add(currentsStatus);
//		statusSearch.add(statusList);
//		
//		currentsStatus.setBounds(0,0,32,100);
//		currentsStatus.setText("Current Status");
//		currentsStatus.setPreferredSize(new Dimension(149,25));
//		currentsStatus.setFont(new Font("Arial",Font.BOLD,14));
//		currentsStatus.setForeground(Color.BLACK);
//		currentsStatus.setBackground(new Color(241,243,255));
//		currentsStatus.setBorder(null);
//		
//		statusList.setBounds(0,20,32,20);
//		statusList.setPreferredSize(new Dimension(149,25));
//		statusList.setBackground(Color.WHITE);
//		
//		
//		
//		
//		mainContent.setBounds(90,0,1020-174,690);
//		mainContent.setLayout(null);
//		mainContent.setBackground(Color.WHITE);
//		mainContent.add(searchBar);
//		
//		searchBar.setBounds(0,0,1020-174,85);
//		searchBar.setLayout(null);
//		searchBar.setBackground(new Color(241,243,255));
//		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
//		searchBar.add(searchBox);
//		searchBar.add(logOutButton);
////				logOutButton.setFont(new Font("Arial",Font.BOLD,14));
//		logOutButton.setText("Log Out");
//		logOutButton.setForeground(Color.WHITE);
//		logOutButton.setBounds(1020-174-150,25,80,40);
//		logOutButton.setLayout(null);
//		logOutButton.setBackground(new Color(39,162,187));
//		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		logOutButton.setFocusable(false);
////				logOutButton.setBorder(new RoundedBorder(10));
//		searchBox.setBounds(180,25,300,40);	
//		searchBox.setBackground(Color.WHITE);
//	}
//	
//	String roomType[] = {"All" ,"1 Single room", "1 Double room", "2 Single room", "2 Double room"};
//	String statusType[] = {"All", "Vacant", "Occupied", "Room off" , "Book in advance"};
//	
//	//sub bar
//	private JPanel subBar = new JPanel();
//	private JButton editRoomButton = new Button();
//	private JPanel roomSearch = new JPanel();
//	private JLabel roomType = new JLabel();
//	private JCheckBox vipRoom = new JCheckBox("Vip");
//	private JCheckBox popularRoom = new JCheckBox("Popular");
//	private JComboBox roomList = new JComboBox(roomType);
//	
//	private JPanel statusSearch = new JPanel();
//	private JLabel currentsStatus = new JLabel();
//	private JComboBox statusList = new JComboBox(statusType);
////	private JComboBox yearList = new JComboBox(YearList);
////	private JComboBox monthList = new JComboBox(MonthList);
////	private JComboBox dayList = new JComboBox(DayList);
//	
//	//main section
//	private JPanel mainContent = new JPanel();
//	private JPanel searchBar = new JPanel();
//	private JButton logOutButton = new Button();
//	private JTextField searchBox = new TextField();
	
//	public void subBarSection() {
//		subBar.setBounds(84,0,190,690);
//		subBar.setLayout(null);
//		subBar.setBackground(new Color(241,243,255));
//		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
//		subBar.add(editRoomButton);
//		subBar.add(roomSearch);
//		subBar.add(statusSearch);
//
//		editRoomButton.setBounds(22,25,149,40);
//		editRoomButton.setText("Edit Room");
//		editRoomButton.setForeground(Color.WHITE);
//		editRoomButton.setBackground(new Color(39,162,187));
//		editRoomButton.setLayout(null);
//		editRoomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		editRoomButton.setFocusable(false);
//		
//		roomSearch.setBounds(22,85,149,130);
//		roomSearch.setForeground(Color.WHITE);
//		roomSearch.setBackground(new Color(241,243,255));
//		roomSearch.setBorder(null);
//		roomSearch.add(roomType);
//		roomSearch.add(vipRoom);
//		roomSearch.add(popularRoom);
//		roomSearch.add(roomList);
//		
//		roomType.setBounds(0,0,32,100);
//		roomType.setText("Room Type");
//		roomType.setPreferredSize(new Dimension(149,25));
//		roomType.setFont(new Font("Arial",Font.BOLD,14));
//		roomType.setForeground(Color.BLACK);
//		roomType.setBackground(new Color(241,243,255));
//		roomType.setBorder(null);
//		
//		vipRoom.setBounds(0, 40, 20, 100);
//		vipRoom.setBackground(new Color(241,243,255));
//		vipRoom.setPreferredSize(new Dimension(155,25));
//		
//		popularRoom.setBounds(0, 60, 20, 100);
//		popularRoom.setBackground(new Color(241,243,255));
//		popularRoom.setPreferredSize(new Dimension(155,25));
//		
//		roomList.setBounds(0,30,20,20);
//		roomList.setPreferredSize(new Dimension(149,25));
//		roomList.setBackground(Color.WHITE);
//		
//		statusSearch.setBounds(22, 250, 149, 150);
//		statusSearch.setForeground(Color.WHITE);
//		statusSearch.setBackground(new Color(241,243,255));
//		statusSearch.setBorder(null);
//		statusSearch.add(currentsStatus);
//		statusSearch.add(statusList);
//		
//		currentsStatus.setBounds(0,0,32,100);
//		currentsStatus.setText("Current Status");
//		currentsStatus.setPreferredSize(new Dimension(149,25));
//		currentsStatus.setFont(new Font("Arial",Font.BOLD,14));
//		currentsStatus.setForeground(Color.BLACK);
//		currentsStatus.setBackground(new Color(241,243,255));
//		currentsStatus.setBorder(null);
//		
//		statusList.setBounds(0,20,32,20);
//		statusList.setPreferredSize(new Dimension(149,25));
//		statusList.setBackground(Color.WHITE);
//		
//		
//	}
////	
//	public void mainSection() {
//		//Main section
//		mainContent.setBounds(174,0,1020-174,690);
//		mainContent.setLayout(null);
//		mainContent.setBackground(Color.WHITE);
//		mainContent.add(searchBar);
//		
//		searchBar.setBounds(0,0,1020-174,85);
//		searchBar.setLayout(null);
//		searchBar.setBackground(new Color(241,243,255));
//		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
//		searchBar.add(searchBox);
//		searchBar.add(logOutButton);
////				logOutButton.setFont(new Font("Arial",Font.BOLD,14));
//		logOutButton.setText("Log Out");
//		logOutButton.setForeground(Color.WHITE);
//		logOutButton.setBounds(1020-174-150,25,80,40);
//		logOutButton.setLayout(null);
//		logOutButton.setBackground(new Color(39,162,187));
//		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		logOutButton.setFocusable(false);
////				logOutButton.setBorder(new RoundedBorder(10));
//		searchBox.setBounds(180,25,300,40);	
//		searchBox.setBackground(Color.WHITE);
//	}
}

//public class View extends javax.swing.JFrame {
//	JPanel leftBar = new JPanel();
//	JPanel dashBoardButton = new javax.swing.JPanel();
//	ImageIcon dashBoardIcon = new javax.swing.ImageIcon("icon_image/stats1.png");
//	JLabel dashBoardLabel = new javax.swing.JLabel("Dash Board");
//	JPanel guestButton = new javax.swing.JPanel();
//	JLabel guestLabel = new javax.swing.JLabel("Guest");
//	JPanel roomButton = new javax.swing.JPanel();
//	JLabel roomLabel = new javax.swing.JLabel("Room");
//	JPanel serviceButton = new javax.swing.JPanel();
//	JLabel serviceLabel = new javax.swing.JLabel("Service");
//	JPanel billButton = new javax.swing.JPanel();
//	JLabel billLabel = new javax.swing.JLabel("Bill");
//	public View() {
//		Init();
//		this.setTitle("HOTEL MANAGEMENT");
//		this.setSize(1020, 690);
//		this.setLayout(null);
////		this.setResizable(true);
////		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		this.setLocationRelativeTo(null);
//		this.setVisible(true);
//		
//		BTLController controller = new BTLController(leftBar);
//		controller.setView(guestButton, guestLabel);
//		List<Test> listItem = new ArrayList<>();
//		listItem.add(new Test("Guest", guestButton, guestLabel));
//		listItem.add(new Test("Room", roomButton, roomLabel));
//        controller.setEvent(listItem);
//	}
//	
//	private void Init() {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		
//		
//
//		javax.swing.GroupLayout dashBoardLayout = new javax.swing.GroupLayout(dashBoardButton);
//		javax.swing.GroupLayout guestLayout = new javax.swing.GroupLayout(guestButton);
//		javax.swing.GroupLayout roomLayout = new javax.swing.GroupLayout(roomButton);
//		javax.swing.GroupLayout serviceLayout = new javax.swing.GroupLayout(serviceButton);
//		javax.swing.GroupLayout billLayout = new javax.swing.GroupLayout(billButton);
//		
//		dashBoardLabel.setBounds(10,0,84,40);
//		dashBoardLabel.setForeground(Color.WHITE);
//		guestLayout.setHorizontalGroup(
//				guestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//				.addGroup(guestLayout.createSequentialGroup()
//				.addComponent(guestLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
//				.addGap(34,34,34)
//				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//				)
//		);
//        guestLayout.setVerticalGroup(
//                guestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, guestLayout.createSequentialGroup()
//                    .addContainerGap(19, Short.MAX_VALUE)
//                    .addComponent(guestLabel)
//                    .addGap(17, 17, 17)
//                    )
//            );
//		
//		dashBoardButton.setBounds(0, 60, 84, 40);
//		dashBoardButton.setLayout(dashBoardLayout);
//		dashBoardButton.setBorder(new EmptyBorder(10, 12, 15, 12));
//		dashBoardButton.setBackground(new Color(39,162,187));
////		dashBoardButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Guest_View.class.getResource("../icon_image/stats1.png"))));
//		dashBoardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		dashBoardButton.setFocusable(false);
//		dashBoardButton.add(dashBoardLabel);
//		
//		guestLabel.setBounds(10,0,84,40);
//		guestLabel.setForeground(Color.WHITE);
//		
//		guestButton.setBounds(0, 120, 84, 40);
//		guestButton.setLayout(guestLayout);
//		guestButton.setBorder(null);
//		guestButton.setBackground(new Color(39,12,187));
////		guestButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Guest_View.class.getResource("../icon_image/guests2.png"))));
//		guestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		guestButton.setFocusable(false);
//		guestButton.add(guestLabel);
//		
//		roomLabel.setBounds(10,0,84,40);
//		roomLabel.setForeground(Color.WHITE);
//		roomButton.setBounds(0, 180, 84, 40);
//		roomButton.setLayout(null);
//		roomButton.setBorder(null);
//		roomButton.setBackground(new Color(39,162,187));
////		roomButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Guest_View.class.getResource("../icon_image/room.png"))));
//		roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		roomButton.setFocusable(false);
//		roomButton.add(roomLabel);
//		
//		serviceLabel.setBounds(10,0,84,40);
//		serviceLabel.setForeground(Color.WHITE);
//		serviceButton.setBounds(0, 240, 84, 40);
//		serviceButton.setLayout(null);
//		serviceButton.setBorder(null);
//		serviceButton.setBackground(new Color(39,162,187));
////		serviceButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Guest_View.class.getResource("../icon_image/service.png"))));
//		serviceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		serviceButton.setFocusable(false);
//		serviceButton.add(serviceLabel);
//		
//		billLabel.setBounds(10,0,84,40);
//		billLabel.setForeground(Color.WHITE);
//		billButton.setBounds(0, 300, 84, 40);
//		billButton.setLayout(null);
//		billButton.setBorder(null);
//		billButton.setBackground(new Color(39,162,187));
////		billButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Guest_View.class.getResource("../icon_image/bill.png"))));
//		billButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		billButton.add(billLabel);		
//
////		setTitle("HOTEL MANAGEMENT");
////		setSize(screenSize.width, screenSize.height-30);
////		setLayout(null);
////		setResizable(true);
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		setLocationRelativeTo(null);
////		setVisible(true);
//		
//		leftBar.setBounds(0,0,84,screenSize.height-30);
//		leftBar.setLayout(null);
//		leftBar.setBackground(new Color(39,16,187));
//		leftBar.add(dashBoardButton);
//		leftBar.add(guestButton);
//		leftBar.add(roomButton);
//		leftBar.add(serviceButton);
//		leftBar.add(billButton);
//		add(leftBar);
//		
////		billButton.setFocusable(false);
//	}
//}
