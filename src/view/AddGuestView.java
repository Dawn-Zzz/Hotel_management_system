package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	
	private JLabel guestID = new JLabel();
	private JTextField guestIDField = new JTextField();
	
	private JLabel guestBirth = new JLabel();;
	
	private JLabel guestGender = new JLabel();
	private JPanel genderGroup = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton genderMale = new JRadioButton();
	private JRadioButton genderFemale = new JRadioButton();
	
	private JButton submitButton = new Button();
	
	public AddGuestView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,1020,720);
		this.setLayout(null);
		this.add(guestInfor);
		this.add(guestName);
		this.add(guestNameField);
		this.add(guestPhone);
		this.add(guestPhoneField);
		this.add(guestID);
		this.add(guestIDField);
		this.add(guestGender);
		this.add(genderGroup);
		this.add(guestBirth);
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
		
		guestID.setBounds(50,260,150,30);
		guestID.setText("Guest ID");
		guestID.setFont(new Font("Arial",Font.BOLD,14));
		guestID.setForeground(Color.BLACK);
		guestID.setBackground(Color.WHITE);
		guestID.setBorder(null);
		
		guestIDField.setBounds(50,290,910,30);
		guestIDField.setBackground(Color.WHITE);
		guestIDField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		guestBirth.setBounds(478,340,150,30);
		guestBirth.setText("Birthday");
		guestBirth.setFont(new Font("Arial",Font.BOLD,14));
		guestBirth.setForeground(Color.BLACK);
		guestBirth.setBackground(Color.blue);
		guestBirth.setBorder(null);
		
		guestID.setForeground(Color.BLACK);
		guestID.setBackground(Color.WHITE);
		
		guestGender.setBounds(50,340,70,30);
		guestGender.setText("Gender");
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		genderGroup.setBounds(50,370,128,30);
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
		
		genderFemale.setBounds(0,80,10,30);
		genderFemale.setText("Female");
		genderFemale.setBackground(Color.WHITE);
		
		submitButton.setBounds(50, 600, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}