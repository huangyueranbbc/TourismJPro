package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.UserBo;

/**
 * Servlet implementation class ToUserCenter
 */
public class ToUserCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToUserCenterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 进入用户中心
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 进入用户中心
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		if (session.getAttribute("userBo") != null) {
			UserBo userBo = (UserBo) session.getAttribute("userBo");
			if (userBo.getType() == 0) {
				request.getRequestDispatcher("userCenter.jsp").forward(request,
						response);
			} else {
				session.removeAttribute("userBo");
				request.getRequestDispatcher("ToLoginServlet").forward(request,
						response);
			}
		} else {
			session.removeAttribute("userBo");
			request.getRequestDispatcher("ToLoginServlet").forward(request,
					response);
		}
	}

}
