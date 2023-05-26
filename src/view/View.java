package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.NavController;
import model.AccessPersonnel;
import model.Staff;
import view.editComponent.Button;

public class View extends JFrame{	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private String name;
	private JPanel leftBar = new JPanel();
	private JPanel otherBar = new JPanel();
	private JButton logOutButton = new Button();

	private JButton dashBoardButton = new JButton("1");
	private JButton guestButton = new JButton("2");
	private JButton roomButton = new JButton("3");
	private JButton serviceButton = new JButton("4");
	private JButton billButton = new JButton("5");

	private JButton adminButton = new JButton("6");
	private ActionListener actionListener= new NavController(this);;

	private JLabel staffName = new JLabel();

	private static volatile View instance;

    public static View getInstance() {
        if (instance == null) {
            synchronized (View.class) {
                if (instance == null) {
                    instance = new View();
                }
            }
        }
        return instance;
    }

    private View() {
        initView();
    }

	public void initView( ) {
		ImageIcon image = new ImageIcon("./Images/Logo.png");
		this.setIconImage(image.getImage());
		otherSection();
		leftBarSection();
		
		this.setTitle("HOTEL MANAGEMENT");
		this.setSize(1020, 720);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(leftBar);
		this.add(otherBar);
	}
	
	public JPanel getOtherBar() {
		return otherBar;
	}

	public void setOtherBar(JPanel otherBar) {
		this.otherBar = otherBar;
	}
	
	public JButton getLogOutButton() {
		return logOutButton;
	}
	public void leftBarSection() {
		//Left bar section

		leftBar.setBounds(0,0,64,720);
		leftBar.setLayout(null);
		leftBar.setBackground(new Color(39,162,187));
		leftBar.add(dashBoardButton);
		leftBar.add(guestButton);
		leftBar.add(roomButton);
		leftBar.add(serviceButton);
		leftBar.add(billButton);
		leftBar.add(adminButton);

		dashBoardButton.setBounds(0, 30, 64, 40);
		dashBoardButton.setLayout(null);
		dashBoardButton.setBorder(null);
		dashBoardButton.setBackground(new Color(39,162,187));
		dashBoardButton.setForeground(new Color(39,162,187));
		dashBoardButton.setIcon(new ImageIcon("./Images/stats1.png"));
		dashBoardButton.setHorizontalTextPosition(0);
		dashBoardButton.setFont(new Font("Arial", Font.PLAIN, 0));
		dashBoardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dashBoardButton.setFocusable(false);
		dashBoardButton.addActionListener(actionListener);
		
		guestButton.setBounds(0, 90, 64, 40);
		guestButton.setLayout(null);
		guestButton.setBorder(null);
		guestButton.setBackground(new Color(39,162,187));
		guestButton.setForeground(new Color(39,162,187));
		guestButton.setIcon(new ImageIcon("./Images/users.png"));
		guestButton.setHorizontalTextPosition(0);
		guestButton.setFont(new Font("Arial", Font.PLAIN, 0));
		guestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		guestButton.setFocusable(false);
		guestButton.addActionListener(actionListener);
		
		roomButton.setBounds(0, 150, 64, 40);
		roomButton.setLayout(null);
		roomButton.setBorder(null);
		roomButton.setBackground(new Color(39,162,187));
		roomButton.setForeground(new Color(39,162,187));
		roomButton.setIcon(new ImageIcon("./Images/bed2.png"));
		roomButton.setHorizontalTextPosition(0);
		roomButton.setFont(new Font("Arial", Font.PLAIN, 0));
		roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		roomButton.setFocusable(false);
		roomButton.addActionListener(actionListener);
		
		serviceButton.setBounds(0, 210, 64, 40);
		serviceButton.setLayout(null);
		serviceButton.setBorder(null);
		serviceButton.setBackground(new Color(39,162,187));
		serviceButton.setForeground(new Color(39,162,187));
		serviceButton.setIcon(new ImageIcon("./Images/service2.png"));
		serviceButton.setHorizontalTextPosition(0);
		serviceButton.setFont(new Font("Arial", Font.PLAIN, 0));
		serviceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		serviceButton.setFocusable(false);
		serviceButton.addActionListener(actionListener);
		
		billButton.setBounds(0, 270, 64, 40);
		billButton.setLayout(null);
		billButton.setBorder(null);
		billButton.setBackground(new Color(39,162,187));
		billButton.setForeground(new Color(39,162,187));
		billButton.setIcon(new ImageIcon("./Images/bill2.png"));
		billButton.setHorizontalTextPosition(0);
		billButton.setFont(new Font("Arial", Font.PLAIN, 0));
		billButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		billButton.setFocusable(false);
		billButton.addActionListener(actionListener);
		
		adminButton.setBounds(0, 330, 64, 40);
		adminButton.setLayout(null);
		adminButton.setBorder(null);
		adminButton.setBackground(new Color(39,162,187));
		adminButton.setForeground(new Color(39,162,187));
		adminButton.setIcon(new ImageIcon("./Images/admin.png"));
		adminButton.setHorizontalTextPosition(0);
		adminButton.setFont(new Font("Arial", Font.PLAIN, 0));
		adminButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		adminButton.setFocusable(false);
		adminButton.addActionListener(actionListener);
//		adminButton.setVisible(false);
	}
	
	public void otherSection() {
		otherBar.setVisible(true);
		otherBar.setLayout(null);
		otherBar.setBounds(64,0,1020-84,720);
		
		logOutButton.setText("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBounds(1020-174,23,80,40);
		logOutButton.setLayout(null);
		logOutButton.setBackground(new Color(39,162,187));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOutButton.setFocusable(false);
		logOutButton.addActionListener(actionListener);

		staffName.setBounds(750,23,100,40);
		staffName.setText(AccessPersonnel.getInstance().getStaff().getName());
		staffName.setFont(new Font("Arial", Font.BOLD, 12));
		staffName.setForeground(new Color(170,170,170));
		
		otherBar.add(logOutButton,0);
		otherBar.add(staffName,1);
	}
	public void updateLoginInfo() {
	    // cập nhật tên người dùng hiển thị trên cửa sổ màn hình chính
	    staffName.setText(AccessPersonnel.getInstance().getStaff().getName());
	}
	public JButton getAdminButton() {
		return this.adminButton;
	}
}