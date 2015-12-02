package com.ruanku.model;

import java.util.Date;

/**
 * 评价表
 * 
 * @author rkcoe
 * 
 */
public class Comment {
	private int ID; // 评价唯一标识
	private int tourism_id; // 旅游ID相关联
	private int user_id; // 用户ID相关联
	private String userName; // 用户评价的姓名
	private int satisfaction; // 满意度 3满意(3分)；2一般(2分)；0不满意(0分) 总体满意度=总体/(评价总数*3)
	private String content; // 用户评论内容
	private Date createDate; // 创建时间 默认为当前时间
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
