package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.UserBo;
import com.ruanko.service.UserService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class UpdateUserServlet
 */
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用户密码修改
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 用户密码修改
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// name password
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserBo user = (UserBo)request.getSession().getAttribute("userBo");
		//调用service层进行用户的修改
		try {
			UserBo userBo = userService.updateUserById(user.getUserId(),name,password);
			request.getSession().setAttribute("userBo", userBo);
			request.getRequestDispatcher("ToUserCenterServlet").forward(request, response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
