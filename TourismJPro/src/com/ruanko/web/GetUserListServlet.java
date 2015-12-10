package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.PageModel;
import com.ruanko.model.UserBo;
import com.ruanko.service.UserService;
import com.ruanko.utils.AppException;

/**
 * 管理员获取用户信息列表
 * 
 * @author rkcoe
 * 
 */
public class GetUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 管理员获取用户信息列表
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 管理员获取用户信息列表
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 判断登录的用户是否拥有管理员权限
		HttpSession session = request.getSession();
		UserBo userBo = (UserBo) session.getAttribute("userBo");
		if (session.getAttribute("userBo") != null) {
			if (userBo.getType() == 1) {
				PageModel pageModel;
				int page = returnPage(request);// 要查询的页数
				// 准备用户列表数据
				try {
					pageModel = userService.getList(page, 20);
					request.getSession().setAttribute("pageModel", pageModel);
					request.getRequestDispatcher("userList.jsp").forward(request,
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
