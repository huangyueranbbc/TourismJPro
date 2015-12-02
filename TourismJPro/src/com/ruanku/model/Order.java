package com.ruanku.model;

import java.util.Date;

/**
 * 订单表
 * 
 * @author rkcoe
 * 
 */
public class Order {
	private int ID; // 订单唯一标识
	private String orderId; // 订单号
	private int user_id; // 用户ID相关联
	private int tourism_id; // 旅游信息ID相关联
	private int price; // 价格
	private Date tourismTime; // 出游时间
	private int number; // 旅游人数
	private int status; // 订单状态 1下单，2支付，3受理接受，4受理拒绝，5取消订单
	private float amount; // 总金额 总金额=出发人数*单人价格
	private String remark; // 备注
	private String createTime; // 下订单的时间
	private int del; // 是否删除 0未删除，1已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTourism_id() {
		return tourism_id;
	}

	public void setTourism_id(int tourism_id) {
		this.tourism_id = tourism_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getTourismTime() {
		return tourismTime;
	}

	public void setTourismTime(Date tourismTime) {
		this.tourismTime = tourismTime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
