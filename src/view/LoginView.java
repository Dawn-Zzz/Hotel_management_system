package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginListener;

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
	private MouseListener mouseListener = new LoginListener(this);
	
	private boolean checkSignUp_button = false;
	
	public LoginView() {
		this.Init();
		this.setVisible(true);
	}
	
	public void Init() {
		ImageIcon image = new ImageIcon("./Images/Logo.png"); // set icon for app
		this.setIconImage(image.getImage()); //set icon for app
		
		ImageIcon image2 = new ImageIcon("./Images/Background.jpg");
		
		login_button.addMouseListener(mouseListener);
		signup_button.addMouseListener(mouseListener);
		
		leftPanel.setBounds(0, 0, 600, 800);
		
		rightPanel.setBounds(600,0, 500, 800);
		rightPanel.setLayout(null);
		
		labelLeft.setIcon(image2);
		labelLeft.setBounds(0,0,600,800);
		
		labelRight.setBackground(Color.white);
		labelRight.setOpaque(true);
		labelRight.setBounds(0,0,500,800);
		
		labelHeadLine.setText("Log In");
		labelHeadLine.setForeground(new Color(85,85,85));
		labelHeadLine.setFont(new Font("Arial",Font.BOLD,40));
		labelHeadLine.setBounds(50,160,150,50);
		
		labelUser.setText("User Name");
		labelUser.setForeground(new Color(85,85,85));
		labelUser.setFont(new Font("Arial",Font.BOLD,16));
		labelUser.setBounds(50,292,100,40);//set x,y position within frame as well 
		
		userTextField.setFont(new Font("Arial",Font.PLAIN,16));
		userTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userTextField.setBounds(50,320,400,40);
		
		labelPassword.setText("Password");
		labelPassword.setForeground(new Color(85,85,85));
		labelPassword.setFont(new Font("Arial",Font.BOLD,16));
		labelPassword.setBounds(50,392, 100,40);//set x,y position within frame as well
		
		userPassWord.setFont(new Font("Arial",Font.PLAIN,16));
		userPassWord.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(85,85,85)));
		userPassWord.setBounds(50,420,400,40);
		
		login_button.setBounds(180,520,140,40);
		login_button.setText("Log In");
		login_button.setFont(new Font("Arial",Font.BOLD,20));
		login_button.setFocusable(false);
		login_button.setForeground(Color.white);
		login_button.setBackground(new Color(39,162,187));
		login_button.setBorder(BorderFactory.createEmptyBorder());
		
		labelBotLine.setText("Don't have an account?");
		labelBotLine.setForeground(new Color(85,85,85));
		labelBotLine.setFont(new Font("Arial",Font.BOLD,12));
		labelBotLine.setBounds(50,650,140,100);
		
		signup_button.setBounds(200,690,60,20);
		signup_button.setText("Sign Up");
		signup_button.setFont(new Font("Arial",Font.BOLD,12));
		signup_button.setFocusable(false);
		signup_button.setForeground(new Color(39,162,187));
		signup_button.setBackground(Color.white);
		signup_button.setBorder(BorderFactory.createEmptyBorder());
		
		this.setTitle("HOTEL MANAGEMENT");
		this.setSize(1100,800);
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
	
//	public UserModel getUser() {
//		String user = new String(userTextField.getText());
//		String pass = new String(userPassWord.getPassword());
//		return new UserModel(user, pass);
//	} 
	public void LoginAction() {
		String user = new String(userTextField.getText());
		String pass = new String(userPassWord.getPassword());
		if (user.equals("admin") && pass.equals("admin"))
			JOptionPane.showMessageDialog(this, "Success");
		else 
			JOptionPane.showMessageDialog(this, "Fail");
	}

	public JButton getLogin_button() {
		return login_button;
	}

	public void setLogin_button(JButton login_button) {
		this.login_button = login_button;
	}

	public JButton getSignup_button() {
		return signup_button;
	}

	public void setSignup_button(JButton signup_button) {
		this.signup_button = signup_button;
	}

	public boolean isCheckSignUp_button() {
		return checkSignUp_button;
	}

	public void setCheckSignUp_button(boolean checkSignUp_button) {
		this.checkSignUp_button = checkSignUp_button;
	}
}
