package model;

import java.time.LocalDate;

public class Customer extends Person {
	private String idNumber;
	private String type;
	public Customer(String name, String numberPhone, LocalDate birth, String idNumber, String type) {
		super(name, numberPhone, birth);
		this.idNumber = idNumber;
		this.type = type;
	}	
}
