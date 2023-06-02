package model;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
	private String id;
	private Guest guest;
	private Staff staff;
	private Room room;
	private Timestamp checkIn;
	private Timestamp checkOut;
	private Date createDay;
	private int headCount;
	private String rentalType;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Timestamp getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Timestamp checkIn) {
		this.checkIn = checkIn;
	}
	public Timestamp getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Timestamp checkOut) {
		this.checkOut = checkOut;
	}
	public Date getCreateDay() {
		return createDay;
	}
	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public String getRentalType() {
		return rentalType;
	}
	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
