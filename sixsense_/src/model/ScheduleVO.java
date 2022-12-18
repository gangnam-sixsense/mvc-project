package model;

public class ScheduleVO {
	private String weather; //날씨.
	private int year; //년.
	private int month; //달.
	private int day; //일.
	private String highTemperature; //최고기온.
	private String lowTemperature; //최저기온.
	private String pmRainfall; //최대 강우량.
	private String amRainfall; //최소 강우량.
	private String memo;
	private String ID; //FK
	private int pNum; //PK
	private String gu;
	private String dong;

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
	public void setID(String ID) {
		this.ID = ID;
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

	public String getHighTemperature() {
		return highTemperature;
	}
	public void setHighTemperature(String highTemperature) {
		this.highTemperature = highTemperature;
	}
	public String getLowTemperature() {
		return lowTemperature;
	}
	public void setLowTemperature(String lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	
	public String getPmRainfall() {
		return pmRainfall;
	}
	public void setPmRainfall(String pmRainfall) {
		this.pmRainfall = pmRainfall;
	}
	public String getAmRainfall() {
		return amRainfall;
	}
	public void setAmRainfall(String amRainfall) {
		this.amRainfall = amRainfall;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}



}