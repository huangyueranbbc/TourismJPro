package com.ruanku.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanku.dao.UserDao;
import com.ruanku.model.User;
import com.ruanku.service.UserService;
import com.ruanku.utils.AppException;
import com.ruanku.utils.DBUtil;

/**
 * Servlet implementation class ToRegisterServlet
 */
public class ToRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用户注册
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 用户注册
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 创建连接开始注册
		Connection connection = DBUtil.getConnection();
		// 创建user对象并初始化
		User user = new User();
		user.setName(request.getParameter("name")); 
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("mobile"));
		// 调用service层进行注册
		try {
			if (userService.register(user)) {
				// 注册成功
				request.getRequestDispatcher("login.jsp").forward(request, response); 
			} else {
				// 注册失败
				request.getRequestDispatcher("register.jsp").forward(request, response); 
			}
		} catch (AppException e) {
			e.printStackTrace();
		}

	}

}
