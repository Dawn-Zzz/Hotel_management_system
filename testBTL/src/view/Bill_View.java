package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.editComponent.Button;
import view.editComponent.TextField;

public class Bill_View extends JPanel{
	public Bill_View() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
//		subBarSection();
//		mainSection();
		this.add(subBar);
		this.add(mainContent);
		
		setDateList(YearList, MonthList, DayList);
		JComboBox yearList = new JComboBox(YearList);
		JComboBox monthList = new JComboBox(MonthList);
		JComboBox dayList = new JComboBox(DayList);
		
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
		staffList.setPreferredSize(new Dimension(129,25));
		staffList.setBackground(Color.WHITE);
		
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
		
		
		
		
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);
		searchBar.add(logOutButton);
//				logOutButton.setFont(new Font("Arial",Font.BOLD,14));
		logOutButton.setText("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setBounds(1020-174-150,25,80,40);
		logOutButton.setLayout(null);
		logOutButton.setBackground(new Color(39,162,187));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOutButton.setFocusable(false);
//				logOutButton.setBorder(new RoundedBorder(10));
		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
	}
	String StaffList[] = {"All", "nv1", "nv2", "nv3", "nv4"};
	int nDay;
	String YearList[] = new String[51];
	String MonthList[] = new String[13];
	String DayList[] = new String[32];
	public void setDateList(String YearList[], String MonthList[], String DayList[]) {
		nDay = 31;
		for(int i = 1; i < 51; i++) {
//			ArrayYearList.add((2023 - i) + "");
			YearList[i] = (2023 - i + 1) + "";
		}
		YearList[0] = "All";
//		YearList = ArrayYearList.toArray(new String[0]);
		for(int i = 1; i < 13; i++) {
//			ArrayMonthList.add("" + (i + 1));
			MonthList[i] = (i + 1 - 1) + "";
		}
		MonthList[0] = "All";
//		MonthList = ArrayMonthList.toArray(new String[0]);
		for(int i = 1; i < nDay + 1; i++) {
			DayList[i] = (i + 1 - 1) + "";
//			ArrayDayList.add("" + (i + 1));
		}
		DayList[0] = "All";
//		DayList = ArrayDayList.toArray(new String[0]);
	}
	
	//sub bar
	private JPanel subBar = new JPanel();
	private JPanel staffSearch = new JPanel();
	private JLabel invoicingStaff = new JLabel("Invoicing Staff");
	private JComboBox staffList = new JComboBox(StaffList);
	//	
	private	JPanel dateSearch = new JPanel();
	private	JLabel dateCheckIn = new JLabel();
	private	JLabel searchYear = new JLabel();
	private	JLabel searchMonth = new JLabel();
	private	JLabel searchDay = new JLabel();
//	private JComboBox yearList = new JComboBox(YearList);
//	private JComboBox monthList = new JComboBox(MonthList);
//	private JComboBox dayList = new JComboBox(DayList);
	
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel searchBar = new JPanel();
	private JButton logOutButton = new Button();
	private JTextField searchBox = new TextField();
	
}
