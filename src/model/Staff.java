package model;

import java.sql.Date;

public class Staff extends Person {
	private String idNumber;
	private String role;
	public Staff( String name, String idNumber, String numberPhone,Date birth, String role) {
		super(name, numberPhone, birth);
		this.idNumber = idNumber;
		this.role = role;
	}	
}

