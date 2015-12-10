package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.UserBo;
import com.ruanko.service.CommentService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加评论
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 添加评论
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 准备数据
		int tourismId = Integer.parseInt(request.getParameter("tourism_id"));
		int satisfaction = Integer.parseInt(request
				.getParameter("satisfaction"));
		String content = request.getParameter("content");
		System.out.println(content);
		UserBo userBo = (UserBo) request.getSession().getAttribute("userBo");

		// 添加评价
		try {
			boolean flag = commentService.add(tourismId, userBo.getUserId(),
					userBo.getName(), content, satisfaction);
			if (flag) {
				request.getRequestDispatcher("TourismDetailServlet?tourismid="+tourismId).forward(
						request, response);  
			} else {
				throw new AppException("添加评论失败");
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
