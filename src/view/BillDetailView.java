package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BillDetailController;
import view.editComponent.Button;
import view.editComponent.Table;

public class BillDetailView extends JDialog {
	private ActionListener actionListener = new BillDetailController(this);
	
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	//public static JPanel panel = new JPanel();
	private JLabel billID = new JLabel();
	private JButton exportPDFButton = new Button();
	
	private JLabel guestName = new JLabel();
	private JLabel date = new JLabel();
	private JLabel invoicingStaff = new JLabel();
	private JLabel totalMoney = new JLabel();
	
	private JPanel mainTable1 = new JPanel();
	private Table roomInforTable = new Table();
	private JLabel totalRoom = new JLabel();
	
	private JPanel mainTable2 = new JPanel();
	private Table serviceInforTable = new Table();
	private JLabel totalService = new JLabel();
	
	public BillDetailView() {
		
		billID.setBounds(50,20,250,30);
		billID.setPreferredSize(new Dimension(250,30));
		billID.setFont(new Font("Arial",Font.BOLD,24));
		billID.setForeground(Color.BLACK);
		billID.setBackground(Color.WHITE);
		billID.setBorder(null);
		
		exportPDFButton.setBounds(540, 20, 100, 40);
		exportPDFButton.setText("Export PDF");
		exportPDFButton.setFont(new Font("Arial",Font.BOLD,12));
		exportPDFButton.setForeground(Color.WHITE);
		exportPDFButton.setBackground(new Color(39,162,187));
		exportPDFButton.setFocusable(false);
		exportPDFButton.addActionListener(actionListener);
		
		guestName.setBounds(50,90,250,30);
		guestName.setFont(new Font("Arial",Font.BOLD,14));
		guestName.setForeground(Color.BLACK);
		guestName.setBackground(Color.WHITE);
		guestName.setBorder(null);
		
		date.setBounds(50,130,180,30);
		date.setFont(new Font("Arial",Font.BOLD,14));
		date.setForeground(Color.BLACK);
		date.setBackground(Color.WHITE);
		date.setBorder(null);
		
		invoicingStaff.setBounds(400,90,320,30);
		invoicingStaff.setFont(new Font("Arial",Font.BOLD,14));
		invoicingStaff.setForeground(Color.BLACK);
		invoicingStaff.setBackground(Color.WHITE);
		invoicingStaff.setBorder(null);
		
		totalMoney.setBounds(400,140,250,30);
		totalMoney.setFont(new Font("Arial",Font.BOLD,14));
		totalMoney.setForeground(Color.BLACK);
		totalMoney.setBackground(Color.WHITE);
		totalMoney.setBorder(null);
	
		mainTable1.setBounds(50,220,600,67);
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
		roomInforTable.setColumnAlignment(4, JLabel.CENTER);
		roomInforTable.setCellAlignment(4, JLabel.CENTER);
		roomInforTable.setFont(new Font("Arial",Font.BOLD,12));
		roomInforTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"Room", "Rental Option", "Unit Price", "Check In", "Check Out"}) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
		roomInforTable.setColumnWidth(0,100);
		roomInforTable.setColumnWidth(1,100);
		roomInforTable.setColumnWidth(2,100);
		roomInforTable.setColumnWidth(3,150);
		roomInforTable.setColumnWidth(4,150);
        
        totalRoom.setBounds(510,290,150,30);
        totalRoom.setFont(new Font("Arial",Font.BOLD,14));
        totalRoom.setForeground(Color.BLACK);
        totalRoom.setBackground(Color.WHITE);
        
        //service
        mainTable2.setBounds(50,360,600,127);
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
        
        totalService.setBounds(510,490,150,30);
        //totalService.setText("Total: 200.000");
        totalService.setFont(new Font("Arial",Font.BOLD,14));
        totalService.setForeground(Color.BLACK);
        totalService.setBackground(Color.WHITE);
        
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(700,600);
		//this.setBounds(0,0,700,600);
		this.setIconImage(image.getImage());
		this.setLayout(null);
		this.add(billID);
		this.add(exportPDFButton);
		this.add(guestName);
		this.add(date);
		this.add(invoicingStaff);
		this.add(totalMoney);
		this.add(mainTable1);
		this.add(totalRoom);
		this.add(mainTable2);
		this.add(totalService);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		//this.add(panel);
		this.setVisible(false);
	}

	public JLabel getBillID() {
		return billID;
	}

	public JLabel getGuestName() {
		return guestName;
	}

	public JLabel getDate() {
		return date;
	}

	public JLabel getInvoicingStaff() {
		return invoicingStaff;
	}

	public JLabel getTotalMoney() {
		return totalMoney;
	}

	public JLabel getTotalRoom() {
		return totalRoom;
	}

	public JLabel getTotalService() {
		return totalService;
	}
	
	public Table getRoomInforTable() {
		return roomInforTable;
	}

	public Table getServiceInforTable() {
		return serviceInforTable;
	}
}