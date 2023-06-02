package ultils;

import java.sql.Date;

public class Guest extends Person {
	private String idNumber;
	private String type;
	public Guest(String idNumber, String name, String numberPhone, Date birth, String type) {
		super(name, numberPhone, birth);
		this.idNumber = idNumber;
		this.type = type;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public String getType() {
		return type;
	}
}
