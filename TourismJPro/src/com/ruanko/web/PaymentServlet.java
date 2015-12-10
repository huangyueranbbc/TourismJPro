package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.Bill;
import com.ruanko.model.Order;
import com.ruanko.model.UserBo;
import com.ruanko.service.BCBankService;
import com.ruanko.service.BillService;
import com.ruanko.service.OrderService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	private BillService billService = new BillService();
	private BCBankService bcBankService = new BCBankService();
	private String appId = "1001"; // 商户和以后合作ID 银行伙伴号
	private String recv_account = "ruanko";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 发送在线支付所需要的字段数据
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 发送在线支付所需要的字段数据
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// 1.判断用户是否在登录状态
		if (session.getAttribute("userBo") != null) {
			// 如果登录 则继续下一步
			UserBo user = (UserBo) session.getAttribute("userBo");
			// 2.准备数据
			String orderId = request.getParameter("order_id");
			float pay_account = Float.parseFloat(request
					.getParameter("pay_account")); // 获取支付总金额
			String name = request.getParameter("name"); // 获取传递的银行卡号
			String password = request.getParameter("password"); // 获取传递的银行卡对应密码

			// 3.开始支付
			try {
				// 调用Service层获取相应的订单信息
				Order order = orderService.getOrderById(orderId);
				// String text = order.getOrderId(); // 拼凑加密串
				// 调用银行的WebService进行支付
				// 验证金额是否正确
				int payResult = 30;// 银行支付后返回的支付状态 20支付成功 30支付失败
				if (pay_account == order.getAmount()) {
					payResult = bcBankService.payByNameAndPassword(pay_account,
							name, password, appId, recv_account);
				}
				// 支付成功后，调用BillService 支付 并 创建账单
				boolean flag = false;
				if (20 == payResult) {
					// 支付成功 添加账单
					Bill bill = new Bill();
					bill.setNO(order.getOrderId());
					bill.setOrder_id(order.getID());
					bill.setUser_id(order.getUser_id());
					bill.setUser_name(name);// 用户银行卡用户名
					bill.setRecv_account(recv_account);
					bill.setBank("中国银行");
					bill.setPay_account(order.getAmount());
					bill.setCurrency(0);
					bill.setMoney(pay_account);
					bill.setPaymethod("在线支付");
					bill.setType(0);
					bill.setStatus(3);
					bill.setRemarks(order.getRemark() + ":"
							+ bill.getPaymethod() + "" + bill.getBank()
							+ " 支付金额:" + bill.getMoney());
					bill.setDel(0);

					// 添加账单 并修改订单和订单流程的状态
					flag = billService.add(bill);
					if (flag) {
						order.setStatus(2);  
						System.out.println("订单号:"+order.getOrderId());
						flag = orderService.updateOrderStatus(order, 2);
					} else { 
						flag = false;
					}
				} else if (30 == payResult) {
					// 支付失败
					flag = false;
				}
				session.setAttribute("payorder", order);
				session.setAttribute("payResult", flag);
				request.getRequestDispatcher("paymentResult.jsp").forward(
						request, response);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 如果用户session失效或者不在登录状态 则返回登录界面
			session.removeAttribute("userBo");
			request.getRequestDispatcher("ToLoginServlet").forward(request,
					response);
		}

	}

}
