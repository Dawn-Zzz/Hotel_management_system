package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Reservation {
	private Guest guest;
	private Staff staff;
	private Room room;
	private Timestamp checkIn;
	private Timestamp checkOut;
	private Date createDay;
	private int amoutPerson;
	private String rentalType;
	private Double deposit;
	private String status;

	public Reservation(Room room, Timestamp checkIn2, Timestamp checkOut2) {
		super();
		this.room = room;
		this.checkIn = checkIn2;
		this.checkOut = checkOut2;
	}
	
	public Room getRoom() {
		return room;
	}
	public Timestamp getCheckIn() {
		return checkIn;
	}
	public Timestamp getCheckOut() {
		return checkOut;
	}
}
