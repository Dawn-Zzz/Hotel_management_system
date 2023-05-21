package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.GuestDAO;
import DAO.StaffDAO;
import controller.AdminController;
import controller.GuestController;
import view.editComponent.Button;
import view.editComponent.Combobox;
import view.editComponent.Table;
import view.editComponent.TextField;

public class AdminView extends JPanel {
	private ActionListener actionListener = new AdminController(this);
	private MouseListener mouseListener = new AdminController(this);
	private Calendar calendar;
	private static AdminView instance;
	public static AdminView getInstance() {
		if (instance==null) {
			instance = new AdminView();
		}
		return instance;
	}
	private int staffsCount;
	
	private AdminView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);

		calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(addStaffButton);
		subBar.add(staffSearch);

		addStaffButton.setBounds(10,25,129,40);
		addStaffButton.setText("Staff Infor");
		addStaffButton.setForeground(Color.WHITE);
		addStaffButton.setBackground(new Color(39,162,187));
		addStaffButton.setLayout(null);
		addStaffButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addStaffButton.setFocusable(false);
		addStaffButton.addActionListener(actionListener);
		
		staffSearch.setBounds(10,85,129,60);
		staffSearch.setForeground(Color.WHITE);
		staffSearch.setBackground(new Color(241,243,255));
		staffSearch.setBorder(null);
		staffSearch.add(staffType);
		staffSearch.add(staffList);
		
		staffType.setBounds(0,0,32,100);
		staffType.setText("Staff Role");
		staffType.setPreferredSize(new Dimension(129,25));
		staffType.setFont(new Font("Arial",Font.BOLD,14));
		staffType.setForeground(Color.BLACK);
		staffType.setBackground(new Color(241,243,255));
		staffType.setBorder(null);
		
		staffList.setLocation(0, 40);
		staffList.setModel(new DefaultComboBoxModel(StaffType));
		staffList.setSelectedIndex(0);
		staffList.setBackground(Color.WHITE);
		staffList.setPreferredSize(new Dimension(129,25));
		staffList.setFocusable(false);
		staffList.addActionListener(actionListener);
		
//		//Main section
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		mainContent.add(mainStaffTable);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);

		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);

		mainStaffTable.setBounds(0,85,1020-150-84,720-130);
		mainStaffTable.setBorder(null);
		mainStaffTable.setLayout(new BorderLayout());
		mainStaffTable.add(staffTable.getTableHeader(), BorderLayout.NORTH);
		mainStaffTable.add(staffTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(staffTable);
		staffTable.fixTable(jScrollPane2);
		mainStaffTable.add(jScrollPane2);
		
//		staffTable
		staffTable.setBounds(0,0,1020,720-120);
		staffTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		staffTable.setShowVerticalLines(false);
		staffTable.setGridColor(new Color(204,204,204));
		staffTable.setBorder(null);
        staffTable.setColumnAlignment(0, JLabel.CENTER);
        staffTable.setCellAlignment(0, JLabel.CENTER);
        staffTable.setColumnAlignment(1, JLabel.CENTER);
        staffTable.setCellAlignment(1, JLabel.CENTER);
        staffTable.setColumnAlignment(2, JLabel.CENTER);
        staffTable.setCellAlignment(2, JLabel.CENTER);
        staffTable.setColumnAlignment(3, JLabel.CENTER);
        staffTable.setCellAlignment(3, JLabel.CENTER);
        staffTable.setColumnAlignment(4, JLabel.CENTER);
        staffTable.setCellAlignment(4, JLabel.CENTER);
        staffTable.setFont(new Font("Arial",Font.BOLD,12));
		staffTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Staff Name", "Indentification Number", "Phone Number","Birthday", "Staff Role"}) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		
		StaffDAO.getInstance().selectAll(staffTable);
		staffsCount = staffTable.getModel().getRowCount();
		staffTable.addMouseListener(mouseListener);
		this.setVisible(false);
//		System.out.println(staffTable.getModel().getRowCount());
	}
	
	public int getCountStaffs() {
		return staffsCount;
	}

	public void resetStaffTable() {
		((DefaultTableModel) staffTable.getModel()).setRowCount(0);
		StaffDAO.getInstance().selectAll(staffTable);
		System.out.println("reset2");
		staffsCount = staffTable.getModel().getRowCount();
		((DefaultTableModel) staffTable.getModel()).fireTableDataChanged();
	}
	
	public JTextField getSearchBox() {
		return searchBox;
	}
	
	public Table getStaffTable() {
		return staffTable;
	}
	
	public JComboBox getStaffList() {
		return staffList;
	}
	
	String StaffType[] = {"All","Giám đốc", "Nhân viên lễ tân", "Nhân viên phục vụ", "Nhân viên kế toán"};

	//	//sub bar
	JPanel subBar = new JPanel();
	JButton addStaffButton = new Button();
	
	JPanel staffSearch = new JPanel();
	JLabel staffType = new JLabel();
	JComboBox staffList = new Combobox();
//	
//	//main section
	JPanel mainContent = new JPanel();
	JPanel mainStaffTable = new JPanel();
	Table staffTable = new Table();
	JScrollPane jScrollPane1 = new JScrollPane();
	
	JPanel searchBar = new JPanel();
	JTextField searchBox = new TextField();
}
