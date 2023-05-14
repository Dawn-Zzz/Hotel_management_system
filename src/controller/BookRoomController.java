package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;

import view.AddGuestView;
import view.BookRoomView;

public class BookRoomController implements ActionListener, ItemListener  {
	private BookRoomView bookRoomView;

	public BookRoomController(BookRoomView bookRoomView) {
		super();
		this.bookRoomView = bookRoomView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Submit")) {
			this.bookRoomView.addGuestAction();
		}
		else if (e.getSource() == bookRoomView.getQuestQuantityBox()) {
			addItemComboboxRoom();
			bookRoomView.getRoomBox().setSelectedIndex(-1);
			bookRoomView.getRoomBox().setEnabled(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		boolean isEditable = bookRoomView.getAdvanceBooking().isSelected();
		bookRoomView.getDepositField().setEnabled(isEditable);
        if (isEditable) {
        	bookRoomView.getDepositField().setBackground(Color.WHITE);
        } else {
        	bookRoomView.getDepositField().setText("");
        	bookRoomView.getDepositField().setBackground(new Color(240, 240, 240));
        }
	}
	
	public void addItemComboboxRoom() {
		int amount = (int) bookRoomView.getQuestQuantityBox().getSelectedItem();
		List<String> roomValues = new ArrayList<>();
		if (amount == 1) {
			for (int i = 1; i <= 6; i++) {
			    roomValues.add(i + "01");
			    roomValues.add(i + "02");
			}
		} else if (amount == 2) {
			for (int i = 1; i <= 6; i++) {
				roomValues.add(i + "02");
			    roomValues.add(i + "03");
			    roomValues.add(i + "04");
			}
		} else if (amount == 3) {
			for (int i = 1; i <= 6; i++) {
				roomValues.add(i + "04");
			    roomValues.add(i + "05");
			}
		} else if (amount == 4) {
			for (int i = 1; i <= 6; i++) {
				roomValues.add(i + "04");
			    roomValues.add(i + "05");
			    roomValues.add(i + "06");
			}
		} else if (amount > 4 && amount < 7) {
			for (int i = 1; i <= 6; i++) {
				roomValues.add(i + "05");
			    roomValues.add(i + "06");
			}
		} else {
			for (int i = 1; i <= 6; i++) {
			    roomValues.add(i + "06");
			}
		}
		bookRoomView.getRoomBox().setModel(new DefaultComboBoxModel(roomValues.toArray(new String[0])));
	}
}
