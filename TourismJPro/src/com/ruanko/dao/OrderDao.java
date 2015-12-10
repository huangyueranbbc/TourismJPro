package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.Order;
import com.ruanko.model.OrderProcess;
import com.ruanko.model.TourismBo;
import com.ruanko.utils.AppException;

/**
 * 订单DAO层接口
 * 
 * @author rkcoe
 * 
 */
public interface OrderDao {

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 * @throws AppException
	 */
	int add(Order order) throws AppException;

	/**
	 * 根据用户ID查询用户订单的个数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getCountByUserId(int userId) throws AppException;

	/**
	 * 根据用户ID查询并返回用户自身订单信息
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Order> getListByUserId(int userId, int page, int size)
			throws AppException;

	/**
	 * 根据订单号返回订单详细数据
	 * 
	 * @param orderId
	 * @return
	 * @throws AppException
	 */
	Order getOrderByOrderId(String orderId) throws AppException;

	/**
	 * 根据订单号更新订单状态
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 * @throws AppException
	 */
	boolean updateStatusById(String orderId, int type) throws AppException;

	/**
	 * 返回所有订单总数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getCount() throws AppException;

	/**
	 * 返回所有用户的订单信息
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Order> getList(int page, int size) throws AppException;

	/**
	 * 判断该用户是否下订单并受理通过
	 * 
	 * @param userId
	 * @param tourismid
	 * @throws AppException
	 */
	List<TourismBo> isTourism(int userId, int tourismid) throws AppException;

	/**
	 * 返回需要受理订单总数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getAcceptCount() throws AppException;

	/**
	 * 返回需要受理订单的数据
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<com.ruanko.model.Order> getAcceptList(int page, int size)
			throws AppException;

	/**
	 * 根据旅游信息分组 按照订单人数排序 返回指定长度的旅游信息数据
	 * 
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Integer> findTourismIdGroupByTourismidOrderbyNum(int size) throws AppException;
	
	/**
	 * 根据销量进行排序
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Integer> getListBySales(int page, int size) throws AppException;
}
