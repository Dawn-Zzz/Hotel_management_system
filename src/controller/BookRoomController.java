package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import DAO.ReservationDAO;
import model.Reservation;
import model.Room;
import view.BookRoomView;
import view.RoomView;

public class BookRoomController implements ActionListener, ItemListener, PropertyChangeListener {
	private BookRoomView bookRoomView;
	private RoomView roomView = RoomView.getInstance();
	private boolean isSettingTime = false;

	public BookRoomController(BookRoomView bookRoomView) {
		super();
		this.bookRoomView = bookRoomView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Submit")) {
			this.bookRoomView.addGuestAction();
			roomView.resetRoomTable();
		} else if (e.getSource() == bookRoomView.getQuestQuantityBox()) {
			bookRoomView.addItemComboboxRoom();
			bookRoomView.getRoomBox().setEnabled(true);
			bookRoomView.getRoomBox().setSelectedIndex(-1);
		} else if (e.getSource() == bookRoomView.getRentalTypeBox()) {
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Ngày")) {
				bookRoomView.setComboBoxRentForTheDay();
			} else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Đêm")) {
				bookRoomView.setComboBoxOvernightRental();
				bookRoomView.getDayCOut()
						.setDate(Date.from(bookRoomView.getDayCIn().getDate().toInstant().atZone(ZoneId.systemDefault())
								.toLocalDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
				bookRoomView.getDayCOut().setMinSelectableDate(
						Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				bookRoomView.getDayCOut().setMaxSelectableDate(
						Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				bookRoomView.getHourCIn().removeAllItems();
				for (int hour = 20; hour <= 23; hour++) {
				    String hourString = String.format("%02d", hour);
				    bookRoomView.getHourCIn().addItem(hourString);
				}
			} else {
				bookRoomView.setComboBoxRentByTheHour();
				bookRoomView.setInitialTime();
				bookRoomView.updateMinuteComboBox();
				bookRoomView.setTimeOneHourAfterCheckin();
			}
		} else if (e.getSource() == bookRoomView.getMinuteCIn() || e.getSource() == bookRoomView.getHourCIn()) {
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Giờ"))
				if (bookRoomView.getHourCIn().getSelectedItem() != null && bookRoomView.getMinuteCIn().getSelectedItem() != null) {
					bookRoomView.updateMinuteComboBox();
					bookRoomView.setTimeOneHourAfterCheckin();
				}
					
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isSettingTime) {
			return;
		}
		if (evt.getSource() == bookRoomView.getDayCIn() || evt.getSource() == bookRoomView.getDayCOut()) {
			Date currentDate = new Date(); // Ngày hiện tại
			bookRoomView.getDayCIn().setMinSelectableDate(Date.from(LocalDate.now().plusDays(LocalTime.now().isAfter(LocalTime.of(23, 30)) ? 1 : 0).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			bookRoomView.getDayCOut().setMinSelectableDate(bookRoomView.getDayCIn().getDate());
			bookRoomView.getDayCOut().setMaxSelectableDate(null);
			// Nếu đang là theo ngày
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Ngày")) {
				if (evt.getSource() == bookRoomView.getDayCIn())
					bookRoomView.getDayCOut().setDate(
							Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				bookRoomView.getDayCIn().setMinSelectableDate(currentDate);
				bookRoomView.getDayCOut().setMinSelectableDate(
						Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
			}
			// Nếu đang là theo đêm
			else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Đêm")) {
				if (evt.getSource() == bookRoomView.getDayCIn()) {
					bookRoomView.getDayCOut()
							.setDate(Date.from(bookRoomView.getDayCIn().getDate().toInstant()
									.atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1)
									.atStartOfDay(ZoneId.systemDefault()).toInstant()));
					bookRoomView.getDayCOut().setMinSelectableDate(
							Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
					bookRoomView.getDayCOut().setMaxSelectableDate(
							Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				}
			}
			// Nếu đang là theo giờ
			else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Giờ")) {
				isSettingTime = true;
				if (evt.getSource() == bookRoomView.getDayCIn()) {
					bookRoomView.setInitialTime();
					bookRoomView.updateMinuteComboBox();
					bookRoomView.setTimeOneHourAfterCheckin();
				}
				else {
					if (!(bookRoomView.getDayCOut().getDate().equals(bookRoomView.getDayCIn().getDate()))) {
						bookRoomView.getHourCOut().removeAllItems();
						for (int hour = 0; hour <= 23; hour++) {
							String hourString = String.format("%02d", hour);
							bookRoomView.getHourCOut().addItem(hourString);
						}
					}
				}
				bookRoomView.getDayCOut().setMaxSelectableDate(Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				isSettingTime = false;
			}
		}
	}
}
