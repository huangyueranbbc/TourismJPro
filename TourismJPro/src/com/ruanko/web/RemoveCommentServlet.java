package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.service.CommentService;
import com.ruanko.utils.AppException;

/**
 * 删除用户评论
 * 
 * @author rkcoe
 * 
 */
public class RemoveCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 删除用户评论
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 删除用户评论
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// 获取用户评论ID
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		boolean flag;
		try {
			flag = commentService.removeCommentById(commentId);
			if (flag) {
				request.getRequestDispatcher("ManagerUserCommentServlet").forward(request,
						response);   
			} else {  
				throw new AppException(
						"评论删除失败:com.ruanko.web.RemoveCommentServlet.doPost(HttpServletRequest, HttpServletResponse)");
			} 
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
