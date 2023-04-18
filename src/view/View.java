package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.NavController;
import view.editComponent.Button;

public class View extends JFrame{
	
//	private BTLModel btlModel;
	private DashBoardView dv = new DashBoardView();
	private GuestView gv = new GuestView();
	private RoomView rv = new RoomView();
	private ServiceView sv = new ServiceView();
	private BillView bv = new BillView();
	private NavController btlController = new NavController(this, dv, gv, rv, sv, bv);
	
	public View() {
		initView();
	}
	
	public void initView( ) {
		ImageIcon image = new ImageIcon("./Images/Logo.png");
		this.setIconImage(image.getImage());
		leftBarSection();
		otherSection();
		
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
	private JPanel leftBar = new JPanel();
	private JPanel otherBar = new JPanel();
	private JButton logOutButton = new Button();
	private JButton dashBoardButton = new JButton("1");
	private JButton guestButton = new JButton("2");
	private JButton roomButton = new JButton("3");
	private JButton serviceButton = new JButton("4");
	private JButton billButton = new JButton("5");

	private int temp = 1;

	
	
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
	
	public void otherSection() {
		otherBar.setVisible(true);
		otherBar.setBounds(64,0,1020-84,720);
		otherBar.setLayout(null);
		otherBar.add(logOutButton);
		otherBar.add(dv);
		otherBar.add(gv);
		otherBar.add(rv);
		otherBar.add(sv);
		otherBar.add(bv);

		
		logOutButton.setText("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBounds(1020-174,25,80,40);
		logOutButton.setLayout(null);
		logOutButton.setBackground(new Color(39,162,187));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOutButton.setFocusable(false);
	}
}