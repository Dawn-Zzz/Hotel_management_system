package model;

import java.sql.Date;
import java.time.LocalDate;

public class Person {
	private String name;
	private String numberPhone;
	private Date birth;
	public Person(String name, String numberPhone, Date birth) {
		super();
		this.name = name;
		this.numberPhone = numberPhone;
		this.birth = birth;
	}
}
