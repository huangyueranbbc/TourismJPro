package com.ruanko.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruanko.dao.OrderDao;
import com.ruanko.dao.OrderProcessDao;
import com.ruanko.dao.TourismDao;
import com.ruanko.dao.TourismImagesDao;
import com.ruanko.dao.impl.OrderDaoImpl;
import com.ruanko.dao.impl.OrderProcessDaoImpl;
import com.ruanko.dao.impl.TourismDaoImpl;
import com.ruanko.dao.impl.TourismImagesDaoImpl;
import com.ruanko.model.Order;
import com.ruanko.model.OrderInfo;
import com.ruanko.model.OrderProcess;
import com.ruanko.model.PageModel;
import com.ruanko.model.TourismImage;
import com.ruanko.model.TourismInfo;
import com.ruanko.utils.AppException;

public class OrderService {
	// 准备Dao
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderProcessDao orderProcessDao = new OrderProcessDaoImpl();
	private TourismImagesDao tourismImagesDao = new TourismImagesDaoImpl();
	private TourismDao tourismDao = new TourismDaoImpl();

	/**
	 * 根据准备的数据 创建订单
	 * 
	 * @param userId用户ID
	 * @param tourismInfo旅游信息
	 * @param num出游人数
	 * @param tourismTime出游日期
	 * @param createTime订单创建时间
	 *            订单号 由 创建年月日+用户ID+订单创建毫秒时间 构成
	 * @return 返回订单信息
	 * @throws AppException
	 */
	public Order Order(float price,int userId, TourismInfo tourismInfo, int num,
			Date tourismTime, Timestamp createTime) throws AppException {
		Order order = new Order();
		OrderProcess orderProcess = new OrderProcess();
		// 准备数据
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String orderId = formatter.format(createTime).toString() + userId
				+ System.currentTimeMillis();// 创建订单号
		float amount = num * price;// 计算总金额
		String remark;
		order.setOrderId(orderId);
		order.setUser_id(userId);
		order.setTourism_id(tourismInfo.getTourism().getID());
		order.setPrice(price);
		order.setTourismTime(tourismTime);
		order.setNumber(num); 
		order.setStatus(1);
		order.setAmount(amount);
		order.setRemark(tourismInfo.getTourism().getTitle());
		order.setCreateTime(createTime);
		// 添加订单
		int order_id = orderDao.add(order);
		orderProcess.setOrder_id(order_id);
		orderProcess.setUser_id(order.getUser_id());
		orderProcess.setType(order.getStatus());
		orderProcess.setDateTime(order.getCreateTime());
		orderProcess.setDel(0);
		// 添加订单流程
		orderProcessDao.add(orderProcess);
		return order;
	}

	/**
	 * 根据用户ID获得用户自身订单信息
	 * 
	 * @param userId
	 * @throws AppException
	 */
	public PageModel getOrderInfoListByUserId(int userId, final int page,
			final int size) throws AppException {
		// 根据记录总数求出 分页总数
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		int count = orderDao.getCountByUserId(userId);
		PageModel pageModel = new PageModel(count, size, page);
		List<Order> orders = orderDao.getListByUserId(userId,
				pageModel.getPage(), pageModel.getSize());
		for (Order order : orders) {
			OrderInfo orderInfo = new OrderInfo();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(order.getTourism_id());
			orderInfo.setToutismTitle(tourismDao.getTourism(
					order.getTourism_id()).getTitle());
			orderInfo.setOrder(order);
			orderInfo.setTourismImages(tourismImages);
			orderInfos.add(orderInfo);
		}
		// 将查询后的订单list信息放入分页数据中返回
		pageModel.setList(orderInfos);
		return pageModel;
	}

	/**
	 * 根据订单号返回订单信息
	 * 
	 * @param orderId
	 * @return 订单信息
	 * @throws AppException
	 */
	public Order getOrderById(String orderId) throws AppException {
		Order order = orderDao.getOrderByOrderId(orderId);
		return order;
	}

	/**
	 * 更新订单信息
	 * 
	 * @param order
	 * @return
	 * @throws AppException
	 */
	public boolean updateOrderStatus(Order order, int type) throws AppException {
		boolean flag;
		// 更新订单状态
		flag = orderDao.updateStatusById(order.getOrderId(), type);
		// 更新订单流程状态
		flag = orderProcessDao.updateTypeByOrderId(order.getID(), type);
		System.out.println(order.getID()); 
		return flag;
	}

	/**
	 * 返回所有用户需要受理的订单信息
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getOrderInfoList(final int page, final int size)
			throws AppException {
		// 根据记录总数求出 分页总数
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		int count = orderDao.getAcceptCount();
		PageModel pageModel = new PageModel(count, size, page);
		List<Order> orders = orderDao.getAcceptList(pageModel.getPage(),
				pageModel.getSize());
		for (Order order : orders) {
			OrderInfo orderInfo = new OrderInfo();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(order.getTourism_id());
			orderInfo.setToutismTitle(tourismDao.getTourism(
					order.getTourism_id()).getTitle());
			orderInfo.setOrder(order);
			orderInfo.setTourismImages(tourismImages);
			orderInfos.add(orderInfo); 
		}
		// 将查询后的订单list信息放入分页数据中返回
		pageModel.setList(orderInfos);
		return pageModel;
	}

}
