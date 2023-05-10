package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.UserDAO;
import controller.SignupController;
import view.editComponent.Button;

public class SignupView extends JDialog{
	public SignupView() {
		this.init();
		this.setModal(true);
		this.setVisible(false);
	}
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JLabel labelLeft = new JLabel();
	private JLabel labelRight = new JLabel();
	private JLabel labelHeadLine = new JLabel();
	private JLabel labelFirstName = new JLabel();
	private JTextField firstNameTextField = new JTextField();
	private JLabel labelLasttName = new JLabel();
	private JTextField lastNameTextField = new JTextField();
	private JLabel labelUser = new JLabel();
	private JTextField userTextField = new JTextField();
	private JLabel labelPassword = new JLabel();
	private JPasswordField userPassWord = new JPasswordField();
	private JLabel labelConFirmPassword = new JLabel();
	private JPasswordField userConFirmPassWord = new JPasswordField();
	private Button signup_button = new Button();
	
	private ActionListener actionListener = new SignupController(this);
	
	public void init() {
		ImageIcon image = new ImageIcon("./Images/Logo.png"); // set icon for app
		this.setIconImage(image.getImage()); //set icon for app
		
		ImageIcon image2 = new ImageIcon("./Images/Background.jpg");
		
		signup_button.addActionListener(actionListener);
		
		leftPanel.setBounds(0, 0, 520, 720);
		
		rightPanel.setBounds(520,0, 500, 720);
		rightPanel.setLayout(null);
		
		labelLeft.setIcon(image2);
		labelLeft.setBounds(0,0,520,720);
		
		labelRight.setBackground(Color.white);
		labelRight.setOpaque(true);
		labelRight.setBounds(0,0,500,720);
		
		labelHeadLine.setText("Sign Up");
		labelHeadLine.setForeground(new Color(85,85,85));
		labelHeadLine.setFont(new Font("Arial",Font.BOLD,40));
		labelHeadLine.setBounds(50,42,160,50);
		
		labelFirstName.setText("First Name");
		labelFirstName.setForeground(new Color(85,85,85));
		labelFirstName.setFont(new Font("Arial",Font.BOLD,16));
		labelFirstName.setBounds(50,142,100,40);//set x,y position within frame as well
		
		firstNameTextField.setFont(new Font("Arial",Font.PLAIN,18));
		firstNameTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		firstNameTextField.setBounds(50,170,180,40);
		
		labelLasttName.setText("Last Name");
		labelLasttName.setForeground(new Color(85,85,85));
		labelLasttName.setFont(new Font("Arial",Font.BOLD,16));
		labelLasttName.setBounds(280,142,100,40);//set x,y position within frame as well
		
		lastNameTextField.setFont(new Font("Arial",Font.PLAIN,18));
		lastNameTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		lastNameTextField.setBounds(270,170,180,40);
		
		labelUser.setText("User Name");
		labelUser.setForeground(new Color(85,85,85));
		labelUser.setFont(new Font("Arial",Font.BOLD,16));
		labelUser.setBounds(50,242,100,40);//set x,y position within frame as well 
		
		userTextField.setFont(new Font("Arial",Font.PLAIN,18));
		userTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userTextField.setBounds(50,270,400,40);
		
		labelPassword.setText("Password");
		labelPassword.setForeground(new Color(85,85,85));
		labelPassword.setFont(new Font("Arial",Font.BOLD,16));
		labelPassword.setBounds(50,342, 100,40);//set x,y position within frame as well
		
		userPassWord.setFont(new Font("Arial",Font.PLAIN,18));
		userPassWord.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userPassWord.setBounds(50,370,400,40);
		
		labelConFirmPassword.setText("Confirm Password");
		labelConFirmPassword.setForeground(new Color(85,85,85));
		labelConFirmPassword.setFont(new Font("Arial",Font.BOLD,16));
		labelConFirmPassword.setBounds(50,442, 180,40);//set x,y position within frame as well
		
		userConFirmPassWord.setFont(new Font("Arial",Font.PLAIN,18));
		userConFirmPassWord.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userConFirmPassWord.setBounds(50,470,400,40);
		
		signup_button.setBounds(180,570,140,50);
		signup_button.setText("Sign Up");
		signup_button.setFont(new Font("Arial",Font.BOLD,20));
		signup_button.setFocusable(false);
		signup_button.setForeground(Color.white);
		signup_button.setBackground(new Color(39,162,187));
		
		this.setTitle("HOTEL MANAGEMENT");
		this.setSize(1020,720);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		rightPanel.add(labelHeadLine);
		rightPanel.add(labelFirstName);
		rightPanel.add(firstNameTextField);
		rightPanel.add(lastNameTextField);
		rightPanel.add(labelLasttName);
		rightPanel.add(labelUser);
		rightPanel.add(userTextField);
		rightPanel.add(labelPassword);
		rightPanel.add(userPassWord);
		rightPanel.add(labelConFirmPassword);
		rightPanel.add(userConFirmPassWord);
		rightPanel.add(signup_button);
		rightPanel.add(labelRight);
		
		leftPanel.add(labelLeft);
		
		this.add(leftPanel);
		this.add(rightPanel);	
	}
	
	public void signupAction() {
		String firstName = new String(firstNameTextField.getText());
		String lastName = new String(lastNameTextField.getText());
		String user = new String(userTextField.getText());
		String pass = new String(userPassWord.getPassword());
		String confirmPass = new String(userConFirmPassWord.getPassword());
		if (firstName.isEmpty() || lastName.isEmpty() || user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty())
			JOptionPane.showMessageDialog(this, "Khong duoc bo trong");
		else if (pass.length() < 8 | pass.length() > 16)
			JOptionPane.showMessageDialog(this, "Do dai mat khau phai tu 8 - 16");
		else if (user.matches(".*"+" "+".*"))
			JOptionPane.showMessageDialog(this, "Tai khoang khong duoc co khoang trang");
		else if (!pass.equals(confirmPass)) {
			JOptionPane.showMessageDialog(this, "Mat khau khong trung");
		} 
		else {
			UserDAO.getInstance().insert(user, pass);
			JOptionPane.showMessageDialog(this, "Success");
			this.dispose();
		}
	}
	
	public void resetFormSignup() {
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		userTextField.setText("");
		userPassWord.setText("");
		userConFirmPassWord.setText("");
	}
}

