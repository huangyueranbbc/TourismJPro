package com.ruanko.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.Order;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismInfo;
import com.ruanko.model.TourismRule;
import com.ruanko.model.UserBo;
import com.ruanko.service.OrderService;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * 创建订单 Servlet implementation class PlaceOrderServlet
 */
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService(); // 创建订单Service层
	private TourismService tourismService=new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建订单
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 创建订单
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 订单号 由 年月日+用户ID+订单创建毫秒时间 构成
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 准备订单数据
		try {
			UserBo user = (UserBo) request.getSession().getAttribute("userBo");
			TourismInfo tourismInfo = (TourismInfo) request.getSession().getAttribute("tourismInfo");
			int num = Integer.parseInt(request.getParameter("tourismnum")); // 出游人数
 			int tourismtimeRuleId=Integer.parseInt(request.getParameter("tourismtimeRuleId"));
			TourismRule tourismRule=tourismService.getTourismRuleById(tourismtimeRuleId);
			Date tourismTime = tourismRule.getTourismTime();// 出游时间
			Timestamp createTime = new Timestamp(System.currentTimeMillis()); // 下订单的时间
			// 创建订单  
			Order order = orderService.Order(tourismRule.getPrice(),user.getUserId(), tourismInfo, num, tourismTime, createTime);
			Tourism tourism = tourismService.getTourismById(order.getTourism_id());
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("order_tourism", tourism);
			request.getRequestDispatcher("payment.jsp").forward(request, response); 
		} catch (AppException e) { 
			e.printStackTrace();
		}
	}

}
