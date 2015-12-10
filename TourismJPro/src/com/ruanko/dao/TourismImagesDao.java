package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.TourismImage;
import com.ruanko.utils.AppException;

/**
 * 旅游图片信息DAO
 * 
 * @author rkcoe
 * 
 */
public interface TourismImagesDao {
	
	/**
	 * 根据ID返回指定的旅游图片信息
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	List<TourismImage> getTourismImages(int tourismId) throws AppException;
}
