package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.UserDAO;
import controller.LoginController;
import model.User;

public class LoginView extends JFrame{
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JLabel labelLeft = new JLabel();
	private JLabel labelRight = new JLabel();
	private JLabel labelHeadLine = new JLabel();
	private JLabel labelUser = new JLabel();
	private JTextField userTextField = new JTextField();
	private JLabel labelPassword = new JLabel();
	private JPasswordField userPassWord = new JPasswordField();
	private JButton login_button = new JButton();// bien toan cuc
	private JLabel labelBotLine = new JLabel();
	private JButton signup_button = new JButton();
	
	private ActionListener actionListener = new LoginController(this);
	
	public LoginView() {
		this.init();
		this.setVisible(true);
	}
	
	public void init() {
		ImageIcon image = new ImageIcon("./Images/Logo.png"); // set icon for app
		this.setIconImage(image.getImage()); //set icon for app
		
		ImageIcon image2 = new ImageIcon("./Images/Background.jpg");
		
		login_button.addActionListener(actionListener);
		signup_button.addActionListener(actionListener);
		
		leftPanel.setBounds(0, 0, 520, 720);
		
		rightPanel.setBounds(520,0, 500, 720);
		rightPanel.setLayout(null);
		
		labelLeft.setIcon(image2);
		labelLeft.setBounds(0,0,520,720);
		
		labelRight.setBackground(Color.white);
		labelRight.setOpaque(true);
		labelRight.setBounds(0,0,500,720);
		
		labelHeadLine.setText("Log In");
		labelHeadLine.setForeground(new Color(85,85,85));
		labelHeadLine.setFont(new Font("Arial",Font.BOLD,40));
		labelHeadLine.setBounds(50,120,150,50);
		
		labelUser.setText("User Name");
		labelUser.setForeground(new Color(85,85,85));
		labelUser.setFont(new Font("Arial",Font.BOLD,16));
		labelUser.setBounds(50,220,100,40);//set x,y position within frame as well 
		
		userTextField.setFont(new Font("Arial",Font.PLAIN,16));
		userTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userTextField.setBounds(50,248,400,40);
		
		labelPassword.setText("Password");
		labelPassword.setForeground(new Color(85,85,85));
		labelPassword.setFont(new Font("Arial",Font.BOLD,16));
		labelPassword.setBounds(50,320, 100,40);//set x,y position within frame as well
		
		userPassWord.setFont(new Font("Arial",Font.PLAIN,16));
		userPassWord.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userPassWord.setBounds(50,348,400,40);
		
		login_button.setBounds(180,448,140,40);
		login_button.setText("Log In");
		login_button.setFont(new Font("Arial",Font.BOLD,20));
		login_button.setFocusable(false);
		login_button.setForeground(Color.white);
		login_button.setBackground(new Color(39,162,187));
		login_button.setBorder(BorderFactory.createEmptyBorder());
		
		labelBotLine.setText("Don't have an account?");
		labelBotLine.setForeground(new Color(85,85,85));
		labelBotLine.setFont(new Font("Arial",Font.BOLD,12));
		labelBotLine.setBounds(50,576,140,100);
		
		signup_button.setBounds(200,616,60,20);
		signup_button.setText("Sign Up");
		signup_button.setFont(new Font("Arial",Font.BOLD,12));
		signup_button.setFocusable(false);
		signup_button.setForeground(new Color(39,162,187));
		signup_button.setBackground(Color.white);
		signup_button.setBorder(BorderFactory.createEmptyBorder());
		
		this.setTitle("HOTEL MANAGEMENT");
		this.setSize(1020,720);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		rightPanel.add(labelHeadLine);
		rightPanel.add(labelUser);
		rightPanel.add(userTextField);
		rightPanel.add(labelPassword);
		rightPanel.add(userPassWord);
		rightPanel.add(login_button);
		rightPanel.add(labelBotLine);
		rightPanel.add(signup_button);
		rightPanel.add(labelRight);
		
		leftPanel.add(labelLeft);
		
		this.add(leftPanel);
		this.add(rightPanel);
	}
	
	public void loginAction() {
		ArrayList<User> arr = UserDAO.getInstance().selectAll();
		String user = new String(userTextField.getText());
		String pass = new String(userPassWord.getPassword());
		boolean check = false;
		for (User userModel : arr) 
			if (user.equals(userModel.getUserName()) && pass.equals(userModel.getPassword())) {
				check = true;
				break;
			}
		if (check) 
			JOptionPane.showMessageDialog(this, "Success");
		else 
			JOptionPane.showMessageDialog(this, "Fail");
	}
}
