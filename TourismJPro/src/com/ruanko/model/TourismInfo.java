package com.ruanko.model;

import java.util.List;

/**
 * 旅游信息实体
 * 
 * @author rkcoe
 * 
 */
public class TourismInfo {
	private Tourism tourism; // 旅游基本信息
	private List<TourismRule> tourismRules; // 旅游规则
	private List<TourismImage> tourismImages; // 旅游图片
	private String satisfactionPercent;// 满意度

	public Tourism getTourism() {
		return tourism;
	}

	public void setTourism(Tourism tourism) {
		this.tourism = tourism;
	}

	public List<TourismRule> getTourismRules() {
		return tourismRules;
	}

	public void setTourismRules(List<TourismRule> tourismRules) {
		this.tourismRules = tourismRules;
	}

	public List<TourismImage> getTourismImages() {
		return tourismImages;
	}

	public void setTourismImages(List<TourismImage> tourismImages) {
		this.tourismImages = tourismImages;
	}

	public String getSatisfactionPercent() {
		return satisfactionPercent;
	}

	public void setSatisfactionPercent(String satisfactionPercent) {
		this.satisfactionPercent = satisfactionPercent;
	}
 
}
