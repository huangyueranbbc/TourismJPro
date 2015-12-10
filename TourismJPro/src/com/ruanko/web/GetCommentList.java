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
 * 查看用户自身发表的评论
 * 
 * @author rkcoe
 * 
 */
public class GetCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService=new CommentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCommentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查看用户自身发表的评论
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 查看用户自身发表的评论
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
			UserBo userBo = (UserBo) request.getSession()
					.getAttribute("userBo");
			int userId=userBo.getUserId();
			try {
				//根据用户ID准备用户评论数据
				pageModel=commentService.getListByUserId(userId,page, 10);
				session.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("userCommentList.jsp").forward(request,
						response);  
			} catch (AppException e) {
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
