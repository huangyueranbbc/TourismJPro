package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.OrderProcess;
import com.ruanko.utils.AppException;

/**
 * 订单DAO
 * 
 * @author rkcoe
 * 
 */
public interface OrderProcessDao {

	/**
	 * 添加订单流程信息
	 * 
	 * @param orderProcess
	 * @return
	 * @throws AppException
	 */
	boolean add(OrderProcess orderProcess) throws AppException;

	/**
	 * 根据订单ID更新订单流程状态
	 * 
	 * @param id
	 * @param type
	 * @return
	 * @throws AppException
	 */
	boolean updateTypeByOrderId(int id,int type) throws AppException;

}
