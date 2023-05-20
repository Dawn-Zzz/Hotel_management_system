package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAO.GuestDAO;
import model.Guest;
import model.Room;
import view.editComponent.Button;
import view.editComponent.Table;

public class RoomInfor extends JDialog{
	public RoomInfor(Room room, String bed) {
		this.setUndecorated(true);
		this.room = room;
		this.bed = bed;
		this.guest = GuestDAO.getInstance().getGuestByRoomID(room.getNumberRoom());
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
		roomNumber.setText("Room " + room.getNumberRoom());
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
		roomType.setText("Room Type: " + room.getTypeRoom());
		roomType.setPreferredSize(new Dimension(250,30));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(Color.WHITE);
		roomType.setBorder(null);
		
		currentStatus.setBounds(410,160,250,30);
		if (room.getCurrentStatus().equals("0"))
			currentStatus.setText("Current Status: " + "Trống");
		else if (room.getCurrentStatus().equals("1"))
			currentStatus.setText("Current Status: " + "Đã cho thuê");
		else
			currentStatus.setText("Current Status: " + "Đang sửa chữa");
		currentStatus.setPreferredSize(new Dimension(250,30));
		currentStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentStatus.setForeground(Color.BLACK);
		currentStatus.setBackground(Color.WHITE);
		currentStatus.setBorder(null);
		
		bedQuantity.setBounds(410,190,250,30);
		bedQuantity.setText("Bed Quantity: " + bed);
		bedQuantity.setPreferredSize(new Dimension(250,30));
		bedQuantity.setFont(new Font("Arial",Font.BOLD,14));
		bedQuantity.setForeground(Color.BLACK);
		bedQuantity.setBackground(Color.WHITE);
		bedQuantity.setBorder(null);
		
		
		guestQuantity.setBounds(590,190,250,30);
		guestQuantity.setText("Guest Quantity: ");
		guestQuantity.setPreferredSize(new Dimension(250,30));
		guestQuantity.setFont(new Font("Arial",Font.BOLD,14));
		guestQuantity.setForeground(Color.BLACK);
		guestQuantity.setBackground(Color.WHITE);
		guestQuantity.setBorder(null);
		
		guestInforTitle.setBounds(410,270,250,30);
		guestInforTitle.setPreferredSize(new Dimension(250,30));
		guestInforTitle.setFont(new Font("Arial",Font.BOLD,18));
		guestInforTitle.setForeground(Color.BLACK);
		guestInforTitle.setBackground(Color.WHITE);
		guestInforTitle.setBorder(null);
		
		guestName.setBounds(410,300,250,30);
		if (guest == null)
			guestName.setText("Guest Name: " + "~");
		else
			guestName.setText("Guest Name: " + guest.getName());
		guestName.setPreferredSize(new Dimension(250,30));
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestGender.setBounds(410,330,250,30);
		if (guest == null)
			guestGender.setText("Type: " + "~");
		else
			guestGender.setText("Type: " + guest.getType());
		guestGender.setPreferredSize(new Dimension(250,30));
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		guestBirthday.setBounds(410,360,250,30);
		if (guest == null)
			guestBirthday.setText("Birthday: " + "~");
		else
			guestBirthday.setText("Birthday: " + guest.getBirth());
		guestBirthday.setPreferredSize(new Dimension(250,30));
		guestBirthday.setFont(new Font("Arial",Font.BOLD,14));
		guestBirthday.setForeground(Color.BLACK);
		guestBirthday.setBackground(Color.WHITE);
		guestBirthday.setBorder(null);
		
		guestId.setBounds(590,360,250,30);
		if (guest == null)
			guestId.setText("Guest ID: " + "~");
		else
			guestId.setText("Guest ID: " + guest.getIdNumber());
		guestId.setPreferredSize(new Dimension(250,30));
		guestId.setFont(new Font("Arial",Font.BOLD,14));
		guestId.setForeground(Color.BLACK);
		guestId.setBackground(Color.WHITE);
		guestId.setBorder(null);
		
		guestPhone.setBounds(590,330,250,30);
		if (guest == null)
			guestPhone.setText("Phone Number: "+ "~");
		else
			guestPhone.setText("Phone Number: "+ guest.getNumberPhone());
		guestPhone.setPreferredSize(new Dimension(250,30));
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);

		exitButton.setBounds(670,450,100,40);
		exitButton.setText("Exit");
		exitButton.setForeground(Color.WHITE);
		exitButton.setBackground(new Color(39,162,187));
		exitButton.setLayout(null);
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		this.setVisible(false);
	}
	
	private Room room;
	private String bed;
	private Guest guest;

	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	
	private JPanel inforSection = new JPanel();
	private JLabel imageSection = new JLabel();
	
	private JLabel roomNumber = new JLabel();
	
	private JLabel roomInforTitle = new JLabel("Room Information");
	private JLabel roomType = new JLabel();
	private JLabel bedQuantity = new JLabel();
	private JLabel currentStatus = new JLabel();
	private JLabel guestQuantity = new JLabel();
	
	private JLabel guestInforTitle = new JLabel("Guest Information");
	
	private JPanel guestListSection = new JPanel();
	private Table guestInforTable = new Table();
	private JLabel guestName = new JLabel();
	private JLabel guestGender = new JLabel();
	private JLabel guestBirthday = new JLabel();
	private JLabel guestId = new JLabel();
	private JLabel guestPhone = new JLabel();
	
	private JButton exitButton = new Button();
}
