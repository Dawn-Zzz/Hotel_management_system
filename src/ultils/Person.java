package ultils;

import java.sql.Date;

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
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public Date getBirth() {
		return birth;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
