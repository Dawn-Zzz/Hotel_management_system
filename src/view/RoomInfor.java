package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import view.editComponent.Table;

public class RoomInfor extends JDialog{
	public RoomInfor(String number, String type, String bed, String status, String guest) {
		this.setNumber(number);
		this.setType(type);
		this.setBed(bed);
		this.setStatus(status);
		this.setGuest(guest);
		ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,1020,720);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(roomNumber);
		this.add(roomInforTitle);
		this.add(roomType);
		this.add(bedQuantity);
		this.add(currentStatus);
		this.add(guestQuantity);
		this.add(guestInforTitle);
		this.add(guestListSection);
//		this.setVisible(false);
		
		roomNumber.setBounds(50,50,250,30);
		roomNumber.setText("Room " + this.number);
		roomNumber.setPreferredSize(new Dimension(250,30));
		roomNumber.setFont(new Font("Arial",Font.BOLD,24));
		roomNumber.setForeground(Color.BLACK);
		roomNumber.setBackground(Color.WHITE);
		roomNumber.setBorder(null);
		
		roomInforTitle.setBounds(50,100,250,30);
		roomInforTitle.setPreferredSize(new Dimension(250,30));
		roomInforTitle.setFont(new Font("Arial",Font.BOLD,18));
		roomInforTitle.setForeground(Color.BLACK);
		roomInforTitle.setBackground(Color.WHITE);
		roomInforTitle.setBorder(null);
		
		roomType.setBounds(50,130,250,30);
		roomType.setText("Room type: " + type);
		roomType.setPreferredSize(new Dimension(250,30));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(Color.WHITE);
		roomType.setBorder(null);
		
		bedQuantity.setBounds(50,160,250,30);
		bedQuantity.setText("Bed quantity: " + bed);
		bedQuantity.setPreferredSize(new Dimension(250,30));
		bedQuantity.setFont(new Font("Arial",Font.BOLD,14));
		bedQuantity.setForeground(Color.BLACK);
		bedQuantity.setBackground(Color.WHITE);
		bedQuantity.setBorder(null);
		
		currentStatus.setBounds(50,190,250,30);
		currentStatus.setText("Current status: " + status);
		currentStatus.setPreferredSize(new Dimension(250,30));
		currentStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentStatus.setForeground(Color.BLACK);
		currentStatus.setBackground(Color.WHITE);
		currentStatus.setBorder(null);
		
		guestQuantity.setBounds(50,220,250,30);
		guestQuantity.setText("Guest quantity: " + guest);
		guestQuantity.setPreferredSize(new Dimension(250,30));
		guestQuantity.setFont(new Font("Arial",Font.BOLD,14));
		guestQuantity.setForeground(Color.BLACK);
		guestQuantity.setBackground(Color.WHITE);
		guestQuantity.setBorder(null);
		
		guestInforTitle.setBounds(50,270,250,30);
		guestInforTitle.setPreferredSize(new Dimension(250,30));
		guestInforTitle.setFont(new Font("Arial",Font.BOLD,18));
		guestInforTitle.setForeground(Color.BLACK);
		guestInforTitle.setBackground(Color.WHITE);
		guestInforTitle.setBorder(null);
		
		guestListSection.setBounds(50,300,910,37+cell*30);
		guestListSection.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		guestListSection.setLayout(new BorderLayout());
		guestListSection.add(guestInforTable.getTableHeader(), BorderLayout.NORTH);
		guestListSection.add(guestInforTable, BorderLayout.CENTER);
		JScrollPane jScrollPane = new JScrollPane(guestInforTable);
		guestInforTable.fixTable(jScrollPane);
		guestListSection.add(jScrollPane);
		
		guestInforTable.setBounds(50,300,720,100);
		guestInforTable.setBackground(Color.WHITE);
		guestInforTable.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		guestInforTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		guestInforTable.setShowVerticalLines(true);
		guestInforTable.setGridColor(new Color(204,204,204));
		guestInforTable.setColumnAlignment(0, JLabel.CENTER);
		guestInforTable.setCellAlignment(0, JLabel.CENTER);
		guestInforTable.setColumnAlignment(1, JLabel.CENTER);
		guestInforTable.setCellAlignment(1, JLabel.CENTER);
		guestInforTable.setColumnAlignment(2, JLabel.CENTER);
		guestInforTable.setCellAlignment(2, JLabel.CENTER);
		guestInforTable.setColumnAlignment(3, JLabel.CENTER);
		guestInforTable.setCellAlignment(3, JLabel.CENTER);
		guestInforTable.setColumnAlignment(4, JLabel.CENTER);
		guestInforTable.setCellAlignment(4, JLabel.CENTER);
		guestInforTable.setColumnAlignment(5, JLabel.CENTER);
		guestInforTable.setCellAlignment(5, JLabel.CENTER);
		guestInforTable.setFont(new Font("Arial",Font.BOLD,12));
		guestInforTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Name", "Phone Number", "Guest ID", "Birthday", "Gender", "Check In"}) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
		
		DefaultTableModel mode = (DefaultTableModel) guestInforTable.getModel();
        for (int i = 1; i <= cell; i++) {
            mode.addRow(new Object[]{"Phuc", i , "VIP", "1/1/2023", "0123456789","12/04/2022"});
        }
		this.setVisible(false);
	}
	
	private String number;
	private String type;
	private String bed;
	private String status;
	private String guest;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
//	public String getType() {
//		return type;
//	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}

	int cell = 4;
	
	private JLabel roomNumber = new JLabel();
	
	private JLabel roomInforTitle = new JLabel("Room Information: ");
	private JLabel roomType = new JLabel();
	private JLabel bedQuantity = new JLabel();
	private JLabel currentStatus = new JLabel();
	private JLabel guestQuantity = new JLabel();
	
	private JLabel guestInforTitle = new JLabel("Guest Information:");
	
	private JPanel guestListSection = new JPanel();
	private Table guestInforTable = new Table();
}
