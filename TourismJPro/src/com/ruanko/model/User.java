package com.ruanko.model;

/**
 * 用户表
 * 
 * @author rkcoe
 * 
 */
public class User {
	private int ID; // 用户唯一标识
	private String name; // 用户名 不能重复
	private String password; // 用户密码
	private String phone; // 用户手机号
	private String email; // 用户邮箱
	private int type = 0; // 用户类型 0旅客，1管理员
	private int status = 0; // 是否冻结 0未冻结，1已冻结
	private int del = 0; // 是否删除 0未删除，1已删除

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", type=" + type
				+ ", status=" + status + ", del=" + del + "]";
	}

}
