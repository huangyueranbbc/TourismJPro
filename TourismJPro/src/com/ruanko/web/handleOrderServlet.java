package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.Bill;
import com.ruanko.model.Order;
import com.ruanko.service.BCBankService;
import com.ruanko.service.BillService;
import com.ruanko.service.OrderService;
import com.ruanko.utils.AppException;

/**
 * 处理订单
 * 
 * @author rkcoe
 * 
 */
public class handleOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	private BCBankService bankService = new BCBankService();
	private BillService billService = new BillService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public handleOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 受理订单
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 受理订单
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 准备要修改的订单状态和订单信息
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		String orderNo = request.getParameter("orderNo");
		try {
			Order order = orderService.getOrderById(orderNo);
			boolean flag = orderService.updateOrderStatus(order,
					orderStatus);
			// TODO 根据状态 如果是受理拒绝 则进行退款
			switch (orderStatus) {
			case 3:
				//受理接受 
				break;
			case 4:
				//受理拒绝
				// 根据订单和要修改的订单状态修改订单状态 保证事务 如果更改成功 则进行退款
				if (flag) {
					// 退款
					String name = "ruanko"; // 旅游网企业银行账户
					String password = "ruanko"; // 旅游网企业银行账户密码
					String appId = "1001";// 合作伙伴ID
					billService.updateBillByOrderId(1, 4, order.getID());
					Bill bill = billService.getBillByOrderId(order.getID());
					bankService.payByNameAndPassword(order.getPrice(), name,
							password, appId, bill.getUser_name());
				}
				break; 
			case 5:
				//取消订单
				if (flag) { 
					// 退款
					String name = "ruanko"; // 旅游网企业银行账户
					String password = "ruanko"; // 旅游网企业银行账户密码
					String appId = "1001";// 合作伙伴ID
					billService.updateBillByOrderId(1, 4, order.getID());
					Bill bill = billService.getBillByOrderId(order.getID());
					bankService.payByNameAndPassword(order.getPrice(), name,
							password, appId, bill.getUser_name());
				}
				break;
			default:
				break;
			}

			request.getRequestDispatcher("GetUserOrderListServlet").forward(
					request, response);
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

}
