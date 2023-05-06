package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.editComponent.Button;
import view.editComponent.Table;

public class BookRoomView extends JDialog{
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	private JLabel registrationForm = new JLabel();
	
	private JLabel dateTime = new JLabel();
	
	private JLabel questQuantity = new JLabel();
	private JComboBox<Integer> questQuantityBox = new JComboBox<>();
	
	private JLabel rentalType = new JLabel();
	String RentalType[] = {"", "Direct Booking", "Book in Advance"}; 
	private JComboBox<String> rentalTypeBox = new JComboBox<>(RentalType);
	
	private JLabel room = new JLabel();
	private JComboBox<Integer> roomBox = new JComboBox<>();
	
	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();
	
	private JLabel checkIn = new JLabel();
	private JTextField hourCIn = new JTextField();
	private JTextField dayCIn = new JTextField();
	
	private JLabel checkOut = new JLabel();
	private JTextField hourCOut = new JTextField();
	private JTextField dayCOut = new JTextField();
	
	private JLabel deposit = new JLabel();
	private JRadioButton depositTick = new JRadioButton();
	private JTextField depositfield = new JTextField();
	private JLabel currencyUnit = new JLabel();
	
	private JButton submitButton = new Button();
	
	public BookRoomView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,900,600);
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
		this.add(dayCIn);
		this.add(checkOut);
		this.add(hourCOut);
		this.add(dayCOut);
		this.add(deposit);
		this.add(depositTick);
		this.add(depositfield);
		this.add(currencyUnit);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);
		
		registrationForm.setBounds(50,50,250,30);
		registrationForm.setText("Registration Form");
		registrationForm.setPreferredSize(new Dimension(250,30));
		registrationForm.setFont(new Font("Arial",Font.BOLD,24));
		registrationForm.setForeground(Color.BLACK);
		registrationForm.setBackground(Color.WHITE);
		registrationForm.setBorder(null);
		
		dateTime.setBounds(800,50,250,30);
		dateTime.setText("5/2/2023");
		dateTime.setPreferredSize(new Dimension(250,30));
		dateTime.setFont(new Font("Arial",Font.BOLD,14));
		dateTime.setForeground(Color.BLACK);
		dateTime.setBackground(Color.WHITE);
		dateTime.setBorder(null);
		
		questQuantity.setBounds(50,100,150,30);
		questQuantity.setText("Quest Quantity");
		questQuantity.setFont(new Font("Arial",Font.BOLD,14));
		questQuantity.setForeground(Color.BLACK);
		questQuantity.setBackground(Color.WHITE);
		questQuantity.setBorder(null);
		
		questQuantityBox.setBounds(50,130,150,30);
		questQuantityBox.setBackground(Color.WHITE);
		questQuantityBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		for(int i = 1; i <= 3 ; i++) {
			questQuantityBox.addItem(i);
		}
		
		rentalType.setBounds(50,180,120,30);
		rentalType.setText("Rental Type");
		rentalType.setFont(new Font("Arial",Font.BOLD,14));
		rentalType.setForeground(Color.BLACK);
		rentalType.setBackground(Color.WHITE);
		rentalType.setBorder(null);
		
		rentalTypeBox.setBounds(50,210,150,30);
		rentalTypeBox.setBackground(Color.WHITE);
		rentalTypeBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		room.setBounds(500,100,120,30);
		room.setText("Room");
		room.setFont(new Font("Arial",Font.BOLD,14));
		room.setForeground(Color.BLACK);
		room.setBackground(Color.WHITE);
		room.setBorder(null);
		
		roomBox.setBounds(500,130,150,30);
		roomBox.setBackground(Color.WHITE);
		roomBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		identificationNumber.setBounds(500,180,150,30);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial",Font.BOLD,14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		
		identificationNumberField.setBounds(500,210,150,30);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		checkIn.setBounds(50,260,150,30);
		checkIn.setText("Check In");
		checkIn.setFont(new Font("Arial",Font.BOLD,14));
		checkIn.setForeground(Color.BLACK);
		checkIn.setBackground(Color.WHITE);
		
		hourCIn.setBounds(50,290,100,30);
		hourCIn.setBackground(Color.WHITE);
		hourCIn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		dayCIn.setBounds(200,290,130,30);
		dayCIn.setBackground(Color.WHITE);
		dayCIn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		checkOut.setBounds(500,260,150,30);
		checkOut.setText("Check Out");
		checkOut.setFont(new Font("Arial",Font.BOLD,14));
		checkOut.setForeground(Color.BLACK);
		checkOut.setBackground(Color.WHITE);
		
		hourCOut.setBounds(500,290,100,30);
		hourCOut.setBackground(Color.WHITE);
		hourCOut.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		dayCOut.setBounds(650,290,130,30);
		dayCOut.setBackground(Color.WHITE);
		dayCOut.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		deposit.setBounds(50,340,80,30);
		deposit.setText("Deposit");
		deposit.setFont(new Font("Arial",Font.BOLD,14));
		deposit.setForeground(Color.BLACK);
		deposit.setBackground(Color.WHITE);
		
		depositTick.setBounds(120,340,20,30);
		depositTick.setBackground(Color.WHITE);
		depositTick.setFocusable(false);
		
		depositfield.setBounds(50,370,150,30);
		depositfield.setBackground(Color.WHITE);
		depositfield.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		currencyUnit.setBounds(205,370,150,30);
		currencyUnit.setText("VND");
		currencyUnit.setFont(new Font("Arial",Font.BOLD,16));
		currencyUnit.setForeground(Color.BLACK);
		currencyUnit.setBackground(Color.WHITE);
		registrationForm.setBorder(null);
            
		submitButton.setBounds(50, 470, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}