package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.BookRoomView;
import view.RoomInfor;
import view.RoomView;

public class RoomController implements ActionListener {
	private RoomView roomView;
	private BookRoomView addRoomView = new BookRoomView();
//	private RoomInfor roomInfor = new RoomInfor("aa");
	
	public RoomController(RoomView roomView){
		super();
		this.roomView = roomView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Book Room")) {
			addRoomView.setVisible(true);
		}	
		for(int i = 0; i < 36; i++) {
			if(e.getActionCommand().equals("10" + (i+1))) {
				RoomInfor roomInfor = new RoomInfor("10" + (i+1),null,null,roomView.getCurrentStatus()[i],null);
				roomInfor.setVisible(true);
			}
			else if(e.getActionCommand().equals("20" + (i-6+1))) {
				RoomInfor roomInfor = new RoomInfor("20" + (i-6+1),null,null,roomView.getCurrentStatus()[i],null);
				roomInfor.setVisible(true);
			}
			else if(e.getActionCommand().equals("30" + (i-12+1))) {
				RoomInfor roomInfor = new RoomInfor("30" + (i-12+1),null,null,roomView.getCurrentStatus()[i],null);
				roomInfor.setVisible(true);
			}
			else if(e.getActionCommand().equals("40" + (i-18+1))) {
				RoomInfor roomInfor = new RoomInfor("40" + (i-18+1),null,null,roomView.getCurrentStatus()[i],null);
				roomInfor.setVisible(true);
			}
			else if(e.getActionCommand().equals("50" + (i-24+1))) {
				RoomInfor roomInfor = new RoomInfor("50" + (i-24+1),null,null,roomView.getCurrentStatus()[i],null);
				roomInfor.setVisible(true);
			}
			else if(e.getActionCommand().equals("60" + (i-30+1))) {
				RoomInfor roomInfor = new RoomInfor("60" + (i-30+1),roomView.getCurrentStatus()[i],null,null,null);
				roomInfor.setVisible(true);
			}
		}
		JPanel historyTemp = roomView.getHistoryRoomList();
		JPanel mainTemp = roomView.getMainRoomList();
		JPanel roomSearchTemp = roomView.getRoomSearch();
		JPanel statusSearch = roomView.getStatusSearch();
		JTextField searchBox = roomView.getSearchBox();
		if(e.getActionCommand().equals("Room History")) {
			historyTemp.setVisible(true);
			mainTemp.setVisible(false);
			roomSearchTemp.setVisible(true);
			statusSearch.setVisible(true);
			searchBox.setVisible(true);
			roomView.setHistoryRoomList(historyTemp);
			roomView.setMainRoomList(mainTemp);
		}
		else if(e.getActionCommand().equals("Back Button")) {
			historyTemp.setVisible(false);
			mainTemp.setVisible(true);
			roomSearchTemp.setVisible(false);
			statusSearch.setVisible(false);
			searchBox.setVisible(false);
			roomView.setHistoryRoomList(historyTemp);
			roomView.setMainRoomList(mainTemp);
		}
	}

}
