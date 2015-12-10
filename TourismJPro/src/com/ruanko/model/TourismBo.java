package com.ruanko.model;

import java.util.Date;

/**
 * 
 * @author rkcoe
 *
 */
public class TourismBo {
	private int tourismid;// 旅游信息ID
	private Date tourismTime; // 出游时间

	public int getTourismid() {
		return tourismid;
	}

	public void setTourismid(int tourismid) {
		this.tourismid = tourismid;
	}

	public Date getTourismTime() {
		return tourismTime;
	}

	public void setTourismTime(Date tourismTime) {
		this.tourismTime = tourismTime;
	}

}
