package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.UserBo;

/**
 * Servlet implementation class ToShowToursimServlet
 */
public class ToGetTourismRuleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToGetTourismRuleListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 进入旅游规则界面
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 进入旅游规则界面
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		UserBo userBo = (UserBo) session.getAttribute("userBo");
		if (session.getAttribute("userBo") != null) {
			if (userBo.getType() == 1) {
				request.getRequestDispatcher("GetTourismRuleListServlet")
						.forward(request, response);
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
