package com.ruanko.dao;

import java.util.Date;
import java.util.List;

import com.ruanko.model.Tourism;
import com.ruanko.model.TourismImage;
import com.ruanko.utils.AppException;

/**
 * 旅游基本信息DAO
 * 
 * @author rkcoe
 * 
 */
public interface TourismDao {
	/**
	 * 添加旅游基本信息
	 * 
	 * @param tourism
	 * @return
	 * @throws AppException
	 */
	int add(Tourism tourism) throws AppException;

	/**
	 * 添加旅游图片
	 * 
	 * @param tourismImage
	 * @return
	 * @throws AppException
	 */
	boolean add(TourismImage tourismImage) throws AppException;

	/**
	 * 总记录数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getCount() throws AppException;

	/**
	 * 根据每页开始记录和每页的记录的长度 查询出每页的记录
	 * 
	 * @param page当前页
	 * @param size每页的记录长度
	 * @return
	 * @throws AppException
	 */
	List<Tourism> getList(int page, final int size) throws AppException;

	/**
	 * 根据ID返回指定一条旅游基本信息
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	Tourism getTourism(int tourismId) throws AppException;

	/**
	 * 根据旅游类型返回分页数据
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param type旅游类型
	 * @return
	 * @throws AppException
	 */
	List<Tourism> getListByType(int page, int size, int type)
			throws AppException;

	/**
	 * 根据价钱对旅游信息进行排序
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param order排序方式
	 *            0升序 1降序
	 * @return
	 * @throws AppException
	 */
	List<Tourism> getListOrderByPrice(int page, int size, int order)
			throws AppException;

	/**
	 * 根据价格范围返回旅游信息数目
	 * 
	 * @param startprice最低价格
	 * @param endprice最高价格
	 * @return旅游信息数目
	 * @throws AppException
	 */
	int getCountByScope(int startprice, int endprice) throws AppException;

	/**
	 * 根据价格范围返回旅游分页信息
	 * 
	 * @param page
	 * @param size
	 * @param startprice
	 * @param endprice
	 * @return
	 * @throws AppException
	 */
	List<Tourism> getListByPriceScope(int page, int size, int startprice,
			int endprice) throws AppException;

	/**
	 * 根据旅游类型返回旅游信息数目
	 * 
	 * @param type
	 * @return
	 * @return
	 * @throws AppException
	 */
	int getCountByType(int type) throws AppException;

	/**
	 * 判断用户是否具有出游资格
	 * 
	 * @param tourismid
	 * @param tourismTime
	 * @return
	 * @throws AppException
	 */
	boolean isTourism(int tourismid, Date tourismTime) throws AppException;

	/**
	 * 查询所有旅游信息记录
	 * 
	 * @return
	 * @throws AppException
	 */
	List<Tourism> getList() throws AppException;

	/**
	 * 根据ID排序返回指定长度的旅游信息记录
	 * 
	 * @param size
	 * @param type旅游类型
	 * @return
	 * @throws AppException
	 */
	List<Tourism> findTourismListOrderById(int size, int type)
			throws AppException;


}
