package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAO.GuestDAO;
import DAO.ReservationDAO;
import controller.BookRoomController;
import model.Reservation;
import model.Room;
import view.editComponent.Button;
import view.editComponent.Combobox;

public class BookRoomView extends JDialog {
	ImageIcon image = new ImageIcon("./Images/whiteLogo.png");
	private JLabel registrationForm = new JLabel();

	private LocalDate toDay = LocalDate.now();// láº¥y ngÃ y hiá»‡n táº¡i
	// Ä‘á»‹nh dáº¡ng ngÃ y thÃ¡ng nÄƒm
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date = toDay.format(formatter);
	private JLabel dateTime = new JLabel();

	private JLabel questQuantity = new JLabel();
	private Combobox<Integer> questQuantityBox = new Combobox<>();

	private JLabel rentalType = new JLabel();
	String RentalType[] = { "Giờ", "Ngày", "Đêm" };
	private Combobox<String> rentalTypeBox = new Combobox<>();

	private JLabel room = new JLabel();
	private Combobox roomBox;

	private JLabel identificationNumber = new JLabel();
	private JTextField identificationNumberField = new JTextField();

	private JLabel checkIn = new JLabel();
	private Combobox<String> hourCIn;
	private JLabel decorationThing1 = new JLabel();
	private Combobox<String> minuteCIn;
	private JDateChooser dayCIn = new JDateChooser();

	private JLabel checkOut = new JLabel();
	private Combobox<String> hourCOut;
	private JLabel decorationThing2 = new JLabel();
	private Combobox<String> minuteCOut;
	private JDateChooser dayCOut = new JDateChooser();

	private JPanel bookingGroup1 = new JPanel();
	private JPanel bookingGroup2 = new JPanel();
	private ButtonGroup groupRadioButton = new ButtonGroup();
	private JRadioButton directBooking = new JRadioButton();
	private JRadioButton advanceBooking = new JRadioButton();
	private JLabel deposit = new JLabel();
	private JTextField depositField = new JTextField();
	private JLabel currencyUnit = new JLabel();

	private JButton submitButton = new Button();
	
	private RoomView roomView = RoomView.getInstance();
	
	private ActionListener actionListener = new BookRoomController(this);
	private ItemListener itemListener = new BookRoomController(this);
	private PropertyChangeListener propertyChangeListener = new BookRoomController(this);

	
	public BookRoomView() {
		// khá»Ÿi táº¡o giÃ¡ trá»‹ cÃ¡c phÃ²ng

		// Khá»Ÿi táº¡o máº£ng giá»�
		String[] hours = new String[24];
		for (int i = 0; i < 24; i++) {
			hours[i] = String.format("%02d", i);
		}
		
		// Khá»Ÿi táº¡o máº£ng phÃºt
		String[] minutes = { "00", "30" };

		registrationForm.setBounds(50, 10, 250, 30);
		registrationForm.setText("Registration Form");
		registrationForm.setPreferredSize(new Dimension(250, 30));
		registrationForm.setFont(new Font("Arial", Font.BOLD, 24));
		registrationForm.setForeground(Color.BLACK);
		registrationForm.setBackground(Color.WHITE);
		registrationForm.setBorder(null);

		dateTime.setBounds(675, 10, 90, 30);
		dateTime.setText(date);
		dateTime.setPreferredSize(new Dimension(250, 30));
		dateTime.setFont(new Font("Arial", Font.BOLD, 16));
		dateTime.setForeground(Color.BLACK);
		dateTime.setBackground(Color.WHITE);
		dateTime.setBorder(null);

		questQuantity.setBounds(50, 80, 150, 30);
		questQuantity.setText("Guest Count");
		questQuantity.setFont(new Font("Arial", Font.BOLD, 14));
		questQuantity.setForeground(Color.BLACK);
		questQuantity.setBackground(Color.WHITE);
		questQuantity.setBorder(null);

		questQuantityBox.setBounds(50, 110, 340, 30);
		questQuantityBox.setBackground(Color.WHITE);
		questQuantityBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		for (int i = 1; i <= 8; i++) {
			questQuantityBox.addItem(i);
		}
		questQuantityBox.setSelectedIndex(-1);

		rentalType.setBounds(50, 160, 120, 30);
		rentalType.setText("Rental Type");
		rentalType.setFont(new Font("Arial", Font.BOLD, 14));
		rentalType.setForeground(Color.BLACK);
		rentalType.setBackground(Color.WHITE);
		rentalType.setBorder(null);

		rentalTypeBox.setBounds(50, 190, 340, 30);
		rentalTypeBox.setModel(new DefaultComboBoxModel(RentalType));
		rentalTypeBox.setBackground(Color.WHITE);
		rentalTypeBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		rentalTypeBox.setFocusable(false);

		identificationNumber.setBounds(420, 80, 180, 30);
		identificationNumber.setText("Identification Number");
		identificationNumber.setFont(new Font("Arial", Font.BOLD, 14));
		identificationNumber.setForeground(Color.BLACK);
		identificationNumber.setBackground(Color.WHITE);

		identificationNumberField.setBounds(420, 110, 340, 30);
		identificationNumberField.setBackground(Color.WHITE);
		identificationNumberField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));

		room.setBounds(420, 160, 150, 30);
		room.setText("Room");
		room.setFont(new Font("Arial", Font.BOLD, 14));
		room.setForeground(Color.BLACK);
		room.setBackground(Color.WHITE);
		room.setBorder(null);

		roomBox = new Combobox();
		roomBox.setMaximumRowCount(4);
		roomBox.setBounds(420, 190, 340, 30);
		roomBox.setBackground(Color.WHITE);
		roomBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
		roomBox.setEnabled(false);
		roomBox.setFocusable(false);

		checkIn.setBounds(50, 240, 150, 30);
		checkIn.setText("Check In");
		checkIn.setFont(new Font("Arial", Font.BOLD, 14));
		checkIn.setForeground(Color.BLACK);
		checkIn.setBackground(Color.WHITE);

		hourCIn = new Combobox();
		hourCIn.setMaximumRowCount(4);
		hourCIn.setBounds(50, 270, 70, 30);
		hourCIn.setModel(new DefaultComboBoxModel(hours));
		hourCIn.setBackground(Color.WHITE);
		hourCIn.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(204, 204, 204)));
		hourCIn.setFocusable(false);

		decorationThing1.setBounds(128, 270, 10, 30);
		decorationThing1.setText(":");
		decorationThing1.setFont(new Font("Arial", Font.BOLD, 18));
		decorationThing1.setForeground(Color.BLACK);
		decorationThing1.setBackground(Color.WHITE);

		minuteCIn = new Combobox<>();
		minuteCIn.setMaximumRowCount(4);
		minuteCIn.setBounds(140, 270, 70, 30);
		minuteCIn.setModel(new DefaultComboBoxModel(minutes));
		minuteCIn.setBackground(Color.WHITE);
		minuteCIn.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(220, 220, 220)));
		minuteCIn.setFocusable(false);

		dayCIn.setBounds(230, 270, 160, 30);
		dayCIn.setBackground(Color.WHITE);
//		dayCIn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		dayCIn.setDate(java.sql.Date.valueOf(toDay));

		checkOut.setBounds(420, 240, 150, 30);
		checkOut.setText("Check Out");
		checkOut.setFont(new Font("Arial", Font.BOLD, 14));
		checkOut.setForeground(Color.BLACK);
		checkOut.setBackground(Color.WHITE);

		hourCOut = new Combobox();
		hourCOut.setMaximumRowCount(4);
		hourCOut.setBounds(420, 270, 70, 30);
		hourCOut.setModel(new DefaultComboBoxModel(hours));
		hourCOut.setBackground(Color.WHITE);
		hourCOut.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(220, 220, 220)));
		hourCOut.setFocusable(false);

		decorationThing2.setBounds(498, 270, 10, 30);
		decorationThing2.setText(":");
		decorationThing2.setFont(new Font("Arial", Font.BOLD, 18));
		decorationThing2.setForeground(Color.BLACK);
		decorationThing2.setBackground(Color.WHITE);

		minuteCOut = new Combobox<>();
		minuteCOut.setMaximumRowCount(4);
		minuteCOut.setBounds(510, 270, 70, 30);
		minuteCOut.setModel(new DefaultComboBoxModel(minutes));
		minuteCOut.setBackground(Color.WHITE);
		minuteCOut.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(220, 220, 220)));
		minuteCOut.setFocusable(false);

		dayCOut.setBounds(600, 270, 160, 30);
		dayCOut.setBackground(Color.WHITE);
//		dayCOut.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		dayCOut.setDate(java.sql.Date.valueOf(toDay));

		bookingGroup1.setBounds(45, 340, 140, 30);
		bookingGroup1.setForeground(Color.BLACK);
		bookingGroup1.setBackground(Color.WHITE);
		bookingGroup1.setBorder(null);
		bookingGroup1.add(directBooking);

		bookingGroup2.setBounds(230, 340, 140, 30);
		bookingGroup2.setForeground(Color.BLACK);
		bookingGroup2.setBackground(Color.WHITE);
		bookingGroup2.setBorder(null);
		bookingGroup2.add(advanceBooking);

		groupRadioButton.add(directBooking);
		groupRadioButton.add(advanceBooking);

		directBooking.setBounds(0, 0, 10, 10);

		directBooking.setText("Direct Booking");
		directBooking.setFont(new Font("Arial", Font.BOLD, 14));
		directBooking.setBackground(Color.WHITE);
		directBooking.setFocusable(false);
		directBooking.setSelected(true);

		advanceBooking.setBounds(0, 0, 10, 10);
		advanceBooking.setFont(new Font("Arial", Font.BOLD, 14));
		advanceBooking.setText("Advance Booking");
		advanceBooking.setBackground(Color.WHITE);
		advanceBooking.setFocusable(false);

		deposit.setBounds(420, 340, 60, 30);
		deposit.setText("Deposit");
		deposit.setFont(new Font("Arial", Font.BOLD, 14));
		deposit.setForeground(Color.BLACK);
		deposit.setBackground(Color.WHITE);

		depositField.setBounds(480, 340, 180, 30);
		depositField.setBackground(Color.WHITE);
//		depositField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		depositField.setEnabled(false);
		depositField.setBackground(new Color(240, 240, 240));

		currencyUnit.setBounds(665, 340, 150, 30);
		currencyUnit.setText("VND");
		currencyUnit.setFont(new Font("Arial", Font.BOLD, 16));
		currencyUnit.setForeground(Color.BLACK);
		currencyUnit.setBackground(Color.WHITE);
		registrationForm.setBorder(null);

		submitButton.setBounds(50, 440, 100, 40);
		submitButton.setText("Submit");
		submitButton.setFont(new Font("Arial", Font.BOLD, 14));
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(39, 162, 187));
		submitButton.setFocusable(false);

		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(0, 0, 800, 560);
		this.setIconImage(image.getImage());
		this.setLayout(null);
		this.add(registrationForm);
		this.add(dateTime);
		this.add(questQuantity);
		this.add(questQuantityBox);
		this.add(rentalType);
		this.add(rentalTypeBox);
		this.add(room);
		this.add(roomBox);
		this.add(identificationNumber);
		this.add(identificationNumberField);
		this.add(checkIn);
		this.add(hourCIn);
		this.add(decorationThing1);
		this.add(minuteCIn);
		this.add(dayCIn);
		this.add(checkOut);
		this.add(hourCOut);
		this.add(decorationThing2);
		this.add(minuteCOut);
		this.add(dayCOut);
		this.add(bookingGroup1);
		this.add(bookingGroup2);
		this.add(deposit);
		this.add(depositField);
		this.add(currencyUnit);
		this.add(submitButton);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(false);

		setInitialTime();
		updateMinuteComboBox();
		setTimeOneHourAfterCheckin();

		questQuantityBox.addActionListener(actionListener);
		rentalTypeBox.addActionListener(actionListener);

		hourCIn.addActionListener(actionListener);
		minuteCIn.addActionListener(actionListener);
		dayCIn.addPropertyChangeListener(propertyChangeListener);

		hourCOut.addActionListener(actionListener);
		minuteCOut.addActionListener(actionListener);
		dayCOut.addPropertyChangeListener(propertyChangeListener);

		advanceBooking.addItemListener(itemListener);
		submitButton.addActionListener(actionListener);
	}

	public JTextField getidentificationNumberField() {
		return identificationNumberField;
	}

	public JTextField getDepositField() {
		return depositField;
	}

	public JRadioButton getAdvanceBooking() {
		return advanceBooking;
	}

	public Combobox<Integer> getQuestQuantityBox() {
		return questQuantityBox;
	}

	public Combobox getRoomBox() {
		return roomBox;
	}

	public JLabel getRentalType() {
		return rentalType;
	}

	public Combobox<String> getRentalTypeBox() {
		return rentalTypeBox;
	}

	public Combobox<String> getHourCIn() {
		return hourCIn;
	}

	public Combobox<String> getMinuteCIn() {
		return minuteCIn;
	}

	public JDateChooser getDayCIn() {
		return dayCIn;
	}

	public Combobox<String> getHourCOut() {
		return hourCOut;
	}

	public Combobox<String> getMinuteCOut() {
		return minuteCOut;
	}

	public JDateChooser getDayCOut() {
		return dayCOut;
	}

	public void addGuestAction() {
		Timestamp checkInTime = setTime(Integer.parseInt(minuteCIn.getSelectedItem().toString()),
				Integer.parseInt(hourCIn.getSelectedItem().toString()),
				dayCIn.getDate());
		Timestamp checkOutTime = setTime(Integer.parseInt(minuteCOut.getSelectedItem().toString()),
				Integer.parseInt(hourCOut.getSelectedItem().toString()),
				dayCOut.getDate());
		String id = identificationNumberField.getText();
		if (id.isEmpty() || questQuantityBox.getSelectedItem() == null || rentalTypeBox.getSelectedItem() == null
				|| roomBox.getSelectedItem() == null)
			JOptionPane.showMessageDialog(this, "Không được bỏ trống");
		else if (!id.matches("\\d{12}"))
			JOptionPane.showMessageDialog(this, "ID phải có 12 số");
		else if (GuestDAO.getInstance().getGuestById(id) == null)
			JOptionPane.showMessageDialog(this, "ID khách hàng không tồn tại. Hãy thêm khách hàng", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		else {
			if (directBooking.isSelected()) {
				ReservationDAO.getInstance().insert(id, (String) rentalTypeBox.getSelectedItem(), (String) roomBox.getSelectedItem(), checkInTime, checkOutTime, (int) questQuantityBox.getSelectedItem(), null);
				this.dispose();
			}
			else {
				if (deposit.getText().isEmpty())
					JOptionPane.showMessageDialog(this, "Không được bỏ trống");
				else {
					Double depositAmount = Double.parseDouble(depositField.getText().trim());
					if(depositAmount != null) {
			            ReservationDAO.getInstance().insert(id, (String) rentalTypeBox.getSelectedItem(), (String) roomBox.getSelectedItem(), checkInTime, checkOutTime, (int) questQuantityBox.getSelectedItem(), depositAmount);
						this.dispose();
			        } else 
			            JOptionPane.showMessageDialog(this, "Giá trị cọc không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
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
	
	public void checkReservation(List<String> roomValues, ArrayList<Room> rooms, int begin, int end) {
		Timestamp checkInTime = setTime(Integer.parseInt(minuteCIn.getSelectedItem().toString()),
				Integer.parseInt(hourCIn.getSelectedItem().toString()),
				dayCIn.getDate());
		Timestamp checkOutTime = setTime(Integer.parseInt(minuteCOut.getSelectedItem().toString()),
				Integer.parseInt(hourCOut.getSelectedItem().toString()),
				dayCOut.getDate());
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
						if (!isBookingTimeAvailable(reservation, checkInTime, checkOutTime) || reservation.getRoom().getCurrentStatus().equals("2")) {
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

	public void addItemComboboxRoom() {
		int amount = 0;
		if (questQuantityBox.getSelectedItem() != null)
			amount = (int) questQuantityBox.getSelectedItem();
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
		roomBox.setModel(new DefaultComboBoxModel(roomValues.toArray(new String[0])));
	}
	
	public void setInitialTime() {
		if (dayCIn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(toDay)) {
			boolean check = false;
			LocalTime currentTime = LocalTime.now(); // Thời gian hiện tại
			int currentHour = currentTime.getHour();
			int currentMinute = currentTime.getMinute();

			if (currentMinute >= 0 && currentMinute < 30) {
				currentMinute = 30;
			} else {
				currentHour++;
				if (currentHour == 24) {
					currentHour = 0;
					check = true;
				}
				currentMinute = 0;
			}
			if (check) 
				dayCIn.setDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

			hourCIn.setSelectedItem(String.format("%02d", currentHour));
			minuteCIn.setSelectedItem(String.format("%02d", currentMinute));
		}
	}

	public void setTimeOneHourAfterCheckin() {
		// Lấy ngày từ startDateChooser
		Date startDate = dayCIn.getDate();

		// Lấy giờ và phút từ combobox
		int hour = Integer.parseInt(hourCIn.getSelectedItem().toString());
		int minute = Integer.parseInt(minuteCIn.getSelectedItem().toString());

		// Tạo một đối tượng Calendar và thiết lập thời gian từ startDateChooser
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);

		// Thêm 1 tiếng vào thời gian
		calendar.add(Calendar.HOUR_OF_DAY, 1);

		// Lấy thời gian mới từ calendar
		Date endDate = calendar.getTime();

		hourCOut.removeAllItems();
		for (int h = calendar.get(Calendar.HOUR_OF_DAY); h <= 23; h++) {
			hourCOut.addItem(String.format("%02d", h));
		}

		// Đặt giờ và phút mới cho combobox
		hourCOut.setSelectedItem(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
		minuteCOut.setSelectedItem(String.format("%02d", calendar.get(Calendar.MINUTE)));
		// Đặt ngày mới cho endDateChooser
		dayCOut.setDate(endDate);
	}

	public void setComboBoxRentByTheHour() {
		hourCIn.setEnabled(true);
		minuteCIn.setEnabled(true);
		hourCOut.setEnabled(true);
		minuteCOut.setEnabled(true);
	}

	public void setComboBoxOvernightRental() {
		hourCIn.setEnabled(true);
		minuteCIn.setEnabled(true);
		hourCOut.addItem(String.format("%02d", 8));
		hourCOut.setSelectedItem("08");
		minuteCOut.setSelectedItem("00");
		hourCOut.setEnabled(false);
		minuteCOut.setEnabled(false);
	}

	public void setComboBoxRentForTheDay() {
		hourCIn.removeAllItems();
		hourCIn.addItem(String.format("%02d", 12));
		hourCOut.removeAllItems();
		hourCOut.addItem(String.format("%02d", 11));
		hourCIn.setEnabled(false);
		minuteCIn.setEnabled(false);
		hourCOut.setEnabled(false);
		minuteCOut.setEnabled(false);
		hourCIn.setSelectedItem("12");
		minuteCIn.setSelectedItem("00");
		hourCOut.setSelectedItem("11");
		minuteCOut.setSelectedItem("00");
		dayCOut.setDate(Date.from(dayCIn.getDate().toInstant().plus(1, ChronoUnit.DAYS)));
	}

	public void updateMinuteComboBox() {
		if (LocalTime.now().isAfter(LocalTime.of(23, 30))) {
			if (hourCIn.getItemCount() != 24) {
		        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		        for (int hour = 0; hour <= 23; hour++) {
		            String hourString = String.format("%02d", hour);
		            model.addElement(hourString);
		        }
		        hourCIn.setModel(model);
			}
		}
		else {
			if (dayCIn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(toDay)) {
				LocalTime currentTime = LocalTime.now(); // Thời gian hiện tại
				int currentHour = currentTime.getHour();
				int currentMinute = currentTime.getMinute();
				
				int selectedHour = Integer.parseInt(hourCIn.getSelectedItem().toString());
	
				minuteCIn.removeAllItems();
				if (selectedHour == currentHour) {
					if (minuteCIn.getItemCount() == 1 || minuteCIn.getSelectedIndex() == -1
							|| !minuteCIn.getItemAt(1).equals("30"))
						minuteCIn.addItem("30");
				} else {
					if (minuteCIn.getItemCount() == 0 || minuteCIn.getSelectedIndex() == -1
							|| !minuteCIn.getItemAt(0).equals("00"))
						minuteCIn.addItem("00");
					if (minuteCIn.getItemCount() == 1 || minuteCIn.getSelectedIndex() == -1
							|| !minuteCIn.getItemAt(1).equals("30"))
						minuteCIn.addItem("30");
				}
				
				if (currentMinute >= 0 && currentMinute < 30) {
					currentMinute = 30;
				} else {
					currentHour++;
					if (currentHour == 24) currentHour = 0;
					currentMinute = 0;
				}
				
				if (hourCIn.getItemCount() != (23 - currentHour + 1)) {
					hourCIn.removeAllItems();
					for (int hour = currentHour; hour <= 23; hour++) {
						String hourString = String.format("%02d", hour);
						hourCIn.addItem(hourString);
					}
				}
			} else {
				minuteCIn.removeAllItems();
				if (minuteCIn.getItemCount() == 0 || minuteCIn.getSelectedIndex() == -1
						|| !minuteCIn.getItemAt(0).equals("00"))
					minuteCIn.addItem("00");
				if (minuteCIn.getItemCount() == 1 || minuteCIn.getSelectedIndex() == -1
						|| !minuteCIn.getItemAt(1).equals("30"))
					minuteCIn.addItem("30");
					
					hourCIn.removeAllItems();
					for (int hour = 0; hour <= 23; hour++) {
						String hourString = String.format("%02d", hour);
						hourCIn.addItem(hourString);
				}
			}
		}
	}
}