package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.EquipmentDAO;
import view.editComponent.Button;
import view.editComponent.Table;
import view.editComponent.TextField;

public class EquipmentView extends JPanel {
	public EquipmentView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(roomSearch);
		subBar.add(equipmentTypeSearch);
		subBar.add(equipmentStatusSearch);
		
		roomSearch.setBounds(10,85,129,60);
		roomSearch.setForeground(Color.WHITE);
		roomSearch.setBackground(new Color(241,243,255));
		roomSearch.setBorder(null);
		roomSearch.add(roomType);
		roomSearch.add(bedList);
		
		roomType.setBounds(0,0,32,100);
		roomType.setText("Room");
		roomType.setPreferredSize(new Dimension(129,25));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(new Color(241,243,255));
		roomType.setBorder(null);
		
		bedList.setBounds(0,30,20,20);
		bedList.setPreferredSize(new Dimension(129,25));
		bedList.setBackground(Color.WHITE);
		
		equipmentTypeSearch.setBounds(10, 150, 129, 70);
		equipmentTypeSearch.setForeground(Color.WHITE);
		equipmentTypeSearch.setBackground(new Color(241,243,255));
		equipmentTypeSearch.setBorder(null);
		equipmentTypeSearch.add(equipmentTypeList);
		equipmentTypeSearch.add(equipmentList);
		
		equipmentTypeList.setBounds(0,0,32,100);
		equipmentTypeList.setText("Equipment");
		equipmentTypeList.setPreferredSize(new Dimension(129,25));
		equipmentTypeList.setFont(new Font("Arial",Font.BOLD,14));
		equipmentTypeList.setForeground(Color.BLACK);
		equipmentTypeList.setBackground(new Color(241,243,255));
		equipmentTypeList.setBorder(null);
		
		equipmentList.setBounds(0,20,32,20);
		equipmentList.setPreferredSize(new Dimension(129,25));
		equipmentList.setBackground(Color.WHITE);
		
		equipmentStatusSearch.setBounds(10, 215, 129, 150);
		equipmentStatusSearch.setForeground(Color.WHITE);
		equipmentStatusSearch.setBackground(new Color(241,243,255));
		equipmentStatusSearch.setBorder(null);
		equipmentStatusSearch.add(equipmentStatusList);
		equipmentStatusSearch.add(statusList);
		
		equipmentStatusList.setBounds(0,0,32,100);
		equipmentStatusList.setText("Status");
		equipmentStatusList.setPreferredSize(new Dimension(129,25));
		equipmentStatusList.setFont(new Font("Arial",Font.BOLD,14));
		equipmentStatusList.setForeground(Color.BLACK);
		equipmentStatusList.setBackground(new Color(241,243,255));
		equipmentStatusList.setBorder(null);
		
		statusList.setBounds(0,20,32,20);
		statusList.setPreferredSize(new Dimension(129,25));
		statusList.setBackground(Color.WHITE);
		
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		mainContent.add(mainEquipmentTable);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);

		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
		
		mainEquipmentTable.setBounds(0,85,1020-150-84,720-130);
		mainEquipmentTable.setBorder(null);
		mainEquipmentTable.setLayout(new BorderLayout());
		mainEquipmentTable.setBackground(Color.WHITE);
		mainEquipmentTable.add(equipmentTable.getTableHeader(), BorderLayout.NORTH);
		mainEquipmentTable.add(equipmentTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(equipmentTable);
		equipmentTable.fixTable(jScrollPane2);
		mainEquipmentTable.add(jScrollPane2);
		
		equipmentTable.setBounds(0,0,1020,720-120);
		equipmentTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		equipmentTable.setShowVerticalLines(false);
		equipmentTable.setGridColor(new Color(204,204,204));
		equipmentTable.setBorder(null);
        equipmentTable.setColumnAlignment(0, JLabel.CENTER);
        equipmentTable.setCellAlignment(0, JLabel.CENTER);
        equipmentTable.setColumnAlignment(1, JLabel.CENTER);
        equipmentTable.setCellAlignment(1, JLabel.CENTER);
        equipmentTable.setColumnAlignment(2, JLabel.CENTER);
        equipmentTable.setCellAlignment(2, JLabel.CENTER);
        equipmentTable.setColumnAlignment(3, JLabel.CENTER);
        equipmentTable.setCellAlignment(3, JLabel.CENTER);
        equipmentTable.setColumnAlignment(4, JLabel.CENTER);
        equipmentTable.setCellAlignment(4, JLabel.CENTER);
        equipmentTable.setFont(new Font("Arial",Font.BOLD,12));
		equipmentTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Room", "Equipment", "Equipment ID", "Status"}) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		EquipmentDAO.getInstance().selectAll(equipmentTable);
		this.setVisible(false);
	}
	String RoomList[] = {"All" ,"1 Single Bed", "1 Double Bed", "2 Single Bed", "2 Double Bed"};
	String EquipmentList[] = {"All", "Tá»§ láº¡nh", "Tivi", "MÃ¡y Láº¡nh"};
	String StatusList[] = {"All","Tá»‘t", "Há»�ng"};
	
	//sub bar
	private JPanel subBar = new JPanel();
	private JPanel roomSearch = new JPanel();
	private JLabel roomType = new JLabel();
	private JComboBox bedList = new JComboBox(RoomList);
	
	private JPanel equipmentTypeSearch = new JPanel();
	private JLabel equipmentTypeList = new JLabel();
	private JComboBox equipmentList = new JComboBox(EquipmentList);
	
	private JPanel equipmentStatusSearch = new JPanel();
	private JLabel equipmentStatusList = new JLabel();
	private JComboBox statusList = new JComboBox(StatusList);
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel mainEquipmentTable = new JPanel();
	private Table equipmentTable = new Table();
	private JScrollPane jScrollPane1 = new JScrollPane();
	
	JPanel searchBar = new JPanel();
	JTextField searchBox = new TextField();	
}
