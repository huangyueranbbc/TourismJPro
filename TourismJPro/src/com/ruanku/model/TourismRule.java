package com.ruanku.model;

import java.util.Date;

/**
 * 旅游规则表
 * 
 * @author rkcoe
 * 
 */
public class TourismRule {
	private int ID; // 旅游规则唯一标识
	private int tourism_id; // 与旅游表ID相关联
	private Date deadline; // 规定时间
	private Date tourismTime; // 出游时间
	private float price; // 一个旅客出游的价钱
	private String discount; // 优惠信息说明
	private int status; // 规则状态 0可用，1不可用
	private String remark; // 备注
	private Date modifyTime; // 管理员修改时间
	private int del; // 是否删除 0未删除，1已删除

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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getTourismTime() {
		return tourismTime;
	}

	public void setTourismTime(Date tourismTime) {
		this.tourismTime = tourismTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
