package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import view.editComponent.Button;
import view.editComponent.Table;

public class RoomInfor extends JDialog{
	public RoomInfor(String number, String type, String bed, String status, String guest) {
		this.setUndecorated(true);
		this.setNumber(number);
		this.setType(type);
		this.setBed(bed);
		this.setStatus(status);
		this.setGuest(guest);
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,800,520);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(imageSection);
		this.add(roomNumber);
		this.add(roomInforTitle);
		this.add(roomType);
		this.add(bedQuantity);
		this.add(currentStatus);
		this.add(guestQuantity);
		this.add(guestInforTitle);
		this.add(guestName);
		this.add(guestGender);
		this.add(guestBirthday);
		this.add(guestId);
		this.add(guestPhone);
		this.add(exitButton);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		this.setModal(true);
		
		ImageIcon image2 = new ImageIcon("./Images/Background.jpg");
		
		imageSection.setIcon(image2);
		imageSection.setBounds(-10,0,390,520);
		
		roomNumber.setBounds(410,20,220,30);
		roomNumber.setText("Room " + this.number);
		roomNumber.setPreferredSize(new Dimension(250,30));
		roomNumber.setFont(new Font("Arial",Font.BOLD,24));
		roomNumber.setForeground(Color.BLACK);
		roomNumber.setBackground(Color.WHITE);
		roomNumber.setBorder(null);
		
		roomInforTitle.setBounds(410,100,250,30);
		roomInforTitle.setPreferredSize(new Dimension(250,30));
		roomInforTitle.setFont(new Font("Arial",Font.BOLD,18));
		roomInforTitle.setForeground(Color.BLACK);
		roomInforTitle.setBackground(Color.WHITE);
		roomInforTitle.setBorder(null);
		
		roomType.setBounds(410,130,250,30);
		roomType.setText("Room type: " + type);
		roomType.setPreferredSize(new Dimension(250,30));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(Color.WHITE);
		roomType.setBorder(null);
		
		bedQuantity.setBounds(590,130,250,30);
		bedQuantity.setText("Bed quantity: " + bed);
		bedQuantity.setPreferredSize(new Dimension(250,30));
		bedQuantity.setFont(new Font("Arial",Font.BOLD,14));
		bedQuantity.setForeground(Color.BLACK);
		bedQuantity.setBackground(Color.WHITE);
		bedQuantity.setBorder(null);
		
		currentStatus.setBounds(410,160,250,30);
		currentStatus.setText("Current status: " + status);
		currentStatus.setPreferredSize(new Dimension(250,30));
		currentStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentStatus.setForeground(Color.BLACK);
		currentStatus.setBackground(Color.WHITE);
		currentStatus.setBorder(null);
		
		guestQuantity.setBounds(590,160,250,30);
		guestQuantity.setText("Guest quantity: " + guest);
		guestQuantity.setPreferredSize(new Dimension(250,30));
		guestQuantity.setFont(new Font("Arial",Font.BOLD,14));
		guestQuantity.setForeground(Color.BLACK);
		guestQuantity.setBackground(Color.WHITE);
		guestQuantity.setBorder(null);
		
		guestInforTitle.setBounds(410,240,250,30);
		guestInforTitle.setPreferredSize(new Dimension(250,30));
		guestInforTitle.setFont(new Font("Arial",Font.BOLD,18));
		guestInforTitle.setForeground(Color.BLACK);
		guestInforTitle.setBackground(Color.WHITE);
		guestInforTitle.setBorder(null);
		
		guestName.setBounds(410,270,250,30);
		guestName.setText("Guest name: " + type);
		guestName.setPreferredSize(new Dimension(250,30));
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestGender.setBounds(590,270,250,30);
		guestGender.setText("Gender: " + type);
		guestGender.setPreferredSize(new Dimension(250,30));
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		guestBirthday.setBounds(410,300,250,30);
		guestBirthday.setText("Birthday: 02/07/2003");
		guestBirthday.setPreferredSize(new Dimension(250,30));
		guestBirthday.setFont(new Font("Arial",Font.BOLD,14));
		guestBirthday.setForeground(Color.BLACK);
		guestBirthday.setBackground(Color.WHITE);
		guestBirthday.setBorder(null);
		
		guestId.setBounds(590,300,250,30);
		guestId.setText("Guest ID: 0123456789012" );
		guestId.setPreferredSize(new Dimension(250,30));
		guestId.setFont(new Font("Arial",Font.BOLD,14));
		guestId.setForeground(Color.BLACK);
		guestId.setBackground(Color.WHITE);
		guestId.setBorder(null);
		
		guestPhone.setBounds(410,330,250,30);
		guestPhone.setText("Phone Number: " + type);
		guestPhone.setPreferredSize(new Dimension(250,30));
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);

		exitButton.setBounds(650,420,100,40);
		exitButton.setText("Exit");
		exitButton.setForeground(Color.WHITE);
		exitButton.setBackground(new Color(39,162,187));
		exitButton.setLayout(null);
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitButton.setFocusable(false);
		this.setVisible(false);
	}
	
	private String number;
	private String type;
	private String bed;
	private String status;
	private String guest;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
//	public String getType() {
//		return type;
//	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}

	int cell = 4;
	
	private JPanel inforSection = new JPanel();
	private JLabel imageSection = new JLabel();
	
	private JLabel roomNumber = new JLabel();
	
	private JLabel roomInforTitle = new JLabel("Room Information: ");
	private JLabel roomType = new JLabel();
	private JLabel bedQuantity = new JLabel();
	private JLabel currentStatus = new JLabel();
	private JLabel guestQuantity = new JLabel();
	
	private JLabel guestInforTitle = new JLabel("Guest Information:");
	
	private JPanel guestListSection = new JPanel();
	private Table guestInforTable = new Table();
	private JLabel guestName = new JLabel();
	private JLabel guestGender = new JLabel();
	private JLabel guestBirthday = new JLabel();
	private JLabel guestId = new JLabel();
	private JLabel guestPhone = new JLabel();
	
	private JButton exitButton = new Button();
}
