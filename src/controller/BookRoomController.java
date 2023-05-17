package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
		} else if (e.getSource() == bookRoomView.getQuestQuantityBox()) {
			addItemComboboxRoom();
			bookRoomView.getRoomBox().setEnabled(true);
			bookRoomView.getRoomBox().setSelectedIndex(-1);
		} else if (e.getSource() == bookRoomView.getRentalTypeBox()) {
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Rent For The Day")) {
				bookRoomView.setComboBoxRentForTheDay();
			} else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Overnight Rental")) {
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
				bookRoomView.updateMinuteComboBox();
				bookRoomView.setTimeOneHourAfterCheckin();
			}
		} else if (e.getSource() == bookRoomView.getMinuteCIn() || e.getSource() == bookRoomView.getHourCIn()) {
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Rent By The Hour"))
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

	public void addItemComboboxRoom() {
		int amount = 0;
		if (bookRoomView.getQuestQuantityBox().getSelectedItem() != null)
			amount = (int) bookRoomView.getQuestQuantityBox().getSelectedItem();
		List<String> roomValues = new ArrayList<>();
		if (amount == 1) {
			checkReservation(roomValues, roomView.getRoomList(), 1, 2);
		} else if (amount == 2) {
			checkReservation(roomValues, roomView.getRoomList(), 2, 4);
		} else if (amount == 3) {
			checkReservation(roomValues, roomView.getRoomList(), 4, 5);
		} else if (amount == 4) {
			checkReservation(roomValues, roomView.getRoomList(), 4, 6);
		} else if (amount > 4 && amount < 7) {
			checkReservation(roomValues, roomView.getRoomList(), 5, 6);
		} else {
			checkReservation(roomValues, roomView.getRoomList(), 6, 6);
		}
		bookRoomView.getRoomBox().setModel(new DefaultComboBoxModel(roomValues.toArray(new String[0])));
	}

	public void checkReservation(List<String> roomValues, ArrayList<Room> rooms, int begin, int end) {
		Timestamp checkInTime = setTime(Integer.parseInt(bookRoomView.getMinuteCIn().getSelectedItem().toString()),
				Integer.parseInt(bookRoomView.getHourCIn().getSelectedItem().toString()),
				bookRoomView.getDayCIn().getDate());
		Timestamp checkOutTime = setTime(Integer.parseInt(bookRoomView.getMinuteCOut().getSelectedItem().toString()),
				Integer.parseInt(bookRoomView.getHourCOut().getSelectedItem().toString()),
				bookRoomView.getDayCOut().getDate());
		for (Room room : rooms) {
			String roomName = room.getNumberRoom();
			int roomNumber = Integer.parseInt(roomName);

			if ((roomNumber % 100 >= begin) && (roomNumber % 100 <= end)) {
				ArrayList<Reservation> reservations = ReservationDAO.getInstance()
						.getReservationNotCheckInByIDRoom(roomName);
				if (reservations == null) {
					roomValues.add(roomName);
				} else {
					boolean isAvailable = true;
					for (Reservation reservation : reservations) {
						if (!isBookingTimeAvailable(reservation, checkInTime, checkOutTime)) {
							isAvailable = false;
							break;
						}
					}
					if (isAvailable) {
						roomValues.add(roomName);
					}
				}
			}
		}
	}

	public boolean isBookingTimeAvailable(Reservation reservation, Timestamp checkInTime, Timestamp checkOutTime) {
		Timestamp startTime = reservation.getCheckIn();
		Timestamp endTime = reservation.getCheckOut();
		// Kiểm tra nếu thời gian đặt trước nằm ngoài khoảng thời gian check-in và
		// check-out
		if (checkInTime.after(endTime)) {
			long diff = startTime.getTime() - checkOutTime.getTime();
			long diffHours = diff / (60 * 60 * 1000);
			return (diffHours < 1);
		}
		if (checkOutTime.before(startTime)) {
			// Kiểm tra thời gian checkout phải trước thời gian start time một tiếng
			long diff = startTime.getTime() - checkOutTime.getTime();
			long diffHours = diff / (60 * 60 * 1000);
			return (diffHours < 1);
		}
		return false;
	}

	public Timestamp setTime(int minute, int hour, Date date) {
		// Chuyển đổi từ java.util.Date sang java.time.LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

		// Thiết lập giờ và phút cho LocalDateTime
		localDateTime = localDateTime.withHour(hour).withMinute(minute);

		// Chuyển đổi từ java.time.LocalDateTime sang java.sql.Timestamp
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		return timestamp;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isSettingTime) {
			return;
		}
		if (evt.getSource() == bookRoomView.getDayCIn() || evt.getSource() == bookRoomView.getDayCOut()) {
			Date currentDate = new Date(); // Ngày hiện tại
			bookRoomView.getDayCIn().setMinSelectableDate(currentDate);
			bookRoomView.getDayCOut().setMinSelectableDate(bookRoomView.getDayCIn().getDate());
			bookRoomView.getDayCOut().setMaxSelectableDate(null);
			// Nếu đang là theo ngày
			if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Rent For The Day")) {
				if (evt.getSource() == bookRoomView.getDayCIn())
					bookRoomView.getDayCOut().setDate(
							Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
				bookRoomView.getDayCIn().setMinSelectableDate(currentDate);
				bookRoomView.getDayCOut().setMinSelectableDate(
						Date.from(bookRoomView.getDayCIn().getDate().toInstant().plus(1, ChronoUnit.DAYS)));
			}
			// Nếu đang là theo đêm
			else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Overnight Rental")) {
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
			else if (bookRoomView.getRentalTypeBox().getSelectedItem().equals("Rent By The Hour")) {
				isSettingTime = true;
				if (evt.getSource() == bookRoomView.getDayCIn()) {
					bookRoomView.updateMinuteComboBox();
					bookRoomView.setInitialTime();
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
