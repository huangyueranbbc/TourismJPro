package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.PageModel;
import com.ruanko.model.UserBo;
import com.ruanko.service.CommentService;
import com.ruanko.utils.AppException;

/**
 * 显示所有用户评论信息 进行管理 Servlet implementation class ManagerUserCommentServlet
 */
public class ManagerUserCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerUserCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 显示所有用户评论信息 进行管理
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 显示所有用户评论信息 进行管理
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PageModel pageModel;
		int page = returnPage(request);// 要查询的页数

		if (session.getAttribute("userBo") != null) {
			UserBo userBo = (UserBo) session.getAttribute("userBo");
			if (userBo.getType() == 1) {
				// 准备用户评论数据
				try {
					pageModel = commentService.getList(page, 10);
					session.setAttribute("pageModel",pageModel);
					request.getRequestDispatcher("userCommentManagerList.jsp").forward(request,
							response); 
				} catch (AppException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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

	private int returnPage(HttpServletRequest request) {
		String parameter = request.getParameter("page");
		if (parameter == null) {
			parameter = 1 + "";
		}
		int page = Integer.parseInt(parameter);
		return page;
	}

}
