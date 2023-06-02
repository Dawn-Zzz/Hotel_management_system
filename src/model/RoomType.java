package model;

public class RoomType {
	private String id;
	private String name;
	private float hourlyRate;
	private float dailyRate;
	private float nightlyRate;
	public RoomType(String id, String name, float hourlyRate, float dailyRate, float nightlyRate) {
		super();
		this.id = id;
		this.name = name;
		this.hourlyRate = hourlyRate;
		this.dailyRate = dailyRate;
		this.nightlyRate = nightlyRate;
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
	public float getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public float getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(float dailyRate) {
		this.dailyRate = dailyRate;
	}
	public float getNightlyRate() {
		return nightlyRate;
	}
	public void setNightlyRate(float nightlyRate) {
		this.nightlyRate = nightlyRate;
	}
}
