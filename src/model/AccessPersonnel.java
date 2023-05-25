package model;

public class AccessPersonnel {
	private User user;
	private Staff staff;
	private static AccessPersonnel instance;
	
	public static AccessPersonnel getInstance() {
        if (instance == null) instance = new AccessPersonnel();
        return instance;
    }
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
