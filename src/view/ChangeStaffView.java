package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAO.StaffDAO;
import controller.ChangeStaffController;
import view.editComponent.Button;
import view.editComponent.Combobox;

public class ChangeStaffView extends JDialog {
	private static ChangeStaffView instance;

	public static ChangeStaffView getInstance() {
		if (instance == null) {
			instance = new ChangeStaffView();
		}
		return instance;
	}

	private AdminView parentView = AdminView.getInstance();

	public ChangeStaffView() {
//		this.parentView = parentView;
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0, 85, 800, 520);
		this.setLayout(null);
		this.add(staffInfor);
		this.add(staffNameTitle);
		this.add(staffName);
		this.add(staffPhone);
		this.add(staffPhoneField);
		this.add(identificationNumber);
		this.add(identificationNumberField);
//		this.add(guestGender);
//		this.add(genderGroup);
//		this.add(genderCheckBox);
		this.add(staffRoleList);
		this.add(staffRole);
		this.add(staffBirth);
		this.add(birthDay);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);

		staffInfor.setBounds(50, 20, 220, 30);
		staffInfor.setText("Staff Information");
		staffInfor.setPreferredSize(new Dimension(250, 30));
		staffInfor.setFont(new Font("Arial", Font.BOLD, 24));
		staffInfor.setForeground(Color.BLACK);
		staffInfor.setBackground(Color.WHITE);
		staffInfor.setBorder(null);

		staffNameTitle.setBounds(50, 100, 45, 14);
		staffNameTitle.setText("Name");
		staffNameTitle.setFont(new Font("Arial", Font.BOLD, 14));
		staffNameTitle.setForeground(Color.BLACK);
		staffNameTitle.setBackground(Color.WHITE);
		staffNameTitle.setBorder(null);

		staffName.setBounds(50, 125, 330, 40);
		staffName.setBackground(Color.WHITE);
		staffName.setModel(new DefaultComboBoxModel(StaffDAO.getInstance().selectName(nameList).toArray()));
		staffName.setSelectedIndex(-1);
		staffName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		staffName.setFocusable(false);

		staffPhone.setBounds(410, 100, 150, 14);
		staffPhone.setText("Phone number");
		staffPhone.setFont(new Font("Arial", Font.BOLD, 14));
		staffPhone.setForeground(Color.BLACK);
		staffPhone.setBackground(Color.WHITE);
		staffPhone.setBorder(null);

		staffPhoneField.setBounds(410, 125, 330, 40);
		staffPhoneField.setBackground(Color.WHITE);
		staffPhoneField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));

		identificationNumber.setBounds(50, 195, 170, 14);
		identificationNumber.setText("Indentification Number");
		identificationNumber.setFont(new Font("Arial", Font.BOLD, 14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);
		identificationNumber.setBorder(null);

		identificationNumberField.setBounds(50, 220, 330, 40);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));

		staffBirth.setBounds(410, 195, 150, 14);
		staffBirth.setText("Birthday");
		staffBirth.setFont(new Font("Arial", Font.BOLD, 14));
		staffBirth.setForeground(Color.BLACK);
		staffBirth.setBackground(Color.blue);
		staffBirth.setBorder(null);

		birthDay.setBounds(410, 220, 330, 40);
		birthDay.setBackground(Color.WHITE);
//		birthDay.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));

		genderCheckBox.setBounds(50, 340, 70, 30);
		genderCheckBox.setFont(new Font("Arial", Font.BOLD, 14));
		genderCheckBox.setForeground(Color.BLACK);
		genderCheckBox.setBackground(Color.WHITE);
		genderCheckBox.setFocusable(false);
		genderCheckBox.setBorder(null);

		staffRoleList.setBounds(50, 290, 150, 14);
		staffRoleList.setFont(new Font("Arial", Font.BOLD, 14));
		staffRoleList.setForeground(Color.BLACK);
		staffRoleList.setBackground(Color.blue);
		staffRoleList.setBorder(null);

		staffRole.setBounds(50, 315, 330, 40);
		staffRole.setModel(new DefaultComboBoxModel(roleList));
		staffRole.setSelectedIndex(-1);
		staffRole.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		staffRole.setFocusable(false);

		submitButton.setBounds(50, 400, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial", Font.BOLD, 14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39, 162, 187));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setFocusable(false);
		submitButton.addActionListener(actionListener);

//	   	 for(int i = 0; i < parentView.getStaffTable().getRowCount(); i++) {
//	   		 temp.addItem(parentView.getStaffTable().getModel().getValueAt(i, 3));
//	   	 }
	}

	public Combobox getStaffNameBox() {
		return staffName;
	}

	public JTextField getStaffName() {
		return staffNameField;
	}

	public JTextField getStaffPhoneField() {
		return staffPhoneField;
	}

	public JTextField getIdentificationNumberField() {
		return identificationNumberField;
	}

	public JDateChooser getBirthDay() {
		return birthDay;
	}

	public AdminView getParentView() {
		return parentView;
	}

	public Combobox getStaffRole() {
		return staffRole;
	}

//	private JComboBox temp = new JComboBox();
//	
//	public JComboBox getTemp() {
//		return temp;
//	}

	public void changeStaffAction() {
		String cccd = identificationNumberField.getText();
		String name = staffName.getSelectedItem().toString();
		String phoneNumber = staffPhoneField.getText();
		String role = null;
		Date birth = null;
		int age = 0;
		if (birthDay.getDate() != null) {
			birth = new Date(birthDay.getDate().getTime());
			Calendar dob = Calendar.getInstance();
			dob.setTime(birth);

			Calendar now = Calendar.getInstance();
			age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
				age--;
			}
		}

		if (staffRole.getSelectedItem() != null) {
			role = staffRole.getSelectedItem().toString();
		}

		if (cccd.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || birth == null || role == null)
			JOptionPane.showMessageDialog(this, "Không được bỏ trống");
		else if (!cccd.matches("\\d{12}") && !cccd.matches("\\d{0}"))
			JOptionPane.showMessageDialog(this, "CCCD phải có đúng 12 số");
		else if (!phoneNumber.matches("\\d{10}") && !phoneNumber.matches("\\d{0}"))
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
		else if (StaffDAO.getInstance().getStaffById(cccd) != null && identificationNumberField.isEnabled() == true)
			JOptionPane.showMessageDialog(this, "CCCD đã tồn tại");
		else if (age < 18) 
			JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi", "Lỗi", JOptionPane.ERROR_MESSAGE);
		else {
			StaffDAO.getInstance().updateStaff(name, phoneNumber, role, birth, cccd);
			JOptionPane.showMessageDialog(this, "Sửa thành công");
			this.dispose();
		}
//		else {
//			JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
//			this.dispose();
//		}
	}

	private JLabel staffInfor = new JLabel();

	private JLabel staffNameTitle = new JLabel();
	private JTextField staffNameField = new JTextField();

	private JLabel staffPhone = new JLabel();
	private JTextField staffPhoneField = new JTextField();

	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();

	private JLabel staffBirth = new JLabel();
	private JDateChooser birthDay = new JDateChooser();

//	private JLabel guestGender = new JLabel();
//	private JPanel genderGroup = new JPanel();
//	private ButtonGroup groupRadioButton = new ButtonGroup();
//	private JRadioButton genderMale = new JRadioButton();
//	private JRadioButton genderFemale = new JRadioButton();

	private JCheckBox genderCheckBox = new JCheckBox("Male");

	private JLabel staffRoleList = new JLabel("Staff Role");
	private Combobox staffRole = new Combobox();
//	private Combobox comboBox = new Combobox();
	private Combobox staffName = new Combobox();

	private JButton submitButton = new Button();

	private ActionListener actionListener = new ChangeStaffController(this);

	String roleList[] = { "Nhân viên lễ tân", "Nhân viên kế toán", "Nhân viên phục vụ" };
	List<String> nameList = null;
//	List<String> roleList = null;

}
