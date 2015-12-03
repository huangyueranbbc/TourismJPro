package com.ruanku.model;

/**
 * 旅游信息表
 * 
 * @author rkcoe
 * 
 */
public class Tourism {
	private int ID; // 唯一标识
	private String title; // 旅游标题
	private int type; // 0跟团游，1自助游
	private String city; // 出发城市
	private int days; // 出游天数
	private int basePrice; // 旅游基本价格 旅游的具体价格会根据具体信息而确定
	private int minNumber; // 一次旅游执行所允许的最低人数
	private int maxNumber; // 一次旅游执行所允许的最多人数
	private String description; // 旅游信息相信说明、景点、行程等信息描述
	private int del = 0; // 是否删除，0表示未删除、1表示已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Tourism [ID=" + ID + ", title=" + title + ", type=" + type
				+ ", city=" + city + ", days=" + days + ", basePrice="
				+ basePrice + ", minNumber=" + minNumber + ", maxNumber="
				+ maxNumber + ", description=" + description + ", del=" + del
				+ "]";
	}

}
