package model;

public class Room {
	private String numberRoom;
	private String currentStatus;
	private String typeRoom;
	public Room(String numberRoom, String currentStatus,  String typeRoom) {
		super();
		this.numberRoom = numberRoom;
		this.currentStatus = currentStatus;
		this.typeRoom = typeRoom;
	}
	
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
	public String getTypeRoom() {
		return typeRoom;
	}	
}