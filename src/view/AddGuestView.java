package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Handler;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAO.GuestDAO;
import controller.AddGuestController;
import view.editComponent.Button;

public class AddGuestView extends JDialog{
	private GuestView parentView;
	
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
	
	private ActionListener actionListener = new AddGuestController(this);
	
	public AddGuestView(GuestView parentView) {
		this.parentView = parentView;
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,800,520);
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
		
		guestInfor.setBounds(50,20,220,30);
		guestInfor.setText("Guest Information");
		guestInfor.setPreferredSize(new Dimension(250,30));
		guestInfor.setFont(new Font("Arial",Font.BOLD,24));
		guestInfor.setForeground(Color.BLACK);
		guestInfor.setBackground(Color.WHITE);
		guestInfor.setBorder(null);
		
		guestName.setBounds(50,100,45,14);
		guestName.setText("Name");
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		guestNameField.setBounds(50,125,330,40);
		guestNameField.setBackground(Color.WHITE);
		guestNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestPhone.setBounds(410,100,150,14);
		guestPhone.setText("Phone number");
		guestPhone.setFont(new Font("Arial",Font.BOLD,14));
		guestPhone.setForeground(Color.BLACK);
		guestPhone.setBackground(Color.WHITE);
		guestPhone.setBorder(null);
		
		guestPhoneField.setBounds(410,125,330,40);
		guestPhoneField.setBackground(Color.WHITE);
		guestPhoneField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		identificationNumber.setBounds(50,215,150,14);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial",Font.BOLD,14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		identificationNumber.setBorder(null);
		
		identificationNumberField.setBounds(50,240,330,40);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		guestBirth.setBounds(410,215,150,14);
		guestBirth.setText("Birthday");
		guestBirth.setFont(new Font("Arial",Font.BOLD,14));
		guestBirth.setForeground(Color.BLACK);
		guestBirth.setBackground(Color.blue);
		guestBirth.setBorder(null);
		
		birthDay.setBounds(410,240,330,40);
		birthDay.setBackground(Color.WHITE);
		birthDay.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		guestGender.setBounds(50,310,70,30);
		guestGender.setText("Gender");
		guestGender.setFont(new Font("Arial",Font.BOLD,14));
		guestGender.setForeground(Color.BLACK);
		guestGender.setBackground(Color.WHITE);
		guestGender.setBorder(null);
		
		genderGroup.setBounds(45,330,128,30);
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
		
		submitButton.setBounds(50, 400, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setFocusable(false);
		submitButton.addActionListener(actionListener);
	}
	
	public GuestView getParentView() {
		return parentView;
	}

	public void addGuestAction () {
		String id = identificationNumberField.getText();
		String name = guestNameField.getText();
		String phoneNumber = guestPhoneField.getText();
		Date birth = null;
		if (birthDay.getDate() != null) { 
			birth = new Date(birthDay.getDate().getTime());
		}
		
		if (id.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || birth == null) 
			JOptionPane.showMessageDialog(this, "Không được bỏ trống");
		else if (!id.matches("\\d{12}")) 
	        JOptionPane.showMessageDialog(this, "ID phải có đúng 12 số");
		else if (GuestDAO.getInstance().getGuestById(id) != null) 
	        JOptionPane.showMessageDialog(this, "ID đã tồn tại");
		else if (!phoneNumber.matches("\\d{10}")) 
	        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		else {
	        Calendar dob = Calendar.getInstance();
	        dob.setTime(birth);

	        Calendar now = Calendar.getInstance();
	        int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
	        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
	            age--;
	        }
	        if (age < 18) {
	            JOptionPane.showMessageDialog(this, "Khách hàng phải đủ 18 tuổi", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        } else {
	            GuestDAO.getInstance().insert(id, name, birth, phoneNumber);
	        }
		}
	}
}