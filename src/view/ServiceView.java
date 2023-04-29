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

import view.editComponent.Button;
import view.editComponent.Table;
import view.editComponent.TextField;

public class ServiceView extends JPanel {
	public ServiceView() {
		this.setBounds(0,0,1020-84,720);
		this.setLayout(null);
		this.add(subBar);
		this.add(mainContent);
		
		subBar.setBounds(0,0,150,690);
		subBar.setLayout(null);
		subBar.setBackground(new Color(241,243,255));
		subBar.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		subBar.add(editServiceButton);
		subBar.add(roomSearch);
		subBar.add(serviceTypeSearch);

		editServiceButton.setBounds(10,25,129,40);
		editServiceButton.setText("Edit Service");
		editServiceButton.setForeground(Color.WHITE);
		editServiceButton.setBackground(new Color(39,162,187));
		editServiceButton.setLayout(null);
		editServiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editServiceButton.setFocusable(false);
		
		roomSearch.setBounds(10,85,129,60);
		roomSearch.setForeground(Color.WHITE);
		roomSearch.setBackground(new Color(241,243,255));
		roomSearch.setBorder(null);
		roomSearch.add(roomType);
		roomSearch.add(bedList);
		
		roomType.setBounds(0,0,32,100);
		roomType.setText("Room Type");
		roomType.setPreferredSize(new Dimension(129,25));
		roomType.setFont(new Font("Arial",Font.BOLD,14));
		roomType.setForeground(Color.BLACK);
		roomType.setBackground(new Color(241,243,255));
		roomType.setBorder(null);
		
		bedList.setBounds(0,30,20,20);
		bedList.setPreferredSize(new Dimension(129,25));
		bedList.setBackground(Color.WHITE);
		
		serviceTypeSearch.setBounds(10, 150, 129, 150);
		serviceTypeSearch.setForeground(Color.WHITE);
		serviceTypeSearch.setBackground(new Color(241,243,255));
		serviceTypeSearch.setBorder(null);
		serviceTypeSearch.add(serviceTypeList);
		serviceTypeSearch.add(serviceList);
		
		serviceTypeList.setBounds(0,0,32,100);
		serviceTypeList.setText("Service");
		serviceTypeList.setPreferredSize(new Dimension(129,25));
		serviceTypeList.setFont(new Font("Arial",Font.BOLD,14));
		serviceTypeList.setForeground(Color.BLACK);
		serviceTypeList.setBackground(new Color(241,243,255));
		serviceTypeList.setBorder(null);
		
		serviceList.setBounds(0,20,32,20);
		serviceList.setPreferredSize(new Dimension(129,25));
		serviceList.setBackground(Color.WHITE);
		
		mainContent.setBounds(150,0,1020-150-64,690);
		mainContent.setLayout(null);
		mainContent.setBackground(Color.WHITE);
		mainContent.add(searchBar);
		mainContent.add(mainServiceTable);
		
		searchBar.setBounds(0,0,1020-174,85);
		searchBar.setLayout(null);
		searchBar.setBackground(new Color(241,243,255));
		searchBar.setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		searchBar.add(searchBox);
		
		searchBox.setBounds(30,25,300,40);	
		searchBox.setBackground(Color.WHITE);
		
		mainServiceTable.setBounds(0,85,1020-150-84,720-130);
		mainServiceTable.setBorder(null);
		mainServiceTable.setLayout(new BorderLayout());
		mainServiceTable.setBackground(Color.WHITE);
		mainServiceTable.add(serviceTable.getTableHeader(), BorderLayout.NORTH);
		mainServiceTable.add(serviceTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(serviceTable);
		serviceTable.fixTable(jScrollPane2);
		mainServiceTable.add(jScrollPane2);
		
		serviceTable.setBounds(0,0,1020,720-120);
		serviceTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(204,204,204)));
		serviceTable.setShowVerticalLines(false);
		serviceTable.setGridColor(new Color(204,204,204));
		serviceTable.setBorder(null);
        serviceTable.setColumnAlignment(0, JLabel.CENTER);
        serviceTable.setCellAlignment(0, JLabel.CENTER);
        serviceTable.setColumnAlignment(1, JLabel.CENTER);
        serviceTable.setCellAlignment(1, JLabel.CENTER);
        serviceTable.setColumnAlignment(2, JLabel.CENTER);
        serviceTable.setCellAlignment(2, JLabel.CENTER);
        serviceTable.setColumnAlignment(3, JLabel.CENTER);
        serviceTable.setCellAlignment(3, JLabel.CENTER);
        serviceTable.setColumnAlignment(4, JLabel.CENTER);
        serviceTable.setCellAlignment(4, JLabel.CENTER);
        serviceTable.setFont(new Font("Arial",Font.BOLD,12));
		serviceTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Room", "Service", "Service ID", "Quantity", "Note"}) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		DefaultTableModel mode = (DefaultTableModel) serviceTable.getModel();
		for (int i = 1; i <= 10; i++) {
            mode.addRow(new Object[]{"301", "Service" + i , "SV" + i, "1", ""});
        }

		this.setVisible(false);
	}
	String RoomList[] = {"All" ,"1 Single Bed", "1 Double Bed", "2 Single Bed", "2 Double Bed"};
	String ServiceList[] = {"All"};
	
	//sub bar
	private JPanel subBar = new JPanel();
	private JButton editServiceButton = new Button();
	private JPanel roomSearch = new JPanel();
	private JLabel roomType = new JLabel();
	private JComboBox bedList = new JComboBox(RoomList);
	
	private JPanel serviceTypeSearch = new JPanel();
	private JLabel serviceTypeList = new JLabel();
	private JComboBox serviceList = new JComboBox(ServiceList);
	//main section
	private JPanel mainContent = new JPanel();
	private JPanel mainServiceTable = new JPanel();
	private Table serviceTable = new Table();
	private JScrollPane jScrollPane1 = new JScrollPane();
	
	private JPanel searchBar = new JPanel();
	private JTextField searchBox = new TextField();
	
}
