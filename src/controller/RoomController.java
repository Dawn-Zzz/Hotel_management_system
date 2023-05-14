package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

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
	}

}
