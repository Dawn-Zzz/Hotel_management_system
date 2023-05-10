package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Timer;

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
	
}
