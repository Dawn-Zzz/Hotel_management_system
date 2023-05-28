package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ServiceController;
import view.editComponent.Button;

public class AddServiceView extends JDialog{
	
	private JLabel serviceInfor = new JLabel();

	private JLabel guestInforTitle = new JLabel("Guest Information");
	private JLabel guestName = new JLabel();
	
	private JLabel guestPhone = new JLabel();
	
	private JLabel roomNumber = new JLabel();
	
	private JLabel serviceListTitle = new JLabel();
	private JLabel service1 = new JLabel("Nhà Hàng (300K)");
	private JLabel service2 = new JLabel("Spa (200K)");
	private JLabel service3 = new JLabel("Hồ Bơi (50K)");
	private JLabel service4 = new JLabel("Gym (50K)");
	private JLabel service5 = new JLabel("Giặt, ủi (100K)");
	
	private JTextField serviceField1 = new JTextField("0");
	private JTextField serviceField2 = new JTextField("0");
	private JTextField serviceField3 = new JTextField("0");
	private JTextField serviceField4 = new JTextField("0");
	private JTextField serviceField5 = new JTextField("0");
	
	private JButton submitButton = new Button();
	
	private String numberCode;
	
	private ActionListener actionListener = new ServiceController(this);
	
	public AddServiceView(String numberCode) {
		this.numberCode = numberCode;
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,730,470);
		this.setLayout(null);
		this.add(serviceInfor);
		this.add(guestInforTitle);
		this.add(guestName);
		this.add(guestPhone);
		this.add(roomNumber);
		this.add(serviceListTitle);
		this.add(service1);
		this.add(serviceField1);
		this.add(service2);
		this.add(serviceField2);
		this.add(service3);
		this.add(serviceField3);
		this.add(service4);
		this.add(serviceField4);
		this.add(service5);
		this.add(serviceField5);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);
		
		serviceInfor.setBounds(50,20,250,30);
		serviceInfor.setText("Service Information");
		serviceInfor.setPreferredSize(new Dimension(250,30));
		serviceInfor.setFont(new Font("Arial",Font.BOLD,24));
		serviceInfor.setForeground(Color.BLACK);
		serviceInfor.setBackground(Color.WHITE);
		serviceInfor.setBorder(null);
		
		guestInforTitle.setBounds(50,80,250,15);
		guestInforTitle.setFont(new Font("Arial",Font.BOLD,16));
		
		guestName.setBounds(50,120,250,15);
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		roomNumber.setBounds(410,120,250,15);
		roomNumber.setFont(new Font("Arial",Font.BOLD,14));
		roomNumber.setForeground(Color.BLACK);
		roomNumber.setBackground(Color.WHITE);
		roomNumber.setBorder(null);

		serviceListTitle.setBounds(50,170,150,15);
		serviceListTitle.setText("Service List:");
		serviceListTitle.setFont(new Font("Arial",Font.BOLD,16));
		serviceListTitle.setForeground(Color.BLACK);
		serviceListTitle.setBackground(Color.blue);
		serviceListTitle.setBorder(null);
		
		service1.setBounds(50,210,120,15);
		service1.setFont(new Font("Arial",Font.BOLD,14));
			
		serviceField1.setBounds(200,207,100,23);
		serviceField1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(220,220,220)));
		
		service2.setBounds(50,240,100,15);
		service2.setFont(new Font("Arial",Font.BOLD,14));
		
		serviceField2.setBounds(200,237,100,23);
		serviceField2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(220,220,220)));
		
		service3.setBounds(50,270,100,15);
		service3.setFont(new Font("Arial",Font.BOLD,14));
		
		serviceField3.setBounds(200,267,100,23);
		serviceField3.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(220,220,220)));
		
		service4.setBounds(410,210,100,15);
		service4.setFont(new Font("Arial",Font.BOLD,14));
		
		serviceField4.setBounds(560,207,100,23);
		serviceField4.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(220,220,220)));
		
		service5.setBounds(410,240,100,15);
		service5.setFont(new Font("Arial",Font.BOLD,14));
		
		serviceField5.setBounds(560,237,100,23);
		serviceField5.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(220,220,220)));
		
		submitButton.setBounds(250, 350, 200, 40);
		submitButton.setText("Trả phòng");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setFocusable(false);
		submitButton.addActionListener(actionListener);
	}
	
	public JLabel getGuestName() {
		return guestName;
	}

	public JLabel getGuestPhone() {
		return guestPhone;
	}

	public JLabel getRoomNumber() {
		return roomNumber;
	}

	public JTextField getServiceField1() {
		return serviceField1;
	}

	public JTextField getServiceField2() {
		return serviceField2;
	}

	public JTextField getServiceField3() {
		return serviceField3;
	}

	public JTextField getServiceField4() {
		return serviceField4;
	}

	public JTextField getServiceField5() {
		return serviceField5;
	}

	public String getNumberCode() {
		return numberCode;
	}
	
//	public JDateChooser getBirthDay() {
//		return birthDay;
//	}

//	public void addServiceAction () {
//		String id = roomNumberField.getText();
//		String name = serviceNameField.getText();
//		String phoneNumber = servicePhoneField.getText();
//		boolean vip = vipCheckBox.isSelected();
//		Date birth = null;
//		if (birthDay.getDate() != null) { 
//			birth = new Date(birthDay.getDate().getTime());
//		}
//		
//		if (id.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || birth == null) 
//			JOptionPane.showMessageDialog(this, "Không được bỏ trống");
//		else if (!id.matches("\\d{12}")) 
//	        JOptionPane.showMessageDialog(this, "ID phải có đúng 12 số");
//		else if (!phoneNumber.matches("\\d{10}")) 
//	        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
//		else if (ServiceDAO.getInstance().getServiceById(id) != null && roomNumberField.isEnabled()==true) 
//	        JOptionPane.showMessageDialog(this, "ID đã tồn tại");
//		else if (roomNumberField.isEnabled()==false) {
//			if (vip)
//				ServiceDAO.getInstance().updateService(id, name, birth, phoneNumber,"Vip");
//			else
//				ServiceDAO.getInstance().updateService(id, name, birth, phoneNumber,null);
//			JOptionPane.showMessageDialog(this, "Sửa thành công");
//			this.dispose();
//		}
//		else {
//	        Calendar dob = Calendar.getInstance();
//	        dob.setTime(birth);
//
//	        Calendar now = Calendar.getInstance();
//	        int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
//	        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
//	            age--;
//	        }
//	        if (age < 18) {
//	            JOptionPane.showMessageDialog(this, "Khách hàng phải đủ 18 tuổi", "Lỗi", JOptionPane.ERROR_MESSAGE);
//	        } else {
//	        	if (vip)
//	            	ServiceDAO.getInstance().insert(id, name, birth, phoneNumber,"Vip");
//	        	else
//	            	ServiceDAO.getInstance().insert(id, name, birth, phoneNumber,null);
//	            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
//	            this.dispose();
//	        }
//		}
//	}
}