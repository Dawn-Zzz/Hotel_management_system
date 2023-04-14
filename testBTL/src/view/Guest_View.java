package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.editComponent.Button;
import view.editComponent.TextField;

//public class Guest_View extends View{
//	public Guest_View() {
//		this.Init();
//	}
//
//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	String GuestType[] = {"All", "VIP", "Popularly"};
//	int nDay;
//	String YearList[] = new String[51];
//	String MonthList[] = new String[13];
//	String DayList[] = new String[32];
//	public void setDateList(String YearList[], String MonthList[], String DayList[]) {
//		nDay = 31;
//		for(int i = 1; i < 51; i++) {
////			ArrayYearList.add((2023 - i) + "");
//			YearList[i] = (2023 - i + 1) + "";
//		}
//		YearList[0] = "All";
////		YearList = ArrayYearList.toArray(new String[0]);
//		for(int i = 1; i < 13; i++) {
////			ArrayMonthList.add("" + (i + 1));
//			MonthList[i] = (i + 1 - 1) + "";
//		}
//		MonthList[0] = "All";
////		MonthList = ArrayMonthList.toArray(new String[0]);
//		for(int i = 1; i < nDay + 1; i++) {
//			DayList[i] = (i + 1 - 1) + "";
////			ArrayDayList.add("" + (i + 1));
//		}
//		DayList[0] = "All";
////		DayList = ArrayDayList.toArray(new String[0]);
//	}
////	List<String> ArrayYearList = new ArrayList<>(Arrays.asList(YearList));
////	List<String> ArrayMonthList = new ArrayList<>(Arrays.asList(MonthList));
////	List<String> ArrayDayList = new ArrayList<>(Arrays.asList(DayList));
//	
//	//sub bar
//	private JPanel subBar = new JPanel();
//	private JButton addGuestButton = new Button();
//	private JPanel guestSearch = new JPanel();
//	private JLabel guestType = new JLabel();
//	private JComboBox guestList = new JComboBox(GuestType);
//
//	
//	private JPanel dateSearch = new JPanel();
//	private JLabel dateCheckIn = new JLabel();
//	private JLabel searchYear = new JLabel();
//	private JLabel searchMonth = new JLabel();
//	private JLabel searchDay = new JLabel();
////	private JComboBox yearList = new JComboBox(YearList);
////	private JComboBox monthList = new JComboBox(MonthList);
////	private JComboBox dayList = new JComboBox(DayList);
//	
//	//main section
//	private JPanel mainContent = new JPanel();
//	private JPanel searchBar = new JPanel();
//	private JButton logOutButton = new Button();
//	private JTextField searchBox = new TextField();
//	
//	ImageIcon image;
//	JLabel displayField;	
//	
//	
//	public void subBarSection() {
//		//Sub bar section
//		setDateList(YearList, MonthList,DayList);
//		JComboBox yearList = new JComboBox(YearList);
//		JComboBox monthList = new JComboBox(MonthList);
//		JComboBox dayList = new JComboBox(DayList);
//		subBar.setBounds(84,0,190,screenSize.height-30);
//		subBar.setLayout(null);
//		subBar.setBackground(new Color(241,243,255));
//		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
//		subBar.add(addGuestButton);
//		subBar.add(guestSearch);
//		subBar.add(dateSearch);
//
//		addGuestButton.setBounds(10,25,149,40);
//		addGuestButton.setText("Add Guest");
//		addGuestButton.setForeground(Color.WHITE);
//		addGuestButton.setBackground(new Color(39,162,187));
//		addGuestButton.setLayout(null);
//		addGuestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		addGuestButton.setFocusable(false);
//		
//		guestSearch.setBounds(10,85,149,60);
//		guestSearch.setForeground(Color.WHITE);
//		guestSearch.setBackground(new Color(241,243,255));
//		guestSearch.setBorder(null);
//		guestSearch.add(guestType);
//		guestSearch.add(guestList);
//		
//		guestType.setBounds(0,0,32,100);
//		guestType.setText("Guest Type");
//		guestType.setPreferredSize(new Dimension(149,25));
//		guestType.setFont(new Font("Arial",Font.BOLD,14));
//		guestType.setForeground(Color.BLACK);
//		guestType.setBackground(new Color(241,243,255));
//		guestType.setBorder(null);
//		
//		guestList.setBounds(0, 40, 20, 100);
//		guestList.setBackground(Color.WHITE);
//		guestList.setPreferredSize(new Dimension(149,25));
//		
//		dateSearch.setBounds(10, 180, 149, 250);
//		dateSearch.setForeground(Color.WHITE);
//		dateSearch.setBackground(new Color(241,243,255));
//		dateSearch.setBorder(null);
//		dateSearch.add(dateCheckIn);
//		dateSearch.add(searchYear);
//		dateSearch.add(yearList);
//		dateSearch.add(searchMonth);
//		dateSearch.add(monthList);
//		dateSearch.add(searchDay);
//		dateSearch.add(dayList);
//		
//		dateCheckIn.setBounds(0,0,32,100);
//		dateCheckIn.setText("Check In");
//		dateCheckIn.setPreferredSize(new Dimension(149,25));
//		dateCheckIn.setFont(new Font("Arial",Font.BOLD,14));
//		dateCheckIn.setForeground(Color.BLACK);
//		dateCheckIn.setBackground(new Color(241,243,255));
//		dateCheckIn.setBorder(null);
//		
//		searchYear.setBounds(0, 60, 32, 100);
//		searchYear.setText("Year:");
//		searchYear.setPreferredSize(new Dimension(149,25));
//		searchYear.setFont(new Font("Arial",Font.BOLD,14));
//		searchYear.setForeground(Color.BLACK);
//		searchYear.setBackground(new Color(241,243,255));
//		searchYear.setBorder(null);
//		
//		yearList.setBounds(0, 100, 32, 100);
//		yearList.setBackground(Color.WHITE);
//		yearList.setForeground(Color.BLACK);
//		yearList.setPreferredSize(new Dimension(149,25));
//		
//		searchMonth.setBounds(0, 150, 32, 100);
//		searchMonth.setText("Month:");
//		searchMonth.setPreferredSize(new Dimension(149,25));
//		searchMonth.setFont(new Font("Arial",Font.BOLD,14));
//		searchMonth.setForeground(Color.BLACK);
//		searchMonth.setBackground(new Color(241,243,255));
//		searchMonth.setBorder(null);
//		
//		monthList.setBounds(0, 210, 32, 100);
//		monthList.setBackground(Color.WHITE);
//		monthList.setPreferredSize(new Dimension(149,25));
//		
//		searchDay.setBounds(0, 260, 32, 100);
//		searchDay.setText("Day:");
//		searchDay.setPreferredSize(new Dimension(149, 25));
//		searchDay.setFont(new Font("Arial",Font.BOLD,14));
//		searchDay.setForeground(Color.BLACK);
//		searchDay.setBackground(new Color(241,243,255));
//		searchDay.setBorder(null);
//		
//		dayList.setBounds(0, 320, 32, 100);
//		dayList.setBackground(Color.WHITE);
//		dayList.setPreferredSize(new Dimension(149,25));
//	}
//	
//	public void mainSection() {
//		//Main section
//		mainContent.setBounds(174,0,screenSize.width-174,screenSize.height-30);
//		mainContent.setLayout(null);
//		mainContent.setBackground(Color.WHITE);
//		mainContent.add(searchBar);
//		
//		searchBar.setBounds(0,0,screenSize.width-174,85);
//		searchBar.setLayout(null);
//		searchBar.setBackground(new Color(241,243,255));
//		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
//		searchBar.add(searchBox);
//		searchBar.add(logOutButton);
////				logOutButton.setFont(new Font("Arial",Font.BOLD,14));
//		logOutButton.setText("Log Out");
//		logOutButton.setForeground(Color.WHITE);
//		logOutButton.setBounds(screenSize.width-174-150,25,80,40);
//		logOutButton.setLayout(null);
//		logOutButton.setBackground(new Color(39,162,187));
//		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		logOutButton.setFocusable(false);
////				logOutButton.setBorder(new RoundedBorder(10));
//		searchBox.setBounds(180,25,300,40);	
//		searchBox.setBackground(Color.WHITE);
//	}
//	
//	public void Init() {
////		leftBarSection();	
//		subBarSection();
//		mainSection();		
//		
//		this.setTitle("HOTEL MANAGEMENT");
//		this.setSize(screenSize.width, screenSize.height-30);
//		this.setLayout(null);
//		this.setResizable(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocationRelativeTo(null);
//		this.setVisible(true);
////		this.add(leftBar);
//		this.add(subBar);
//		this.add(mainContent);
//	}
//}
public class Guest_View extends JPanel {
	public Guest_View() {
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
//	}
//	
//	public void mainSection() {
//		//Main section
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
	
//	public void Init() {
		String GuestType[] = {"All", "VIP", "Popularly"};
		int nDay;
		String YearList[] = new String[51];
		String MonthList[] = new String[13];
		String DayList[] = new String[32];
		public void setDateList(String YearList[], String MonthList[], String DayList[]) {
			nDay = 31;
			for(int i = 1; i < 51; i++) {
//				ArrayYearList.add((2023 - i) + "");
				YearList[i] = (2023 - i + 1) + "";
			}
			YearList[0] = "All";
//			YearList = ArrayYearList.toArray(new String[0]);
			for(int i = 1; i < 13; i++) {
//				ArrayMonthList.add("" + (i + 1));
				MonthList[i] = (i + 1 - 1) + "";
			}
			MonthList[0] = "All";
//			MonthList = ArrayMonthList.toArray(new String[0]);
			for(int i = 1; i < nDay + 1; i++) {
				DayList[i] = (i + 1 - 1) + "";
//				ArrayDayList.add("" + (i + 1));
			}
			DayList[0] = "All";
//			DayList = ArrayDayList.toArray(new String[0]);
		}
//		List<String> ArrayYearList = new ArrayList<>(Arrays.asList(YearList));
//		List<String> ArrayMonthList = new ArrayList<>(Arrays.asList(MonthList));
//		List<String> ArrayDayList = new ArrayList<>(Arrays.asList(DayList));
		

//		//sub bar
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
		
	//	
//		//main section
		JPanel mainContent = new JPanel();
		JPanel searchBar = new JPanel();
		JButton logOutButton = new Button();
		JTextField searchBox = new TextField();
	//	
//		ImageIcon image;
//		JLabel displayField;	
	//	
	//	
//		public void subBarSection() {
//			//Sub bar section
//			setDateList(YearList, MonthList,DayList);
//			JComboBox yearList = new JComboBox(YearList);
//			JComboBox monthList = new JComboBox(MonthList);
//			JComboBox dayList = new JComboBox(DayList);
//			subBar.setBounds(84,0,190,690);
//			subBar.setLayout(null);
//			subBar.setBackground(new Color(241,243,255));
//			subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
//			subBar.add(addGuestButton);
//			subBar.add(guestSearch);
//			subBar.add(dateSearch);
//	//
//			addGuestButton.setBounds(10,25,149,40);
//			addGuestButton.setText("Add Guest");
//			addGuestButton.setForeground(Color.WHITE);
//			addGuestButton.setBackground(new Color(39,162,187));
//			addGuestButton.setLayout(null);
//			addGuestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			addGuestButton.setFocusable(false);
//			
//			guestSearch.setBounds(10,85,149,60);
//			guestSearch.setForeground(Color.WHITE);
//			guestSearch.setBackground(new Color(241,243,255));
//			guestSearch.setBorder(null);
//			guestSearch.add(guestType);
//			guestSearch.add(guestList);
//			
//			guestType.setBounds(0,0,32,100);
//			guestType.setText("Guest Type");
//			guestType.setPreferredSize(new Dimension(149,25));
//			guestType.setFont(new Font("Arial",Font.BOLD,14));
//			guestType.setForeground(Color.BLACK);
//			guestType.setBackground(new Color(241,243,255));
//			guestType.setBorder(null);
//			
//			guestList.setBounds(0, 40, 20, 100);
//			guestList.setBackground(Color.WHITE);
//			guestList.setPreferredSize(new Dimension(149,25));
//			
//			dateSearch.setBounds(10, 180, 149, 250);
//			dateSearch.setForeground(Color.WHITE);
//			dateSearch.setBackground(new Color(241,243,255));
//			dateSearch.setBorder(null);
//			dateSearch.add(dateCheckIn);
//			dateSearch.add(searchYear);
//			dateSearch.add(yearList);
//			dateSearch.add(searchMonth);
//			dateSearch.add(monthList);
//			dateSearch.add(searchDay);
//			dateSearch.add(dayList);
//			
//			dateCheckIn.setBounds(0,0,32,100);
//			dateCheckIn.setText("Check In");
//			dateCheckIn.setPreferredSize(new Dimension(149,25));
//			dateCheckIn.setFont(new Font("Arial",Font.BOLD,14));
//			dateCheckIn.setForeground(Color.BLACK);
//			dateCheckIn.setBackground(new Color(241,243,255));
//			dateCheckIn.setBorder(null);
//			
//			searchYear.setBounds(0, 60, 32, 100);
//			searchYear.setText("Year:");
//			searchYear.setPreferredSize(new Dimension(149,25));
//			searchYear.setFont(new Font("Arial",Font.BOLD,14));
//			searchYear.setForeground(Color.BLACK);
//			searchYear.setBackground(new Color(241,243,255));
//			searchYear.setBorder(null);
//			
//			yearList.setBounds(0, 100, 32, 100);
//			yearList.setBackground(Color.WHITE);
//			yearList.setForeground(Color.BLACK);
//			yearList.setPreferredSize(new Dimension(149,25));
//			
//			searchMonth.setBounds(0, 150, 32, 100);
//			searchMonth.setText("Month:");
//			searchMonth.setPreferredSize(new Dimension(149,25));
//			searchMonth.setFont(new Font("Arial",Font.BOLD,14));
//			searchMonth.setForeground(Color.BLACK);
//			searchMonth.setBackground(new Color(241,243,255));
//			searchMonth.setBorder(null);
//			
//			monthList.setBounds(0, 210, 32, 100);
//			monthList.setBackground(Color.WHITE);
//			monthList.setPreferredSize(new Dimension(149,25));
//			
//			searchDay.setBounds(0, 260, 32, 100);
//			searchDay.setText("Day:");
//			searchDay.setPreferredSize(new Dimension(149, 25));
//			searchDay.setFont(new Font("Arial",Font.BOLD,14));
//			searchDay.setForeground(Color.BLACK);
//			searchDay.setBackground(new Color(241,243,255));
//			searchDay.setBorder(null);
//			
//			dayList.setBounds(0, 320, 32, 100);
//			dayList.setBackground(Color.WHITE);
//			dayList.setPreferredSize(new Dimension(149,25));
////		}
//	//	
////		public void mainSection() {
////			//Main section
//			mainContent.setBounds(174,0,1020-174,690);
//			mainContent.setLayout(null);
//			mainContent.setBackground(Color.WHITE);
//			mainContent.add(searchBar);
//			
//			searchBar.setBounds(0,0,1020-174,85);
//			searchBar.setLayout(null);
//			searchBar.setBackground(new Color(241,243,255));
//			searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
//			searchBar.add(searchBox);
//			searchBar.add(logOutButton);
////					logOutButton.setFont(new Font("Arial",Font.BOLD,14));
//			logOutButton.setText("Log Out");
//			logOutButton.setForeground(Color.WHITE);
//			logOutButton.setBounds(1020-174-150,25,80,40);
//			logOutButton.setLayout(null);
//			logOutButton.setBackground(new Color(39,162,187));
//			logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//			logOutButton.setFocusable(false);
////					logOutButton.setBorder(new RoundedBorder(10));
//			searchBox.setBounds(180,25,300,40);	
//			searchBox.setBackground(Color.WHITE);
//		}
	}
//}