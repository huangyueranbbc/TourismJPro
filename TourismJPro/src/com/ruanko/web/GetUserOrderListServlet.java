package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.PageModel;
import com.ruanko.model.UserBo;
import com.ruanko.service.OrderService;
import com.ruanko.utils.AppException;


/**
 * 获取用户自身的订单信息
 * @author rkcoe
 *
 */
public class GetUserOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserOrderListServlet() {
		super();
	}

	/**
	 * 获取用户自身的订单信息
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获取用户自身的订单信息
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		UserBo userBo = (UserBo) session.getAttribute("userBo");//准备用户ID
		PageModel pageModel;// 返回的分页数据
		int page = returnPage(request);// 要查询的页数
		
		//判断是否是登录状态 如果不是返回登录页面 
		if (userBo != null) {
			session.setAttribute("userBo", userBo);
			if (userBo.getType() == 1) {
				//管理员查看订单功能
				try {
					pageModel = orderService.getOrderInfoList(page,10);
					session.setAttribute("pageModel",pageModel); 
					request.getRequestDispatcher("manageorderList.jsp").forward(
							request, response);
				} catch (AppException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			} else if (userBo.getType() == 0) {
				//用户查看自身订单功能
				try {
					pageModel = orderService.getOrderInfoListByUserId(userBo.getUserId(),page,10);
					session.setAttribute("pageModel",pageModel); 
					request.getRequestDispatcher("orderList.jsp").forward(
							request, response);
				} catch (AppException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			request.getRequestDispatcher("ToLoginServlet").forward(request,
					response);
		}
		
	}

	private int returnPage(HttpServletRequest request) {
		String parameter = request.getParameter("page");
		if (parameter == null) {
			parameter = 1 + "";
		}
		int page = Integer.parseInt(parameter);
		return page;
	}
}
