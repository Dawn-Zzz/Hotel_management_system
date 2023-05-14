package model;

public class Room {
	private String numberRoom;
	private String currentStatus;
	public Room(String numberRoom, String currentStatus) {
		super();
		this.numberRoom = numberRoom;
		this.currentStatus = currentStatus;
	}
	public String getNumberRoom() {
		return numberRoom;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
}
