package com.ruanko.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.PageModel;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismInfo;
import com.ruanko.model.UserBo;
import com.ruanko.service.CommentService;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * 获取用户能够评论的旅游信息列表 Servlet implementation class GetUserCommentableList
 */
public class GetUserCommentableList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService = new TourismService();

	/**
	 * 获取用户能够评论的旅游信息列表
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserCommentableList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取用户能够评论的旅游信息列表
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
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
			UserBo user = (UserBo) request.getSession().getAttribute("userBo");
			try {
				pageModel = tourismService.getUserCommentableList(
						user.getUserId(), page, 10);
				session.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("userCommentTourismInfo.jsp").forward(
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

	private int returnPage(HttpServletRequest request) {
		String parameter = request.getParameter("page");
		if (parameter == null) {
			parameter = 1 + "";
		}
		int page = Integer.parseInt(parameter);
		return page;
	}

}
