package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.BillDAO;
import controller.BillController;
import view.editComponent.Combobox;
import view.editComponent.Table;
import view.editComponent.TextField;

public class BillView extends JPanel{
	private ActionListener actionListener = new BillController(this);
	private MouseListener mouseListener = new BillController(this);
	private Calendar calendar;
	public BillView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);
		
		yearList = new Combobox<>();
		calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
        for (int year = currentYear - 10; year <= currentYear; year++) {
            yearList.addItem(year);
        }
        yearList.setSelectedIndex(-1);
        yearList.addActionListener(actionListener);
        
		monthList = new Combobox<>();
		for (int month = 1; month <= 12; month++) {
			monthList.addItem(month);
	    }
	    monthList.setSelectedIndex(-1);
		monthList.addActionListener(actionListener);
		
		dayList = new Combobox<>();
		dayList.addActionListener(actionListener);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(staffSearch);
		subBar.add(dateSearch);
		
		staffSearch.setBounds(10,85,129,60);
		staffSearch.setForeground(Color.WHITE);
		staffSearch.setBackground(new Color(241,243,255));
		staffSearch.setBorder(null);
		staffSearch.add(invoicingStaff);
		staffSearch.add(staffList);
		
		invoicingStaff.setBounds(0,0,32,100);
		invoicingStaff.setPreferredSize(new Dimension(129,25));
		invoicingStaff.setFont(new Font("Arial",Font.BOLD,14));
		invoicingStaff.setForeground(Color.BLACK);
		invoicingStaff.setBackground(new Color(241,243,255));
		invoicingStaff.setBorder(null);
		
		staffList.setBounds(0,30,20,20);
		staffList.setModel(new DefaultComboBoxModel(StaffList));
		staffList.setPreferredSize(new Dimension(129,25));
		staffList.setBackground(Color.WHITE);
		staffList.setFocusable(false);
		staffList.addActionListener(actionListener);
		
		dateSearch.setBounds(10, 180, 129, 250);
		dateSearch.setForeground(Color.WHITE);
		dateSearch.setBackground(new Color(241,243,255));
		dateSearch.setBorder(null);
		dateSearch.add(dateCheckIn);
		dateSearch.add(searchYear);
		dateSearch.add(yearList);
		dateSearch.add(searchMonth);
		dateSearch.add(monthList);
		dateSearch.add(searchDay);
		dateSearch.add(dayList);
		
		dateCheckIn.setBounds(0,0,32,100);
		dateCheckIn.setText("Check In");
		dateCheckIn.setPreferredSize(new Dimension(129,25));
		dateCheckIn.setFont(new Font("Arial",Font.BOLD,14));
		dateCheckIn.setForeground(Color.BLACK);
		dateCheckIn.setBackground(new Color(241,243,255));
		dateCheckIn.setBorder(null);
		
		searchYear.setBounds(0, 60, 32, 100);
		searchYear.setText("Year:");
		searchYear.setPreferredSize(new Dimension(129,25));
		searchYear.setFont(new Font("Arial",Font.BOLD,14));
		searchYear.setForeground(Color.BLACK);
		searchYear.setBackground(new Color(241,243,255));
		searchYear.setBorder(null);
		
		yearList.setBounds(0, 100, 32, 100);
		yearList.setBackground(Color.WHITE);
		yearList.setForeground(Color.BLACK);
		yearList.setPreferredSize(new Dimension(129,25));
		
		searchMonth.setBounds(0, 150, 32, 100);
		searchMonth.setText("Month:");
		searchMonth.setPreferredSize(new Dimension(129,25));
		searchMonth.setFont(new Font("Arial",Font.BOLD,14));
		searchMonth.setForeground(Color.BLACK);
		searchMonth.setBackground(new Color(241,243,255));
		searchMonth.setBorder(null);
		
		monthList.setBounds(0, 210, 32, 100);
		monthList.setBackground(Color.WHITE);
		monthList.setPreferredSize(new Dimension(129,25));
		monthList.setEnabled(false);
		
		searchDay.setBounds(0, 260, 32, 100);
		searchDay.setText("Day:");
		searchDay.setPreferredSize(new Dimension(129, 25));
		searchDay.setFont(new Font("Arial",Font.BOLD,14));
		searchDay.setForeground(Color.BLACK);
		searchDay.setBackground(new Color(241,243,255));
		searchDay.setBorder(null);
		
		dayList.setBounds(0, 320, 32, 100);
		dayList.setBackground(Color.WHITE);
		dayList.setPreferredSize(new Dimension(129,25));
		dayList.setEnabled(false);
		
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		mainContent.add(mainBillTable);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);

		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
		
		mainBillTable.setBounds(0,85,1020-150-84,720-130);
		mainBillTable.setBorder(null);
		mainBillTable.setLayout(new BorderLayout());
		mainBillTable.setBackground(Color.WHITE);
		mainBillTable.add(billTable.getTableHeader(), BorderLayout.NORTH);
		mainBillTable.add(billTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(billTable);
		billTable.fixTable(jScrollPane2);
		mainBillTable.add(jScrollPane2);
		
		billTable.setBounds(0,0,1020,720-120);
		billTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
//		billTable.setShowGrid(false);
//		billTable.setShowHorizontalLines(false);
		billTable.setShowVerticalLines(false);
		billTable.setGridColor(new Color(204,204,204));
		billTable.setBorder(null);
        billTable.setColumnAlignment(0, JLabel.CENTER);
        billTable.setCellAlignment(0, JLabel.CENTER);
        billTable.setColumnAlignment(1, JLabel.CENTER);
        billTable.setCellAlignment(1, JLabel.CENTER);
        billTable.setColumnAlignment(2, JLabel.CENTER);
        billTable.setCellAlignment(2, JLabel.CENTER);
        billTable.setColumnAlignment(3, JLabel.CENTER);
        billTable.setCellAlignment(3, JLabel.CENTER);
        billTable.setColumnAlignment(4, JLabel.CENTER);
        billTable.setCellAlignment(4, JLabel.CENTER);
        billTable.setFont(new Font("Arial",Font.BOLD,12));
		billTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Bill ID", "Guest", "Date", "Total Money", "Invoicing Staff"}
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		BillDAO.getInstance().selectAll(billTable);
		billTable.addMouseListener(mouseListener);
		this.setVisible(false);
	}
	String StaffList[] = {"All", "Hồ Minh Hải", "Lê Thanh Hoa", "Lê Trung Kiên", "Nguyễn Linh Chi", "Nguyễn Thanh Tùng", "Phạm Hoài Sơn", "Phạm Minh Hùng", "Trương Tịnh Nghi", "Trần Thanh Khoa", "Trần Thị Linh"};
	
	public JComboBox<Integer> getYearList() {
		return yearList;
	}
	
	public JComboBox<Integer> getMonthList() {
		return monthList;
	}
	
	public JComboBox<Integer> getDayList() {
		return dayList;
	}
	
	public JComboBox getStaffList() {
		return staffList;
	}

	public JTextField getSearchBox() {
		return searchBox;
	}
	
	public Table getBillTable() {
		return billTable;
	}
	
	public void setIndexComboBox () {
		staffList.setSelectedIndex(-1);
		yearList.setSelectedIndex(-1);
		monthList.setSelectedIndex(-1);
		dayList.setSelectedIndex(-1);
		monthList.setEnabled(false);
		dayList.setEnabled(false);
	}
	//sub bar
	private JPanel subBar = new JPanel();
	private JPanel staffSearch = new JPanel();
	private JLabel invoicingStaff = new JLabel("Invoicing Staff");
	private JComboBox staffList = new Combobox();
	//	
	private	JPanel dateSearch = new JPanel();
	private	JLabel dateCheckIn = new JLabel();
	private	JLabel searchYear = new JLabel();
	private	JLabel searchMonth = new JLabel();
	private	JLabel searchDay = new JLabel();
	
	private Combobox<Integer> yearList;
	private Combobox<Integer> monthList; 
	private Combobox<Integer> dayList;
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel mainBillTable = new JPanel();
	private Table billTable = new Table();
	private JScrollPane jScrollPane1 = new JScrollPane();
	
	private JPanel searchBar = new JPanel();
	private JTextField searchBox = new TextField();
	
}
