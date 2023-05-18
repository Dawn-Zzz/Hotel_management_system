package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.BookRoomView;
import view.RoomInfor;
import view.RoomView;

public class RoomController implements ActionListener {
	private RoomView roomView;
	private BookRoomView addRoomView;
	
	public RoomController(RoomView roomView){
		super();
		this.roomView = roomView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Book Room")) {
			addRoomView = new BookRoomView();
			addRoomView.setVisible(true);
		} 
		else if(e.getActionCommand().equals("Room History")) {
			roomView.getHistoryRoomList().setVisible(true);
			roomView.getMainRoomList().setVisible(false);
			roomView.getRoomSearch().setVisible(true);
			roomView.getStatusSearch().setVisible(true);
			roomView.getSearchBox().setVisible(true);
			roomView.setHistoryRoomList(roomView.getHistoryRoomList());
			roomView.setMainRoomList(roomView.getMainRoomList());
		}
		else if(e.getActionCommand().equals("Back Button")) {
			roomView.getHistoryRoomList().setVisible(false);
			roomView.getMainRoomList().setVisible(true);
			roomView.getRoomSearch().setVisible(false);
			roomView.getStatusSearch().setVisible(false);
			roomView.getSearchBox().setVisible(false);
			roomView.setHistoryRoomList(roomView.getHistoryRoomList());
			roomView.setMainRoomList(roomView.getMainRoomList());
		} 
		else {
			for(int i = 0; i < roomView.getRoomButtonList().length; i++) {
			    JButton button = roomView.getRoomButtonList()[i];
			    if (e.getSource() == button) {
			        String roomNumber = button.getText();
			        RoomInfor roomInfor;
			        if (roomView.getRoomList().get(i).getCurrentStatus().equals("0"))
			        	roomInfor = new RoomInfor(roomNumber, null, null, "Trống", null);
			        else 
			        	roomInfor = new RoomInfor(roomNumber, null, null, "Đã cho thuê", null);
			        roomInfor.setVisible(true);
			        break;
			    }
			}
		}
		SearchTable(roomView.getRoomTable(), roomView.getSearchBox());
	}
	
	private void SearchTable(JTable table, JTextField textField) {
		String rentalType = "";
		if (roomView.getRentalTypeList().getSelectedItem() != null) {
			if (roomView.getRentalTypeList().getSelectedItem() == "All")
				rentalType = "";
			else
				rentalType = (String) roomView.getRentalTypeList().getSelectedItem();
		}
		
		String currentStatus = "";
		if (roomView.getStatusList().getSelectedItem() != null) {
			if (roomView.getStatusList().getSelectedItem() == "All")
				currentStatus = "";
			else
				currentStatus = (String) roomView.getStatusList().getSelectedItem();
		}
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowFilter<Object,Object>> filters = new ArrayList<>();

        RowFilter<Object,Object> filter1 = RowFilter.regexFilter("(?i)" + rentalType,2);
		RowFilter<Object,Object> filter2 = RowFilter.regexFilter("(?i)" + currentStatus,6);
		filters.add(filter1);
		filters.add(filter2);
		sorter.setRowFilter(RowFilter.andFilter(filters));
		textField.setText("");
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim();
//				if (input.length() == 0) {
//					sorter1.setRowFilter(null);
//				} else {
//					sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + input));
//				}
				filters.clear();
	            RowFilter<Object,Object> filter3 = RowFilter.regexFilter("(?i)" + input, 0, 1,3,4);
	            filters.add(filter1);
	            filters.add(filter2);
	            filters.add(filter3);
	            sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
	}

}
