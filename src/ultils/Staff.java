package ultils;

import java.sql.Date;

public class Staff extends Person {
	private String idNumber;
	private String role;
	public Staff( String name, String idNumber, String numberPhone,Date birth, String role) {
		super(name, numberPhone, birth);
		this.idNumber = idNumber;
		this.role = role;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}

