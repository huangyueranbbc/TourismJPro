package com.ruanko.model;

import java.util.List;

/**
 * 存放订单详情信息
 * @author rkcoe
 *
 */
public class OrderInfo {
	private Order order;//订单信息
	private List<TourismImage> tourismImages;//旅游图片信息
	private String toutismTitle; //旅游标题

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<TourismImage> getTourismImages() {
		return tourismImages;
	}

	public void setTourismImages(List<TourismImage> tourismImages) {
		this.tourismImages = tourismImages;
	}

	public String getToutismTitle() {
		return toutismTitle;
	}

	public void setToutismTitle(String toutismTitle) {
		this.toutismTitle = toutismTitle;
	}
	
	

}
