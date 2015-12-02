package com.ruanku.model;

/**
 * 账单表
 * 
 * @author rkcoe
 * 
 */
public class Bill {
	private int ID; // 账单表唯一标识
	private String NO; // 账单表好 唯一标识不能重复
	private int order_id; // 订单ID相关联
	private int user_id; // 用户ID相关联
	private String user_name; // 用户名 与用户账号相关联
	private String recv_account; // 收款帐号 与用户银行卡密码相关联
	private String bank; // 用户选择付款的银行
	private float pay_account; // 付款金额 与商品总价相关
	private int currency; // 付款币种
	private float money; // 交易的金额
	private String paymethod; // 支付方式 用户选择的支付方式
	private int type; // 类型 0收款，1付款
	private int status; // 账单状态 0成功，1失败，2取消，3处理中，4无效，5超时
	private String remarks; // 备注
	private int del; // 是否删除 0未删除，1已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNO() {
		return NO;
	}

	public void setNO(String nO) {
		NO = nO;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRecv_account() {
		return recv_account;
	}

	public void setRecv_account(String recv_account) {
		this.recv_account = recv_account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public float getPay_account() {
		return pay_account;
	}

	public void setPay_account(float pay_account) {
		this.pay_account = pay_account;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

}
