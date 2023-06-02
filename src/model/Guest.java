package model;

import java.sql.Date;

public class Guest {
	private String id;
	private String name;
	private String type;
	private Date birth;
	private String numberPhone;
	public Guest(String id, String name, String type, Date birth, String numberPhone) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.birth = birth;
		this.numberPhone = numberPhone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
}
