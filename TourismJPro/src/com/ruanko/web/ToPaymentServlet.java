package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.Order;
import com.ruanko.model.Tourism;
import com.ruanko.service.OrderService;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * 进入支付订单页面
 * 
 * @author rkcoe
 * 
 */
public class ToPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	private TourismService tourismService = new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToPaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 进入支付订单页面
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 进入支付订单页面
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		try {
			Order order = orderService.getOrderById(orderId);
			Tourism tourism = tourismService.getTourismById(order 
					.getTourism_id());

			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("order_tourism", tourism);
			request.getRequestDispatcher("payment.jsp").forward(request,
					response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
