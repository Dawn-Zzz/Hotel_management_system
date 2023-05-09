package model;

import java.sql.Date;
import java.util.List;

public class Bill {
	private double total;
	private double totalRoom;
	private double totalService;
	private Date dateCreate;
	private String nameGuest;
	private String nameStaff;
	private List<OrderService> services;
	public Bill(String nameGuest, String nameStaff, double total, double toatalRoom, double totalService, Date dateCreate) {
		super();
		this.total = total;
		this.totalRoom = toatalRoom;
		this.totalService = totalService;
		this.dateCreate = dateCreate;
		this.nameGuest = nameGuest;
		this.nameStaff = nameStaff;
	}
	public double getTotal() {
		return total;
	}
	public double getTotalRoom() {
		return totalRoom;
	}
	public double getTotalService() {
		return totalService;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public String getNameGuest() {
		return nameGuest;
	}
	public String getNameStaff() {
		return nameStaff;
	}
}
