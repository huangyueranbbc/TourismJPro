package com.ruanku.model;

import java.util.Date;

/**
 * 旅游信息图片
 * 
 * @author rkcoe
 * 
 */
public class TourismImage {
	private int ID; // 旅游图片的唯一标识
	private int tourism_id; // 关联旅游信息的ID
	private String big; // 大图路径
	private String small; // 小图路径
	private Date upload_time; // 上传时间
	private Date last_modify; // 更新时间
	private int orders; // 排序
	private int del; // 是否删除，0表示未删除、1表示已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTourism_id() {
		return tourism_id;
	}

	public void setTourism_id(int tourism_id) {
		this.tourism_id = tourism_id;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
