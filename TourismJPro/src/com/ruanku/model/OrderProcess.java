package com.ruanku.model;

import java.sql.Timestamp;

/**
 * 订单流程表
 * 
 * @author rkcoe
 * 
 */
public class OrderProcess {
	private int ID; // 订单流程唯一标识
	private int order_id; // 订单ID相关联
	private int user_id; // 用户ID相关联
	private int type; // 操作类型 1下单，2支付，3受理接受，4受理拒绝，5取消订单
	private Timestamp dateTime; // 下订单的时间
	private int del; // 是否删除 0未删除，1已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
