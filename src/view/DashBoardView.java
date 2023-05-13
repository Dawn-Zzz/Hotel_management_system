package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import calendar.DateChooser;
import chart.Chart;
import chart.ModelChart;
import view.editComponent.Button;
import view.editComponent.PanelRound;

public class DashBoardView extends JPanel{
	public DashBoardView() {
		calendar = Calendar.getInstance();
		int currentDate = calendar.get(Calendar.DATE);
		int currentMonth = calendar.get(Calendar.MONTH);
		LocalDate today = LocalDate.now();
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(mainContent);
		mainContent.setBounds(0,0,936,690);
		mainContent.setLayout(null);
		mainContent.setBackground(new Color(241,243,255));
		mainContent.add(searchBar);
		mainContent.add(roomParameter);
		mainContent.add(availableroomParameter);
		mainContent.add(damagedRoomParameter);
		mainContent.add(guestParameter);
		mainContent.add(dartTable);
		mainContent.add(inforTableTop);
		mainContent.add(inforTableBottom);
		
		searchBar.setBounds(0,0,1020-84,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(214,214,214)));
		
		roomParameter.setBounds(30,110,204,130);
		roomParameter.setBackground(Color.WHITE);
		roomParameter.setLayout(null);
//		roomParameter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(214,214,214)));
		roomParameter.add(totalRooms);
		roomParameter.add(roomQuantity);
		
		availableroomParameter.setBounds(254,110,204,130);
		availableroomParameter.setBackground(Color.WHITE);
		availableroomParameter.setLayout(null);
//		availableroomParameter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(214,214,214)));
		availableroomParameter.add(availableRooms);
		availableroomParameter.add(availableRoomQuantity);
		
		damagedRoomParameter.setBounds(478,110,204,130);
		damagedRoomParameter.setBackground(Color.WHITE);
		damagedRoomParameter.setLayout(null);
//		damagedRoomParameter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(214,214,214)));
		damagedRoomParameter.add(damagedRooms);
		damagedRoomParameter.add(damagedRoomQuantity);
		
		guestParameter.setBounds(702,110,204,130);
		guestParameter.setBackground(Color.WHITE);
		guestParameter.setLayout(null);
//		guestParameter.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(214,214,214)));
		guestParameter.add(totalGuest);
		guestParameter.add(totalGuestQuantity);
		
		totalRooms.setFont(new Font("Arial",Font.BOLD,16));
		totalRooms.setBounds(0,10,204,25);
		totalRooms.setHorizontalAlignment(JLabel.CENTER);
		totalRooms.setIcon(new ImageIcon("./Images/beddart.png"));
		
		availableRooms.setFont(new Font("Arial",Font.BOLD,16));
		availableRooms.setBounds(0,10,204,25);
		availableRooms.setHorizontalAlignment(JLabel.CENTER);
		availableRooms.setIcon(new ImageIcon("./Images/availableroom.png"));
		
		damagedRooms.setFont(new Font("Arial",Font.BOLD,16));
		damagedRooms.setBounds(0,10,204,25);
		damagedRooms.setHorizontalAlignment(JLabel.CENTER);
		damagedRooms.setIcon(new ImageIcon("./Images/damagedroom.png"));
		
		totalGuest.setFont(new Font("Arial",Font.BOLD,16));
		totalGuest.setBounds(0,10,204,25);
		totalGuest.setHorizontalAlignment(JLabel.CENTER);
		totalGuest.setIcon(new ImageIcon("./Images/guestdart.png"));
		
		roomQuantity.setFont(new Font("Arial",Font.BOLD,24));
		roomQuantity.setBounds(0,53,204,25);
		roomQuantity.setHorizontalAlignment(JLabel.CENTER);
		
		availableRoomQuantity.setFont(new Font("Arial",Font.BOLD,24));
		availableRoomQuantity.setBounds(0,53,204,25);
		availableRoomQuantity.setHorizontalAlignment(JLabel.CENTER);
		
		damagedRoomQuantity.setFont(new Font("Arial",Font.BOLD,24));
		damagedRoomQuantity.setBounds(0,53,204,25);
		damagedRoomQuantity.setHorizontalAlignment(JLabel.CENTER);
		
		totalGuestQuantity.setFont(new Font("Arial",Font.BOLD,24));
		totalGuestQuantity.setBounds(0,53,204,25);
		totalGuestQuantity.setHorizontalAlignment(JLabel.CENTER);
		
		dartTable.setBounds(30,260,550,400);
		dartTable.setBackground(Color.WHITE);
		dartTable.setBackground(new Color(250, 250, 250));
		dartTable.add(chart);
		
	    chart.addLegend("Check In", new Color(245, 189, 135));
	    chart.addLegend("Check Out", new Color(139, 229, 222));
	    chart.addLegend("Book Room", new Color(189, 135, 245));
//	    chart.addLegend("Cost", new Color(135, 189, 245));
	    chart.addData(new ModelChart("" + today.minusDays(6).getDayOfMonth() + "/" + today.minusDays(6).getMonthValue(), new double[]{5, 2, 8,8}));
	    chart.addData(new ModelChart("" + today.minusDays(5).getDayOfMonth() + "/" + today.minusDays(5).getMonthValue(), new double[]{6, 7, 9,15}));
	    chart.addData(new ModelChart("" + today.minusDays(4).getDayOfMonth() + "/" + today.minusDays(4).getMonthValue(), new double[]{20, 3, 4, 4}));
	    chart.addData(new ModelChart("" + today.minusDays(3).getDayOfMonth() + "/" + today.minusDays(3).getMonthValue(), new double[]{4, 15, 7,70}));
	    chart.addData(new ModelChart("" + today.minusDays(2).getDayOfMonth() + "/" + today.minusDays(2).getMonthValue(), new double[]{16, 5, 3,10}));
	    chart.addData(new ModelChart("" + today.minusDays(1).getDayOfMonth() + "/" + today.minusDays(1).getMonthValue(), new double[]{19, 2, 11,0}));
	    chart.addData(new ModelChart("" + today.getDayOfMonth() + "/" + today.getMonthValue(), new double[]{10, 8, 1,20}));
		
		inforTableTop.setBounds(600,260,307,160);
		inforTableTop.setBackground(Color.WHITE);
		
		inforTableBottom.setBounds(600,440,307,220);
		inforTableBottom.setBackground(Color.WHITE);
		inforTableBottom.setLayout(null);
		inforTableBottom.add(dateCalendar);
		
		dateCalendar.setBounds(17,20,275,220);
		dateCalendar.setBackground(Color.WHITE);
		
		this.setVisible(true);
	}
	private RoomView roomView = new RoomView();
	private GuestView guestView = GuestView.getInstance();
	int countAvailable = 0;
	int countDamaged = 0;
	private Calendar calendar;
	
	private int getCountAvailable() {
		for(int i = 0; i < 36; i++) {
			if(roomView.getCurrentStatus()[i] == "Vacant") {
				countAvailable++;
			}
		}
		return countAvailable;
	}
	
	private int getCountDamaged() {
		for(int i = 0; i < 36; i++) {
			if(roomView.getCurrentStatus()[i] == "Room Off") {
				countDamaged++;
			}
		}
		return countDamaged;
	}
	
	public void resetDashBoard() {
		totalGuestQuantity.setText(""+guestView.getCountGuests());
		System.out.println(guestView.getCountGuests());	
	}
	
	private String AvailableQuantity = "" + getCountAvailable();
	private String DamagedQuantity = "" + getCountDamaged();
	private String GuestQuantity = "" + guestView.getCountGuests();
	
	private JPanel mainContent = new JPanel();
	private JPanel searchBar = new JPanel();
	
	private JPanel roomParameter = new PanelRound();
	private JLabel totalRooms = new JLabel("Total Rooms");
	private JLabel roomQuantity = new JLabel("36");
	
	private JPanel availableroomParameter = new PanelRound();
	private JLabel availableRooms = new JLabel("Available Rooms");
	private JLabel availableRoomQuantity = new JLabel(AvailableQuantity);
	
	private JPanel damagedRoomParameter = new PanelRound();
	private JLabel damagedRooms = new JLabel("Damaged Rooms");
	private JLabel damagedRoomQuantity = new JLabel(DamagedQuantity);
	
	private JPanel guestParameter = new PanelRound();
	private JLabel totalGuest = new JLabel("Guest");
	private JLabel totalGuestQuantity = new JLabel(GuestQuantity);
	
	private JPanel dartTable = new PanelRound();	
	private JPanel inforTableTop = new PanelRound();
	private JPanel inforTableBottom = new PanelRound();
	
	private Chart chart = new Chart();
	
	private DateChooser dateCalendar = new DateChooser();
}
