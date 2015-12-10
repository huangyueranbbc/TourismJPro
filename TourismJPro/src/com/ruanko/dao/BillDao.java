package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.Bill;
import com.ruanko.utils.AppException;

/**
 * 账单的Dao接口
 * 
 * @author rkcoe
 * 
 */
public interface BillDao {

	/**
	 * 添加账单信息
	 * 
	 * @param bill
	 * @return
	 * @throws AppException
	 */
	boolean add(Bill bill) throws AppException;

	/**
	 * 根据订单ID修改账单状态
	 * 
	 * @param del
	 * @param orderid
	 * @param status
	 * @return
	 * @throws AppException
	 */
	boolean updateBillDelByOrderId(int del, int status, int orderid)
			throws AppException;

	/**
	 * 根据订单ID查询账单
	 * 
	 * @param orderid
	 * @return
	 * @throws AppException
	 */
	Bill findBillByOrderId(int orderid) throws AppException;

	/**
	 * 根据用户ID返回账单数量
	 * 
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	int getCountByUserId(int userId) throws AppException;

	/**
	 * 根据用户ID返回用户账户信息
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Bill> getListByUserId(int userId, int page, int size)
			throws AppException;

}
