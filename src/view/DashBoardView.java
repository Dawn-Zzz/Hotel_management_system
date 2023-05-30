package view;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DashBoardDAO;
import DAO.RoomDAO;
import calendar.DateChooser;
import chart.Chart;
import chart.ModelChart;
import view.editComponent.PanelRound;

public class DashBoardView extends JPanel{
	public DashBoardView() {
		calendar = Calendar.getInstance();
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
		roomParameter.add(totalRooms);
		roomParameter.add(roomQuantity);
		
		availableroomParameter.setBounds(254,110,204,130);
		availableroomParameter.setBackground(Color.WHITE);
		availableroomParameter.setLayout(null);
		availableroomParameter.add(availableRooms);
		availableroomParameter.add(availableRoomQuantity);
		
		damagedRoomParameter.setBounds(478,110,204,130);
		damagedRoomParameter.setBackground(Color.WHITE);
		damagedRoomParameter.setLayout(null);
		damagedRoomParameter.add(damagedRooms);
		damagedRoomParameter.add(damagedRoomQuantity);
		
		guestParameter.setBounds(702,110,204,130);
		guestParameter.setBackground(Color.WHITE);
		guestParameter.setLayout(null);
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
	    chart.addData(new ModelChart("" + today.minusDays(6).getDayOfMonth() + "/" + today.minusDays(6).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(6)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(6)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(6))}));
	    chart.addData(new ModelChart("" + today.minusDays(5).getDayOfMonth() + "/" + today.minusDays(5).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(5)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(5)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(5))}));
	    chart.addData(new ModelChart("" + today.minusDays(4).getDayOfMonth() + "/" + today.minusDays(4).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(4)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(4)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(4))}));
	    chart.addData(new ModelChart("" + today.minusDays(3).getDayOfMonth() + "/" + today.minusDays(3).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(3)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(3)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(3))}));
	    chart.addData(new ModelChart("" + today.minusDays(2).getDayOfMonth() + "/" + today.minusDays(2).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(2)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(2)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(2))}));
	    chart.addData(new ModelChart("" + today.minusDays(1).getDayOfMonth() + "/" + today.minusDays(1).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(1)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(1)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(1))}));
	    chart.addData(new ModelChart("" + today.getDayOfMonth() + "/" + today.getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today), DashBoardDAO.getInstance().selectCheckOut(today), DashBoardDAO.getInstance().selectBookRoom(today)}));
		
		inforTableTop.setBounds(600,260,307,160);
		inforTableTop.setBackground(Color.WHITE);
		inforTableTop.setLayout(null);
		inforTableTop.add(totalIncomeTitle);
		inforTableTop.add(totalIncome);
		inforTableTop.add(totalIncomeWeeklyTitle);
		inforTableTop.add(totalIncomeWeekly);
		
		totalIncomeTitle.setBounds(25,15,160,15);
		totalIncomeTitle.setFont(new Font("Arial",Font.BOLD,12));
		totalIncomeTitle.setForeground(new Color(150,150,150));
		
		totalIncome.setBounds(25,43,200,25);
		totalIncome.setFont(new Font("Arial",Font.BOLD,22));
		totalIncome.setText(DashBoardDAO.getInstance().selectAll() + " VND");
		
		totalIncomeWeeklyTitle.setBounds(25,85,160,15);
		totalIncomeWeeklyTitle.setFont(new Font("Arial",Font.BOLD,12));
		totalIncomeWeeklyTitle.setForeground(new Color(150,150,150));
		
		totalIncomeWeekly.setBounds(25,113,200,25);
		totalIncomeWeekly.setFont(new Font("Arial",Font.BOLD,22));
		totalIncomeWeekly.setText(DashBoardDAO.getInstance().selectWeek() + " VND");
		
		inforTableBottom.setBounds(600,440,307,220);
		inforTableBottom.setBackground(Color.WHITE);
		inforTableBottom.setLayout(null);
		inforTableBottom.add(dateCalendar);
		
		dateCalendar.setBounds(17,20,275,220);
		dateCalendar.setBackground(Color.WHITE);
		
		this.setVisible(true);
	}
	private RoomView roomView = RoomView.getInstance();
	private GuestView guestView = GuestView.getInstance();
	int countAvailable;
	int countDamaged;
	private Calendar calendar;
	
	private int getCountAvailable() {
		countAvailable = 0;
		for(int i = 0; i < 36; i++) {
			if(roomView.getRoomList().get(i).getCurrentStatus().equals("0")) {
				countAvailable++;
			}
		}
		return countAvailable;
	}
	
	private int getCountDamaged() {
		countDamaged = 0;
		for(int i = 0; i < 36; i++) {
			if(roomView.getRoomList().get(i).getCurrentStatus().equals("2")) {
				countDamaged++;
			}
		}
		return countDamaged;
	}
	
	public void updateChart() {
		chart.getModel().clear();
		chart.addData(new ModelChart("" + today.minusDays(5).getDayOfMonth() + "/" + today.minusDays(5).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(5)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(5)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(5))}));
	    chart.addData(new ModelChart("" + today.minusDays(4).getDayOfMonth() + "/" + today.minusDays(4).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(4)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(4)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(4))}));
	    chart.addData(new ModelChart("" + today.minusDays(3).getDayOfMonth() + "/" + today.minusDays(3).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(3)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(3)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(3))}));
	    chart.addData(new ModelChart("" + today.minusDays(2).getDayOfMonth() + "/" + today.minusDays(2).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(2)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(2)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(2))}));
	    chart.addData(new ModelChart("" + today.minusDays(1).getDayOfMonth() + "/" + today.minusDays(1).getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today.minusDays(1)), DashBoardDAO.getInstance().selectCheckOut(today.minusDays(1)), DashBoardDAO.getInstance().selectBookRoom(today.minusDays(1))}));
	    chart.addData(new ModelChart("" + today.getDayOfMonth() + "/" + today.getMonthValue(), new double[]{DashBoardDAO.getInstance().selectCheckIn(today), DashBoardDAO.getInstance().selectCheckOut(today), DashBoardDAO.getInstance().selectBookRoom(today)}));
	}
	
	public void resetDashBoard() {
		roomView.setRoomList(RoomDAO.getInstance().selectAll());
		totalGuestQuantity.setText("" + guestView.getCountGuests());
		damagedRoomQuantity.setText("" + getCountDamaged());
		availableRoomQuantity.setText("" + getCountAvailable());
		totalIncome.setText(DashBoardDAO.getInstance().selectAll() + " VND");
		totalIncomeWeekly.setText(DashBoardDAO.getInstance().selectWeek() + " VND");
		updateChart();
	}
	
	LocalDate today = LocalDate.now();
	
	private JPanel mainContent = new JPanel();
	private JPanel searchBar = new JPanel();
	
	private JPanel roomParameter = new PanelRound();
	private JLabel totalRooms = new JLabel("Total Rooms");
	private JLabel roomQuantity = new JLabel("36");
	
	private JPanel availableroomParameter = new PanelRound();
	private JLabel availableRooms = new JLabel("Available Rooms");
	private JLabel availableRoomQuantity = new JLabel("" + getCountAvailable());
	
	private JPanel damagedRoomParameter = new PanelRound();
	private JLabel damagedRooms = new JLabel("Damaged Rooms");
	private JLabel damagedRoomQuantity = new JLabel( "" + getCountDamaged());
	
	private JPanel guestParameter = new PanelRound();
	private JLabel totalGuest = new JLabel("Guest");
	private JLabel totalGuestQuantity = new JLabel("" + guestView.getCountGuests());
	
	private JPanel dartTable = new PanelRound();	
	private JPanel inforTableTop = new PanelRound();
	private JLabel totalIncomeTitle = new JLabel("Total Income");
	private JLabel totalIncome = new JLabel();
	private JLabel totalIncomeWeeklyTitle = new JLabel("Total Weekly Income");
	private JLabel totalIncomeWeekly = new JLabel();
	
	private JPanel inforTableBottom = new PanelRound();
	
	private Chart chart = new Chart();
	
	private DateChooser dateCalendar = new DateChooser();
}
