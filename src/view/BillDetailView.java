package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import view.editComponent.Button;
import view.editComponent.Table;

public class BillDetailView extends JDialog {
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	private JLabel billID = new JLabel();
	private JButton exportPDFButton = new Button();
	
	private JLabel guestName = new JLabel();
	private JLabel date = new JLabel();
	private JLabel invoicingStaff = new JLabel();
	private JLabel totalMoney = new JLabel();
	
	private JPanel mainTable1 = new JPanel();
	private Table roomInforTable = new Table();
	private JLabel toTal1 = new JLabel();
	
	private JPanel mainTable2 = new JPanel();
	private Table serviceInforTable = new Table();
	private JLabel toTal2 = new JLabel();
	
	public BillDetailView() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0,85,900,600);
		this.setIconImage(image.getImage());
		this.setLayout(null);
		this.add(billID);
		this.add(exportPDFButton);
		this.add(guestName);
		this.add(date);
		this.add(invoicingStaff);
		this.add(totalMoney);
		this.add(mainTable1);
		this.add(toTal1);
		this.add(mainTable2);
		this.add(toTal2);
		//this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		billID.setBounds(50,50,250,30);
		billID.setText("Bill ID: ...");
		billID.setPreferredSize(new Dimension(250,30));
		billID.setFont(new Font("Arial",Font.BOLD,24));
		billID.setForeground(Color.BLACK);
		billID.setBackground(Color.WHITE);
		billID.setBorder(null);
		
		exportPDFButton.setBounds(750, 50, 110, 40);
		exportPDFButton.setText("Export PDF");
		exportPDFButton.setFont(new Font("Arial",Font.BOLD,14));
		exportPDFButton.setForeground(Color.WHITE);
		exportPDFButton.setBackground(new Color(39,162,187));
		exportPDFButton.setFocusable(false);
		
		guestName.setBounds(50,100,200,30);
		guestName.setText("Guest: Nguyen Van B");
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		date.setBounds(50,130,150,30);
		date.setText("Date: 10/04/2022");
		date.setFont(new Font("Arial",Font.BOLD,14));
		date.setForeground(Color.BLACK);
		date.setBackground(Color.WHITE);
		date.setBorder(null);
		
		invoicingStaff.setBounds(400,100,250,30);
		invoicingStaff.setText("Invoicing Staff: Nguyen Van A");
		invoicingStaff.setFont(new Font("Arial",Font.BOLD,14));
		invoicingStaff.setForeground(Color.BLACK);
		invoicingStaff.setBackground(Color.WHITE);
		invoicingStaff.setBorder(null);
		
		totalMoney.setBounds(400,130,250,30);
		totalMoney.setText("Total Money: 1.000.000");
		totalMoney.setFont(new Font("Arial",Font.BOLD,14));
		totalMoney.setForeground(Color.BLACK);
		totalMoney.setBackground(Color.WHITE);
		totalMoney.setBorder(null);
	
		mainTable1.setBounds(50,190,800,128);
		mainTable1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		mainTable1.setLayout(new BorderLayout());
		mainTable1.add(roomInforTable.getTableHeader(), BorderLayout.NORTH);
		mainTable1.add(roomInforTable, BorderLayout.CENTER);
		JScrollPane jScrollPane = new JScrollPane(roomInforTable);
		roomInforTable.fixTable(jScrollPane);
		mainTable1.add(jScrollPane);
		
		roomInforTable.setBackground(Color.WHITE);
		roomInforTable.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		roomInforTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		roomInforTable.setShowVerticalLines(true);
		roomInforTable.setGridColor(new Color(204,204,204));
		roomInforTable.setColumnAlignment(0, JLabel.CENTER);
		roomInforTable.setCellAlignment(0, JLabel.CENTER);
		roomInforTable.setColumnAlignment(1, JLabel.CENTER);
		roomInforTable.setCellAlignment(1, JLabel.CENTER);
		roomInforTable.setColumnAlignment(2, JLabel.CENTER);
		roomInforTable.setCellAlignment(2, JLabel.CENTER);
		roomInforTable.setColumnAlignment(3, JLabel.CENTER);
		roomInforTable.setCellAlignment(3, JLabel.CENTER);
		roomInforTable.setFont(new Font("Arial",Font.BOLD,12));
		roomInforTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Room", "Check In", "Check Out", "Room Fee"}) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
		
		DefaultTableModel mode1 = (DefaultTableModel) roomInforTable.getModel();
        for (int i = 1; i <= 30; i++) {
            mode1.addRow(new Object[]{"10"+ i, "1/1/2023","12/04/2022", "200.000"});
        }
        
        toTal1.setBounds(750,325,150,30);
        toTal1.setText("Total: 600.000");
        toTal1.setFont(new Font("Arial",Font.BOLD,14));
        toTal1.setForeground(Color.BLACK);
        toTal1.setBackground(Color.WHITE);
        
        //service
        mainTable2.setBounds(50,385,800,95);
		mainTable2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		mainTable2.setLayout(new BorderLayout());
		mainTable2.add(serviceInforTable.getTableHeader(), BorderLayout.NORTH);
		mainTable2.add(serviceInforTable, BorderLayout.CENTER);
		JScrollPane jScrollPane2 = new JScrollPane(serviceInforTable);
		serviceInforTable.fixTable(jScrollPane2);
		mainTable2.add(jScrollPane2);
		
		//serviceInforTable.setBounds(30,290,720,500);
		serviceInforTable.setBackground(Color.WHITE);
		serviceInforTable.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(204,204,204)));
		serviceInforTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(204,204,204)));
		serviceInforTable.setShowVerticalLines(true);
		serviceInforTable.setGridColor(new Color(204,204,204));
		serviceInforTable.setColumnAlignment(0, JLabel.CENTER);
		serviceInforTable.setCellAlignment(0, JLabel.CENTER);
		serviceInforTable.setColumnAlignment(1, JLabel.CENTER);
		serviceInforTable.setCellAlignment(1, JLabel.CENTER);
		serviceInforTable.setColumnAlignment(2, JLabel.CENTER);
		serviceInforTable.setCellAlignment(2, JLabel.CENTER);
		serviceInforTable.setColumnAlignment(3, JLabel.CENTER);
		serviceInforTable.setCellAlignment(3, JLabel.CENTER);
		serviceInforTable.setFont(new Font("Arial",Font.BOLD,12));
		serviceInforTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Service", "Amount", "Service Fee", "Total Fee"}) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
		
		DefaultTableModel mode2 = (DefaultTableModel) serviceInforTable.getModel();
        for (int i = 1; i <= 30; i++) {
            mode2.addRow(new Object[]{i, "3","100.000", "100.000"});
        }
        
        toTal2.setBounds(750,475,150,30);
        toTal2.setText("Total: 200.000");
        toTal2.setFont(new Font("Arial",Font.BOLD,14));
        toTal2.setForeground(Color.BLACK);
        toTal2.setBackground(Color.WHITE);
	}
}