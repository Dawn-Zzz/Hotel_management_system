package model;

import java.time.LocalDate;

public class Person {
	private String name;
	private String numberPhone;
	private LocalDate birth;
	public Person(String name, String numberPhone, LocalDate birth) {
		super();
		this.name = name;
		this.numberPhone = numberPhone;
		this.birth = birth;
	}
}
