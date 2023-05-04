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

import com.toedter.calendar.JDateChooser;

import view.editComponent.Button;

public class AddGuestView extends JDialog{
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	private JLabel guestInfor = new JLabel();
	
	private JLabel guestName = new JLabel();
	private JTextField guestNameField = new JTextField();
	
	private JLabel guestPhone = new JLabel();
	private JTextField guestPhoneField = new JTextField();
	
	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();
	
	private JLabel guestBirth = new JLabel();
	private JDateChooser birthDay = new JDateChooser();
	
	private JLabel guestGender = new JLabel();
	private JPanel genderGroup = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton genderMale = new JRadioButton();
	private JRadioButton genderFemale = new JRadioButton();
	
	private JButton submitButton = new Button();
	
	public AddGuestView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,0,900,600);
		this.setIconImage(image.getImage());
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
		this.add(birthDay);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);
		
		guestInfor.setBounds(50,50,250,30);
		guestInfor.setText("Guest Information:");
		guestInfor.setPreferredSize(new Dimension(250,30));
		guestInfor.setFont(new Font("Arial",Font.BOLD,24));
		guestInfor.setForeground(Color.BLACK);
		guestInfor.setBackground(Color.WHITE);
		guestInfor.setBorder(null);
		
		guestName.setBounds(50,150,60,30);
		guestName.setText("Name");
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestNameField.setBounds(50,180,350,30);
		guestNameField.setBackground(Color.WHITE);
		guestNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestPhone.setBounds(500,150,150,30);
		guestPhone.setText("Phone number");
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);
		
		guestPhoneField.setBounds(500,180,350,30);
		guestPhoneField.setBackground(Color.WHITE);
		guestPhoneField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		identificationNumber.setBounds(50,230,150,30);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial",Font.BOLD,14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		identificationNumber.setBorder(null);
		
		identificationNumberField.setBounds(50,260,350,30);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		guestBirth.setBounds(500,230,350,30);
		guestBirth.setText("Birthday");
		guestBirth.setFont(new Font("Arial",Font.BOLD,14));
		guestBirth.setForeground(Color.BLACK);
		guestBirth.setBackground(Color.blue);
		guestBirth.setBorder(null);
		
		birthDay.setBounds(500,260,350,30);
		birthDay.setBackground(Color.WHITE);
		birthDay.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestGender.setBounds(50,310,70,30);
		guestGender.setText("Gender");
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		genderGroup.setBounds(50,340,128,30);
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
		
		submitButton.setBounds(50, 440, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}