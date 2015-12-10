package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.service.UserService;
import com.ruanko.utils.AppException;

/**
 * 管理用户 解冻用户 冻结用户
 * @author rkcoe
 *
 */
public class ManagerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 解冻用户 冻结用户
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 解冻用户 冻结用户
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//准备数据
		int userStatus =Integer.parseInt(request.getParameter("userStatus"));//用户要修改的状态值
		int userId=Integer.parseInt(request.getParameter("userId"));//用户ID 
		//开始改变用户状态
		try {
			userService.updateUserStatusById(userId,userStatus);
			request.getRequestDispatcher("GetUserListServlet").forward(request,
					response); 
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
