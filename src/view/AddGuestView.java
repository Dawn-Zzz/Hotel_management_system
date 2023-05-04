package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import view.editComponent.Button;

public class AddGuestView extends JDialog{
	private JLabel guestInfor = new JLabel();
	
	private JLabel guestName = new JLabel();
	private JTextField guestNameField = new JTextField();
	
	private JLabel guestPhone = new JLabel();
	private JTextField guestPhoneField = new JTextField();
	
	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();
	
	private JLabel guestBirth = new JLabel();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	JFormattedTextField dateField = new JFormattedTextField(format);
	
	private JLabel guestGender = new JLabel();
	private JPanel genderGroup = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton genderMale = new JRadioButton();
	private JRadioButton genderFemale = new JRadioButton();
	
	private JButton submitButton = new Button();
	
	public AddGuestView() {
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,1020,720);
		this.setLayout(null);
		this.add(guestInfor);
		this.add(guestName);
		this.add(guestNameField);
		this.add(guestPhone);
		this.add(guestPhoneField);
		this.add(identificationNumber);
		this.add(identificationNumberField);
		this.add(guestGender);
		this.add(genderGroup);
		this.add(guestBirth);
		this.add(dateField);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
		guestInfor.setBounds(50,50,250,30);
		guestInfor.setText("Guest Information");
		guestInfor.setPreferredSize(new Dimension(250,30));
		guestInfor.setFont(new Font("Arial",Font.BOLD,24));
		guestInfor.setForeground(Color.BLACK);
		guestInfor.setBackground(Color.WHITE);
		guestInfor.setBorder(null);
		
		guestName.setBounds(50,100,60,30);
		guestName.setText("Name");
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestNameField.setBounds(50,130,910,30);
		guestNameField.setBackground(Color.WHITE);
		guestNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestPhone.setBounds(50,180,150,30);
		guestPhone.setText("Phone number");
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);
		
		guestPhoneField.setBounds(50,210,910,30);
		guestPhoneField.setBackground(Color.WHITE);
		guestPhoneField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		identificationNumber.setBounds(50,260,150,30);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial",Font.BOLD,14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		identificationNumber.setBorder(null);
		
		identificationNumberField.setBounds(50,290,910,30);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		guestBirth.setBounds(50,350,150,30);
		guestBirth.setText("Birthday");
		guestBirth.setFont(new Font("Arial",Font.BOLD,14));
		guestBirth.setForeground(Color.BLACK);
		guestBirth.setBackground(Color.blue);
		guestBirth.setBorder(null);
		
		dateField.setBounds(50,380,460,30);
		dateField.setBackground(Color.WHITE);
		dateField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestGender.setBounds(50,420,70,30);
		guestGender.setText("Gender");
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		genderGroup.setBounds(50,450,128,30);
		genderGroup.setForeground(Color.BLACK);
		genderGroup.setBackground(Color.WHITE);
		genderGroup.setBorder(null);
		genderGroup.add(genderMale);
		genderGroup.add(genderFemale);
		
		groupRadioButton.add(genderMale);
		groupRadioButton.add(genderFemale);
		
		genderMale.setBounds(0,0,10,30);
		genderMale.setText("Male");
		genderMale.setBackground(Color.WHITE);
		genderMale.setFocusable(false);
		
		genderFemale.setBounds(0,80,10,30);
		genderFemale.setText("Female");
		genderFemale.setBackground(Color.WHITE);
		genderFemale.setFocusable(false);
		
		submitButton.setBounds(50, 600, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}