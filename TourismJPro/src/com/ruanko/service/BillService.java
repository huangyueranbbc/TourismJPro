package com.ruanko.service;

import java.util.List;

import com.ruanko.dao.BillDao;
import com.ruanko.dao.impl.BillDaoImpl;
import com.ruanko.model.Bill;
import com.ruanko.model.Order;
import com.ruanko.model.PageModel;
import com.ruanko.utils.AppException;

/**
 * 账单的Service层
 * 
 * @author rkcoe
 * 
 */
public class BillService {
	private BillDao billDao = new BillDaoImpl();

	/**
	 * 
	 * @param bill
	 * @throws AppException
	 */
	public boolean add(Bill bill) throws AppException {
		boolean flag;
		flag = billDao.add(bill);
		return flag;
	}

	/**
	 * 根据订单ID改变账单状态
	 * 
	 * @param del
	 * @param orderid
	 * @param status
	 * @return
	 * @throws AppException
	 */
	public boolean updateBillByOrderId(int del, int status, int orderid)
			throws AppException {
		boolean flag = billDao.updateBillDelByOrderId(del, status, orderid);
		return flag;
	}

	/**
	 * 根据订单ID返回账单信息
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	public Bill getBillByOrderId(int orderid) throws AppException {
		Bill bill = billDao.findBillByOrderId(orderid);
		return bill;
	}

	/**
	 * 根据用户ID返回用户账单
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getListByUserId(int userId, int page, int size)
			throws AppException {
		int count = billDao.getCountByUserId(userId);
		PageModel pageModel = new PageModel(count, size, page);
		List<Bill> bills = billDao.getListByUserId(userId,pageModel.getPage(),
				pageModel.getSize());
		pageModel.setList(bills);
		return pageModel;
	}

}
