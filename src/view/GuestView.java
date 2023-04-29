package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.GuestController;
import view.editComponent.Button;
import view.editComponent.Table;
import view.editComponent.TextField;

public class GuestView extends JPanel {
	private ActionListener actionListener = new GuestController(this);
	private Calendar calendar;
	public GuestView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);

		yearList = new JComboBox<>();
		calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
        for (int year = currentYear - 10; year <= currentYear; year++) {
            yearList.addItem(year);
        }
        yearList.setSelectedIndex(-1);
        yearList.addActionListener(actionListener);
        
		monthList = new JComboBox<>();
		for (int month = 1; month <= 12; month++) {
			monthList.addItem(month);
	    }
	    monthList.setSelectedIndex(-1);
		monthList.addActionListener(actionListener);
		
		dayList = new JComboBox<>();
		dayList.addActionListener(actionListener);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(addGuestButton);
		subBar.add(guestSearch);
		subBar.add(dateSearch);
//
		addGuestButton.setBounds(10,25,129,40);
		addGuestButton.setText("Add Guest");
		addGuestButton.setForeground(Color.WHITE);
		addGuestButton.setBackground(new Color(39,162,187));
		addGuestButton.setLayout(null);
		addGuestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addGuestButton.setFocusable(false);
		
		guestSearch.setBounds(10,85,129,60);
		guestSearch.setForeground(Color.WHITE);
		guestSearch.setBackground(new Color(241,243,255));
		guestSearch.setBorder(null);
		guestSearch.add(guestType);
		guestSearch.add(guestList);
		
		guestType.setBounds(0,0,32,100);
		guestType.setText("Guest Type");
		guestType.setPreferredSize(new Dimension(129,25));
		guestType.setFont(new Font("Arial",Font.BOLD,14));
		guestType.setForeground(Color.BLACK);
		guestType.setBackground(new Color(241,243,255));
		guestType.setBorder(null);
		
		guestList.setBounds(0, 40, 20, 100);
		guestList.setBackground(Color.WHITE);
		guestList.setPreferredSize(new Dimension(129,25));
		
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
		
//		//Main section
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		mainContent.add(mainGuestTable);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);

		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);

		mainGuestTable.setBounds(0,85,1020-150-84,720-130);
		mainGuestTable.setBorder(null);
		mainGuestTable.setLayout(new BorderLayout());
		mainGuestTable.add(guestTable.getTableHeader(), BorderLayout.NORTH);
		mainGuestTable.add(guestTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(guestTable);
		guestTable.fixTable(jScrollPane2);
		mainGuestTable.add(jScrollPane2);
		
//		guestTable
		guestTable.setBounds(0,0,1020,720-120);
		guestTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		guestTable.setShowVerticalLines(false);
		guestTable.setGridColor(new Color(204,204,204));
		guestTable.setBorder(null);
        guestTable.setColumnAlignment(0, JLabel.CENTER);
        guestTable.setCellAlignment(0, JLabel.CENTER);
        guestTable.setColumnAlignment(1, JLabel.CENTER);
        guestTable.setCellAlignment(1, JLabel.CENTER);
        guestTable.setColumnAlignment(2, JLabel.CENTER);
        guestTable.setCellAlignment(2, JLabel.CENTER);
        guestTable.setColumnAlignment(3, JLabel.CENTER);
        guestTable.setCellAlignment(3, JLabel.CENTER);
        guestTable.setColumnAlignment(4, JLabel.CENTER);
        guestTable.setCellAlignment(4, JLabel.CENTER);
        guestTable.setFont(new Font("Arial",Font.BOLD,12));
		guestTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Guest Name", "Guest ID", "Guest Type", "Check In", "Phone Number"}) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		DefaultTableModel mode = (DefaultTableModel) guestTable.getModel();
		for (int i = 1; i <= 30; i++) {
            mode.addRow(new Object[]{"Phuc", i , "VIP", "1/1/2023", "0123456789"});
        }

		this.setVisible(false);
	}
	
	public JComboBox<Integer> getYearList() {
		return yearList;
	}
	public JComboBox<Integer> getMonthList() {
		return monthList;
	}
	public JComboBox<Integer> getDayList() {
		return dayList;
	}
	String GuestType[] = {"All", "VIP", "Popularly"};

//	//sub bar
	JPanel subBar = new JPanel();
	JButton addGuestButton = new Button();
	
	JPanel guestSearch = new JPanel();
	JLabel guestType = new JLabel();
	JComboBox guestList = new JComboBox(GuestType);
//
//	
	JPanel dateSearch = new JPanel();
	JLabel dateCheckIn = new JLabel();
	JLabel searchYear = new JLabel();
	JLabel searchMonth = new JLabel();
	JLabel searchDay = new JLabel();
	private JComboBox<Integer> yearList;
	private JComboBox<Integer> monthList; 
	private JComboBox<Integer> dayList;
	
//	
//	//main section
	JPanel mainContent = new JPanel();
	JPanel mainGuestTable = new JPanel();
	Table guestTable = new Table();
	JScrollPane jScrollPane1 = new JScrollPane();
	
	JPanel searchBar = new JPanel();
	JTextField searchBox = new TextField();
}