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
	private BTLModel buttonModel;
	
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
	}
	
	public void changToGuest() {
		this.btlModel.setInterface_2();
		temp = 2;
	}
	public void changToRoom() {
		this.btlModel.setInterface_3();
		temp = 3;
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
		otherBar.setVisible(true);
		otherBar.setBounds(64,0,1020-84,720);
		otherBar.setLayout(null);
		otherBar.add(dv);
		otherBar.add(gv);
		otherBar.add(rv);
		otherBar.add(sv);
		otherBar.add(bv);
	}
}