package model;

public class ScheduleVO {
	private String weather; //날씨.
	private int year; //년.
	private int month; //달.
	private int day; //일.
	private int highTemperature; //최고기온.
	private int LowTemperature; //최저기온.
	private int maxRainfall; //최대 강우량.
	private int minRainfall; //최소 강우량.
	private String memo;
	private String ID; //FK
	private int pNum; //PK
	
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHighTemperature() {
		return highTemperature;
	}
	public void setHighTemperature(int highTemperature) {
		this.highTemperature = highTemperature;
	}
	public int getLowTemperature() {
		return LowTemperature;
	}
	public void setLowTemperature(int lowTemperature) {
		LowTemperature = lowTemperature;
	}
	public int getMaxRainfall() {
		return maxRainfall;
	}
	public void setMaxRainfall(int maxRainfall) {
		this.maxRainfall = maxRainfall;
	}
	public int getMinRainfall() {
		return minRainfall;
	}
	public void setMinRainfall(int minRainfall) {
		this.minRainfall = minRainfall;
	}
	
	
	
}