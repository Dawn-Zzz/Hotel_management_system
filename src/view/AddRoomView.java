package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import view.editComponent.Button;
import view.editComponent.Table;

public class AddRoomView extends JDialog{
	private JLabel roomInfor = new JLabel();
	
	private JLabel currentStatus = new JLabel();
	String CurrentStatus[] = {"All", "Vacant", "Room off", "Book in advance", "Occupied"}; 
	private JComboBox<String> currentStatusBox = new JComboBox<>(CurrentStatus);
	
	private JLabel questQuantity = new JLabel();
	private JComboBox<Integer> questQuantityBox = new JComboBox<>();
	
	private JLabel guestList = new JLabel();
	private JPanel mainTable = new JPanel();
	private Table guestListTable = new Table();
	
	private JButton submitButton = new Button();
	
	public AddRoomView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,1020,720);
		this.setLayout(null);
		this.add(roomInfor);
		this.add(currentStatus);
		this.add(currentStatusBox);
		this.add(questQuantity);
		this.add(questQuantityBox);
		this.add(guestList);
		this.add(mainTable);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
		roomInfor.setBounds(50,50,250,30);
		roomInfor.setText("Room ...");
		roomInfor.setPreferredSize(new Dimension(250,30));
		roomInfor.setFont(new Font("Arial",Font.BOLD,24));
		roomInfor.setForeground(Color.BLACK);
		roomInfor.setBackground(Color.WHITE);
		roomInfor.setBorder(null);
		
		currentStatus.setBounds(50,100,120,30);
		currentStatus.setText("Current Status");
		currentStatus.setFont(new Font("Arial",Font.BOLD,14));
		currentStatus.setForeground(Color.BLACK);
		currentStatus.setBackground(Color.WHITE);
		currentStatus.setBorder(null);
		
		currentStatusBox.setBounds(50,130,150,30);
		currentStatusBox.setBackground(Color.WHITE);
		currentStatusBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		
		questQuantity.setBounds(50,180,150,30);
		questQuantity.setText("Quest Quantity");
		questQuantity.setFont(new Font("Arial",Font.BOLD,14));
		questQuantity.setForeground(Color.BLACK);
		questQuantity.setBackground(Color.WHITE);
		questQuantity.setBorder(null);
		
		questQuantityBox.setBounds(50,210,150,30);
		questQuantityBox.setBackground(Color.WHITE);
		questQuantityBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		for(int i = 1; i <= 3 ; i++) {
			questQuantityBox.addItem(i);
		}
		
		guestList.setBounds(50,260,150,30);
		guestList.setText("Guest List");
		guestList.setFont(new Font("Arial",Font.BOLD,14));
		guestList.setForeground(Color.BLACK);
		guestList.setBackground(Color.WHITE);
		
		mainTable.setBounds(50,290,910,155);
		mainTable.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		mainTable.setLayout(new BorderLayout());
		mainTable.add(guestListTable.getTableHeader(), BorderLayout.NORTH);
		mainTable.add(guestListTable, BorderLayout.CENTER);
		JScrollPane jScrollPane = new JScrollPane(guestListTable);
		guestListTable.fixTable(jScrollPane);
		mainTable.add(jScrollPane);
		
		guestListTable.setBounds(30,290,720,500);
		guestListTable.setBackground(Color.WHITE);
		guestListTable.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		guestListTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		guestListTable.setShowVerticalLines(true);
		guestListTable.setGridColor(new Color(204,204,204));
		guestListTable.setColumnAlignment(0, JLabel.CENTER);
		guestListTable.setCellAlignment(0, JLabel.CENTER);
		guestListTable.setColumnAlignment(1, JLabel.CENTER);
		guestListTable.setCellAlignment(1, JLabel.CENTER);
		guestListTable.setColumnAlignment(2, JLabel.CENTER);
		guestListTable.setCellAlignment(2, JLabel.CENTER);
		guestListTable.setColumnAlignment(3, JLabel.CENTER);
		guestListTable.setCellAlignment(3, JLabel.CENTER);
		guestListTable.setColumnAlignment(4, JLabel.CENTER);
		guestListTable.setCellAlignment(4, JLabel.CENTER);
		guestListTable.setColumnAlignment(5, JLabel.CENTER);
		guestListTable.setCellAlignment(5, JLabel.CENTER);
		guestListTable.setFont(new Font("Arial",Font.BOLD,12));
		guestListTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Name", "Phone Number", "Guest ID", "Birthday", "Gender", "Check In"}) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
		
		DefaultTableModel mode = (DefaultTableModel) guestListTable.getModel();
        for (int i = 1; i <= 30; i++) {
            mode.addRow(new Object[]{"Phuc", i , "VIP", "1/1/2023", "0123456789","12/04/2022"});
        }
            
		submitButton.setBounds(50, 600, 80, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial",Font.BOLD,14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39,162,187));
		submitButton.setFocusable(false);
	}
}
