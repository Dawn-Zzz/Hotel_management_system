package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddRoomView;
import view.RoomView;

public class RoomController implements ActionListener {
	private RoomView roomView;
	private AddRoomView addRoomView = new AddRoomView();
	
	public RoomController(RoomView roomView){
		super();
		this.roomView = roomView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Edit Room")) {
			addRoomView.setVisible(true);
		}	
	}

}
