package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.BookRoomController;
import view.editComponent.Button;

public class BookRoomView extends JDialog{
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	private JLabel registrationForm = new JLabel();
	
	private JLabel dateTime = new JLabel();
	
	private JLabel questQuantity = new JLabel();
	private JComboBox<Integer> questQuantityBox = new JComboBox<>();
	
	private JLabel rentalType = new JLabel();
	String RentalType[] = {"", "Rent For The Day", "Overnight Rental"}; 
	private JComboBox<String> rentalTypeBox = new JComboBox<>(RentalType);
	
	private JLabel room = new JLabel();
	private JComboBox<Integer> roomBox = new JComboBox<>();
	
	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();
	
	private JLabel checkIn = new JLabel();
	private JComboBox<Integer> hourCIn = new JComboBox<>();
	private JLabel decorationThing1 = new JLabel();
	private JComboBox<Integer> minuteCIn = new JComboBox<>();
	private JDateChooser dayCIn = new JDateChooser();
	
	private JLabel checkOut = new JLabel();
	private JComboBox<Integer> hourCOut = new JComboBox<>();
	private JLabel decorationThing2 = new JLabel();
	private JComboBox<Integer> minuteCOut = new JComboBox<>();
	private JDateChooser dayCOut = new JDateChooser();
	
	private JPanel bookingGroup1 = new JPanel();
	private JPanel bookingGroup2 = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton directBooking = new JRadioButton();
	private JRadioButton advanceBooking = new JRadioButton();
	private JLabel deposit = new JLabel();
	private JTextField depositField = new JTextField();
	private JLabel currencyUnit = new JLabel();
	
	private JButton submitButton = new Button();
	
	private ActionListener actionListener = new BookRoomController(this);
	private ItemListener itemListener = new BookRoomController(this);
	
	public BookRoomView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,0,800,560);
		this.setIconImage(image.getImage());
		this.setLayout(null);
		this.add(registrationForm);
		this.add(dateTime);
		this.add(questQuantity);
		this.add(questQuantityBox);
		this.add(rentalType);
		this.add(rentalTypeBox);
		this.add(room);
		this.add(roomBox);
		this.add(identificationNumber);
		this.add(identificationNumberField);
		this.add(checkIn);
		this.add(hourCIn);
		this.add(decorationThing1);
		this.add(minuteCIn);
		this.add(dayCIn);
		this.add(checkOut);
		this.add(hourCOut);
		this.add(decorationThing2);
		this.add(minuteCOut);
		this.add(dayCOut);
		this.add(bookingGroup1);
		this.add(bookingGroup2);
		this.add(deposit);
		this.add(depositField);
		this.add(currencyUnit);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);
		
		registrationForm.setBounds(50,10,250,30);
		registrationForm.setText("Registration Form");
		registrationForm.setPreferredSize(new Dimension(250,30));
		registrationForm.setFont(new Font("Arial",Font.BOLD,24));
		registrationForm.setForeground(Color.BLACK);
		registrationForm.setBackground(Color.WHITE);
		registrationForm.setBorder(null);
		
		dateTime.setBounds(695,10,70,30);
		dateTime.setText("5/2/2023");
		dateTime.setPreferredSize(new Dimension(250,30));
		dateTime.setFont(new Font("Arial",Font.BOLD,16));
		dateTime.setForeground(Color.BLACK);
		dateTime.setBackground(Color.WHITE);
		dateTime.setBorder(null);
		
		questQuantity.setBounds(50,80,150,30);
		questQuantity.setText("Quest Quantity");
		questQuantity.setFont(new Font("Arial",Font.BOLD,14));
		questQuantity.setForeground(Color.BLACK);
		questQuantity.setBackground(Color.WHITE);
		questQuantity.setBorder(null);
		
		questQuantityBox.setBounds(50,110,340,30);
		questQuantityBox.setBackground(Color.WHITE);
		questQuantityBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		for(int i = 1; i <= 3 ; i++) {
			questQuantityBox.addItem(i);
		}
		
		rentalType.setBounds(50,160,120,30);
		rentalType.setText("Rental Type");
		rentalType.setFont(new Font("Arial",Font.BOLD,14));
		rentalType.setForeground(Color.BLACK);
		rentalType.setBackground(Color.WHITE);
		rentalType.setBorder(null);
		
		rentalTypeBox.setBounds(50,190,340,30);
		rentalTypeBox.setBackground(Color.WHITE);
		rentalTypeBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		identificationNumber.setBounds(420,80,180,30);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial",Font.BOLD,14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		
		identificationNumberField.setBounds(420,110,340,30);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		room.setBounds(420,160,150,30);
		room.setText("Room");
		room.setFont(new Font("Arial",Font.BOLD,14));
		room.setForeground(Color.BLACK);
		room.setBackground(Color.WHITE);
		room.setBorder(null);
		
		roomBox.setBounds(420,190,340,30);
		roomBox.setBackground(Color.WHITE);
		roomBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		checkIn.setBounds(50,240,150,30);
		checkIn.setText("Check In");
		checkIn.setFont(new Font("Arial",Font.BOLD,14));
		checkIn.setForeground(Color.BLACK);
		checkIn.setBackground(Color.WHITE);
		
		hourCIn.setBounds(50,270,70,30);
		hourCIn.setBackground(Color.WHITE);
		hourCIn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		decorationThing1.setBounds(128,270,10,30);
		decorationThing1.setText(":");
		decorationThing1.setFont(new Font("Arial",Font.BOLD,18));
		decorationThing1.setForeground(Color.BLACK);
		decorationThing1.setBackground(Color.WHITE);
		
		minuteCIn.setBounds(140,270,70,30);
		minuteCIn.setBackground(Color.WHITE);
		minuteCIn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		dayCIn.setBounds(230,270,160,30);
		dayCIn.setBackground(Color.WHITE);
		dayCIn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		checkOut.setBounds(420,240,150,30);
		checkOut.setText("Check Out");
		checkOut.setFont(new Font("Arial",Font.BOLD,14));
		checkOut.setForeground(Color.BLACK);
		checkOut.setBackground(Color.WHITE);
		
		hourCOut.setBounds(420,270,70,30);
		hourCOut.setBackground(Color.WHITE);
		hourCOut.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		decorationThing2.setBounds(498,270,10,30);
		decorationThing2.setText(":");
		decorationThing2.setFont(new Font("Arial",Font.BOLD,18));
		decorationThing2.setForeground(Color.BLACK);
		decorationThing2.setBackground(Color.WHITE);
		
		minuteCOut.setBounds(510,270,70,30);
		minuteCOut.setBackground(Color.WHITE);
		minuteCOut.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		dayCOut.setBounds(600,270,160,30);
		dayCOut.setBackground(Color.WHITE);
		dayCOut.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		bookingGroup1.setBounds(45,340,140,30);
		bookingGroup1.setForeground(Color.BLACK);
		bookingGroup1.setBackground(Color.WHITE);
		bookingGroup1.setBorder(null);
		bookingGroup1.add(directBooking);
		
		bookingGroup2.setBounds(230,340,140,30);
		bookingGroup2.setForeground(Color.BLACK);
		bookingGroup2.setBackground(Color.WHITE);
		bookingGroup2.setBorder(null);
		bookingGroup2.add(advanceBooking);
		
		groupRadioButton.add(directBooking);
		groupRadioButton.add(advanceBooking);
		
		directBooking.setBounds(0,0,10,10);
		
		directBooking.setText("Direct Booking");
		directBooking.setFont(new Font("Arial",Font.BOLD,14));
		directBooking.setBackground(Color.WHITE);
		directBooking.setFocusable(false);
		directBooking.setSelected(true);
		
		advanceBooking.setBounds(0,0,10,10);
		advanceBooking.setFont(new Font("Arial",Font.BOLD,14));
		advanceBooking.setText("Advance Booking");
		advanceBooking.setBackground(Color.WHITE);
		advanceBooking.setFocusable(false);
		advanceBooking.addItemListener(itemListener);
		
		deposit.setBounds(420,340,60,30);
		deposit.setText("Deposit");
		deposit.setFont(new Font("Arial",Font.BOLD,14));
		deposit.setForeground(Color.BLACK);
		deposit.setBackground(Color.WHITE);
		
		depositField.setBounds(480,340,180,30);
		depositField.setBackground(Color.WHITE);
		depositField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		depositField.setEnabled(false);
		depositField.setBackground(new Color(240, 240, 240));
		
		currencyUnit.setBounds(665,340,150,30);
		currencyUnit.setText("VND");
		currencyUnit.setFont(new Font("Arial",Font.BOLD,16));
		currencyUnit.setForeground(Color.BLACK);
		currencyUnit.setBackground(Color.WHITE);
		registrationForm.setBorder(null);
            
		submitButton.setBounds(50, 440, 100, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}

	public JTextField getDepositField() {
		return depositField;
	}

	public JRadioButton getAdvanceBooking() {
		return advanceBooking;
	}
	
}