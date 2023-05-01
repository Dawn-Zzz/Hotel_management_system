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
//import view.editComponent.DateChooser;

public class AddGuestView extends JDialog{
	//private JDialog this = new JDialog();
	//JPanel titleSection = new JPanel();
	private JLabel guestInfor = new JLabel();
	
	private JLabel guestName = new JLabel();
	private JTextField guestNameField = new JTextField();
	
	private JLabel guestPhone = new JLabel();
	private JTextField guestPhoneField = new JTextField();
	
	private JLabel guestID = new JLabel();
	private JTextField guestIDField = new JTextField();
	
	private JLabel guestBirth = new JLabel();
	//DateChooser guestBirthChooser = new DateChooser();
	
	private JLabel guestGender = new JLabel();
	private JPanel genderGroup = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton genderMale = new JRadioButton();
	private JRadioButton genderFemale = new JRadioButton();
	
	private JButton submitButton = new Button();
	
	public AddGuestView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,1020-150-84,720-130);
		//this.setBorder(null);
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
		//this.add(guestBirthChooser);
		
//		titleSection.setBounds(30,50,250,30);
//		titleSection.setBorder(null);
//		titleSection.setBackground(Color.WHITE);
//		titleSection.add(guestInfor);
		
		guestInfor.setBounds(30,50,250,30);
		guestInfor.setText("Guest Information");
		guestInfor.setPreferredSize(new Dimension(250,30));
		guestInfor.setFont(new Font("Arial",Font.BOLD,24));
		guestInfor.setForeground(Color.BLACK);
		guestInfor.setBackground(Color.WHITE);
		guestInfor.setBorder(null);
		
		guestName.setBounds(30,100,60,30);
		guestName.setText("Name");
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestNameField.setBounds(30,130,720,30);
		guestNameField.setBackground(Color.WHITE);
		guestNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestPhone.setBounds(30,180,150,30);
		guestPhone.setText("Phone number");
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);
		
		guestPhoneField.setBounds(30,210,720,30);
		guestPhoneField.setBackground(Color.WHITE);
		guestPhoneField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestID.setBounds(30,260,150,30);
		guestID.setText("Guest ID");
		guestID.setFont(new Font("Arial",Font.BOLD,14));
		guestID.setForeground(Color.BLACK);
		guestID.setBackground(Color.WHITE);
		guestID.setBorder(null);
		
		guestIDField.setBounds(30,290,720,30);
		guestIDField.setBackground(Color.WHITE);
		guestIDField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		guestBirth.setBounds(478,340,150,30);
		guestBirth.setText("Birthday");
		guestBirth.setFont(new Font("Arial",Font.BOLD,14));
		guestBirth.setForeground(Color.BLACK);
		guestBirth.setBackground(Color.blue);
		guestBirth.setBorder(null);
		
		//guestBirthChooser.setBounds(478,380,280,220);
		guestID.setForeground(Color.BLACK);
		guestID.setBackground(Color.WHITE);
		
		guestGender.setBounds(30,340,70,30);
		guestGender.setText("Gender");
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		genderGroup.setBounds(23,370,128,30);
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
		
		submitButton.setBounds(30, 490, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}