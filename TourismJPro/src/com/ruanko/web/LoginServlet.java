package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.User;
import com.ruanko.model.UserBo;
import com.ruanko.service.UserService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class ToLoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用户登录
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 用户登录
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			UserBo userBo = userService.login(request.getParameter("name"),
					request.getParameter("password"));
			if (userBo != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userBo", userBo);
				if (userBo.getType() == 1) {
					request.getRequestDispatcher("ToAdminCenterServlet")
							.forward(request, response);
				} else if (userBo.getType() == 0) {
					request.getRequestDispatcher("ToUserCenterServlet")
							.forward(request, response);
				}

			} else {
				request.getRequestDispatcher("ToLoginServlet").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
		}

	}

}
