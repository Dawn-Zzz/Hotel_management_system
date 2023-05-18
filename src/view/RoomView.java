package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.GuestDAO;
import DAO.ReservationDAO;
import DAO.RoomDAO;
import controller.RoomController;
import model.Room;
import view.editComponent.Button;
import view.editComponent.Combobox;
import view.editComponent.Table;
import view.editComponent.TextField;

public class RoomView extends JPanel{
	private ActionListener actionListener = new RoomController(this);
	private MouseListener mouseListener = new RoomController(this);
	private static RoomView instance;
	public static RoomView getInstance() {
		if (instance==null) {
			instance = new RoomView();
		}
		return instance;
	}
	private RoomView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(0,1,1,1,new Color(204,204,204)));
		subBar.add(editRoomButton);
		subBar.add(historyRoomButton);
		subBar.add(roomSearch);
		subBar.add(statusSearch);

		editRoomButton.setBounds(10,25,129,40);
		editRoomButton.setText("Book Room");
		editRoomButton.setForeground(Color.WHITE);
		editRoomButton.setBackground(new Color(39,162,187));
		editRoomButton.setLayout(null);
		editRoomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editRoomButton.setFocusable(false);
		editRoomButton.addActionListener(actionListener);
		
		historyRoomButton.setBounds(10,80,129,40);
		historyRoomButton.setText("Room History");
		historyRoomButton.setForeground(Color.WHITE);
		historyRoomButton.setBackground(new Color(39,162,187));
		historyRoomButton.setLayout(null);
		historyRoomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		historyRoomButton.setFocusable(false);
		historyRoomButton.addActionListener(actionListener);
		
		roomSearch.setBounds(10,135,129,120);
		roomSearch.setForeground(Color.WHITE);
		roomSearch.setBackground(new Color(241,243,255));
		roomSearch.setBorder(null);
		roomSearch.add(roomType);
		roomSearch.add(vipRoom);
		roomSearch.add(popularRoom);
		roomSearch.add(rentalTypeList);
		roomSearch.setVisible(false);
		
		roomType.setBounds(0,0,32,100);
		roomType.setText("Room Type");
		roomType.setPreferredSize(new Dimension(129,25));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(new Color(241,243,255));
		roomType.setBorder(null);
		
		vipRoom.setBounds(00, 40, 20, 90);
		vipRoom.setBackground(new Color(241,243,255));
		vipRoom.setPreferredSize(new Dimension(137,25));
		
		popularRoom.setBounds(00, 60, 20, 90);
		popularRoom.setBackground(new Color(241,243,255));
		popularRoom.setPreferredSize(new Dimension(137,25));
		
		rentalTypeList.setBounds(0,30,20,20);
		rentalTypeList.setModel(new DefaultComboBoxModel(rentalType));
		rentalTypeList.setPreferredSize(new Dimension(129,25));
		rentalTypeList.setBackground(Color.WHITE);
		rentalTypeList.setFocusable(false);
		rentalTypeList.addActionListener(actionListener);
		
		statusSearch.setBounds(10, 270, 129, 150);
		statusSearch.setForeground(Color.WHITE);
		statusSearch.setBackground(new Color(241,243,255));
		statusSearch.setBorder(null);
		statusSearch.add(currentsStatus);
		statusSearch.add(statusList);
		statusSearch.setVisible(false);
		
		currentsStatus.setBounds(0,0,32,100);
		currentsStatus.setText("Current Status");
		currentsStatus.setPreferredSize(new Dimension(129,25));
		currentsStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentsStatus.setForeground(Color.BLACK);
		currentsStatus.setBackground(new Color(241,243,255));
		currentsStatus.setBorder(null);
		
		statusList.setBounds(0,20,32,20);
		statusList.setModel(new DefaultComboBoxModel(statusType));
		statusList.setPreferredSize(new Dimension(129,25));
		statusList.setBackground(Color.WHITE);
		statusList.setFocusable(false);
		statusList.addActionListener(actionListener);
		
		addRoom(mainRoomList);
		
		mainContent.setBounds(150,0,1020-150-64,720);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(mainRoomList);
		mainContent.add(historyRoomList);
		mainContent.add(buttonPanel);
		mainContent.add(searchBar);
		
		mainRoomList.setBounds(0,85,1020-150-84,720-120);
		mainRoomList.setLayout(new GridLayout(6,6));
		mainRoomList.setBorder(null);
		mainRoomList.setVisible(true);
		
		backButton.setBounds(0,0,60,40);
		backButton.setIcon(new ImageIcon("./Images/backButton2.png"));
		backButton.setFont(new Font("Arial", Font.PLAIN, 0));
		backButton.setForeground(Color.BLACK);
		backButton.setBackground(Color.WHITE);
		backButton.setBorder(null);
		backButton.setLayout(null);
		backButton.setFocusable(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(actionListener);
		
		buttonPanel.setBounds(0,85,1020-150-84,40);
//		buttonPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(backButton);
		
		historyRoomList.setBounds(0,125,1020-150-84,720-120);
		historyRoomList.setBorder(BorderFactory.createMatteBorder(2,0,0,0,new Color(204,204,204)));
		historyRoomList.setLayout(new BorderLayout());
//		historyRoomList.setBackground(Color.WHITE);
		historyRoomList.add(roomTable.getTableHeader(), BorderLayout.NORTH);
		historyRoomList.add(roomTable, BorderLayout.CENTER);
		JScrollPane jScrollPane = new JScrollPane(roomTable);
		roomTable.fixTable(jScrollPane);
		historyRoomList.add(jScrollPane);
		historyRoomList.setVisible(false);
		
		roomTable.setBounds(0,40,1020,720-120);
		roomTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		roomTable.setShowVerticalLines(false);
		roomTable.setGridColor(new Color(204,204,204));
		roomTable.setBorder(null);
        roomTable.setColumnAlignment(0, JLabel.CENTER);
        roomTable.setCellAlignment(0, JLabel.CENTER);
        roomTable.setColumnAlignment(1, JLabel.CENTER);
        roomTable.setCellAlignment(1, JLabel.CENTER);
        roomTable.setColumnAlignment(2, JLabel.CENTER);
        roomTable.setCellAlignment(2, JLabel.CENTER);
        roomTable.setColumnAlignment(3, JLabel.CENTER);
        roomTable.setCellAlignment(3, JLabel.CENTER);
        roomTable.setColumnAlignment(4, JLabel.CENTER);
        roomTable.setCellAlignment(4, JLabel.CENTER);
        roomTable.setColumnAlignment(5, JLabel.CENTER);
        roomTable.setCellAlignment(5, JLabel.CENTER);
        roomTable.setColumnAlignment(6, JLabel.CENTER);
        roomTable.setCellAlignment(6, JLabel.CENTER);
        roomTable.setFont(new Font("Arial",Font.BOLD,12));
		roomTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Guest Name", "Room", "Rental Type", "Check In", "Check Out", "Room Occupancy","Status"}) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false,false,false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		roomTable.setColumnWidth(0,220);
		roomTable.setColumnWidth(1,60);
		roomTable.setColumnWidth(2,100);
		roomTable.setColumnWidth(3,180);
		roomTable.setColumnWidth(4,180);
		roomTable.setColumnWidth(5,140);
		roomTable.setColumnWidth(6,140);
		
		ReservationDAO.getInstance().selectAll(roomTable);
		roomTable.addMouseListener(mouseListener);
		
		searchBar.setBounds(0,0,1020-150-64,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);
		
		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
		searchBox.setVisible(false);

		this.setVisible(false);
	}
	
	String rentalType[] = {"All" ,"Giờ", "Ngày", "Đêm"};
	String statusType[] = {"All", "Đã Nhận Phòng", "Đã Trả Phòng", "Đã Huỷ Phòng" , "Chưa Nhận Phòng"};

	//sub bar
	private JPanel subBar = new JPanel();
	private JButton editRoomButton = new Button();
	private JButton historyRoomButton = new Button();
	private JPanel roomSearch = new JPanel();
	private JLabel roomType = new JLabel();
	private JCheckBox vipRoom = new JCheckBox("Vip");
	private JCheckBox popularRoom = new JCheckBox("Popular");
	private JComboBox rentalTypeList = new Combobox();
	
	private JPanel statusSearch = new JPanel();
	private JLabel currentsStatus = new JLabel();
	private JComboBox statusList = new Combobox();
	
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel mainRoomList = new JPanel();
	private JPanel searchBar = new JPanel();
	private JTextField searchBox = new TextField();
	
	private JPanel historyRoomList = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton backButton = new JButton("Back Button");
	private Table roomTable = new Table();
	JScrollPane jScrollPane1 = new JScrollPane();
	
	private JButton roomButtonList[] = new JButton[36];

	private ArrayList<Room> roomList;
	
	public void resetRoomTable() {
		((DefaultTableModel) roomTable.getModel()).setRowCount(0);
		ReservationDAO.getInstance().selectAll(roomTable);
	}
	
	
	public JComboBox getRentalTypeList() {
		return rentalTypeList;
	}
	public JComboBox getStatusList() {
		return statusList;
	}
	public Table getRoomTable() {
		return roomTable;
	}
	public JPanel getMainRoomList() {
		return mainRoomList;
	}

	public void setMainRoomList(JPanel mainRoomList) {
		this.mainRoomList = mainRoomList;
	}

	public JPanel getHistoryRoomList() {
		return historyRoomList;
	}

	public void setHistoryRoomList(JPanel historyRoomList) {
		this.historyRoomList = historyRoomList;
	}

	public JPanel getRoomSearch() {
		return roomSearch;
	}

	public void setRoomSearch(JPanel roomSearch) {
		this.roomSearch = roomSearch;
	}

	public JPanel getStatusSearch() {
		return statusSearch;
	}

	public void setStatusSearch(JPanel statusSearch) {
		this.statusSearch = statusSearch;
	}

	public JTextField getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(JTextField searchBox) {
		this.searchBox = searchBox;
	}
	
	public JButton[] getRoomButtonList() {
		return roomButtonList;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void addRoom(JPanel mainRoomList) {
		roomList = new RoomDAO().getInstance().selectAll();
		for(int i = 0; i < roomList.size(); i++) {
			roomButtonList[i] = new JButton(roomList.get(i).getNumberRoom());
			if(roomList.get(i).getCurrentStatus().equals("0")) {
				roomButtonList[i].setBackground(new Color(241,243,255));
				roomButtonList[i].setForeground(new Color(102,0,204));
			}
			else if(roomList.get(i).getCurrentStatus().equals("1")) {
				roomButtonList[i].setBackground(new Color(216,251,219));
				roomButtonList[i].setForeground(new Color(0,139,2));
			}
			else if(roomList.get(i).getCurrentStatus().equals("2")) {
				roomButtonList[i].setBackground(new Color(253,247,218));
				roomButtonList[i].setForeground(new Color(194,157,5));
			}
			else {
				roomButtonList[i].setBackground(new Color(225,185,183));
				roomButtonList[i].setForeground(new Color(184,0,0));
			}
			roomButtonList[i].setBorder(BorderFactory.createMatteBorder(0,0,1,1,new Color(221,221,221)));
			roomButtonList[i].setFont(new Font("Inter", Font.BOLD, 16));
			roomButtonList[i].setFocusable(false);
			roomButtonList[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			roomButtonList[i].addActionListener(actionListener);
			mainRoomList.add(roomButtonList[i]);
		}
	}
	
}