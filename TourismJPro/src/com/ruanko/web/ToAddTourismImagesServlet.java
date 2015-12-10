package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.UserBo;

/**
 * Servlet implementation class ToAddTourismImagesServlet
 */
public class ToAddTourismImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToAddTourismImagesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 进入添加旅游图片页面
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 进入添加旅游图片页面
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBo userBo = (UserBo) session.getAttribute("userBo");
		if (session.getAttribute("userBo") != null) {
			if (userBo.getType() == 1) {
				request.getRequestDispatcher("addTourismImages.jsp").forward(
						request, response);
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
