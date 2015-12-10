package com.ruanko.web;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.ruanko.model.Comment;
import com.ruanko.model.TourismInfo;
import com.ruanko.model.UserBo;
import com.ruanko.service.CommentService;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class TourismDetailServlet
 */
public class TourismDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService = new TourismService();
	private CommentService commentService = new CommentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TourismDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 显示旅游信息详述
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * TODO 目前只有图片、规则、 基本信息 还需增加评价信息 显示旅游信息详述
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 判断是否登录 如果未登录 返回登录页面
		// 获取要显示详细信息的旅游ID
		int tourismid = Integer.parseInt(request.getParameter("tourismid"));
		// 显示评论 准备评论相关数据
		try {
			List<Comment> comments = commentService
					.getListByTourismId(tourismid);
			request.getSession().setAttribute("comments", comments);
			// 准备满意度 总评价数 数据
			double commentCount = comments.size();// 总评价次数
			double allSatisfaction = commentService
					.getAllSatisfaction(tourismid);// 返回该旅游信息的满意度之和
			double satisfactionPercent; 
			if(commentCount != 0){
				satisfactionPercent = allSatisfaction / (3 * commentCount);// 满意度
			}else {
				satisfactionPercent=1.0;
			} 
			System.out.println("满意度"+satisfactionPercent);  
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			String satisfactionPercentString = nt.format(satisfactionPercent);
			request.getSession().setAttribute("satisfactionPercent", satisfactionPercentString);
			request.getSession().setAttribute("commentCount", comments.size()); 
		} catch (AppException e1) { 
			e1.printStackTrace();
		}

		try {
			if (request.getSession().getAttribute("userBo") != null) {
				// 获取用户ID
				UserBo user = (UserBo) request.getSession().getAttribute(
						"userBo");
				int userId = user.getUserId();
				// 判断是否能够评价旅游新主题
				boolean flag = tourismService.isRightComment(userId, tourismid);
				// 准备页面数据
				TourismInfo tourismInfo = tourismService
						.getToursimInfoById(tourismid);
				request.getSession().setAttribute("commentPower", flag);
				request.getSession().setAttribute("tourismInfo", tourismInfo);
				request.getRequestDispatcher("tourismDetail.jsp").forward(
						request, response);

			} else {
				TourismInfo tourismInfo = tourismService
						.getToursimInfoById(tourismid);
				request.getSession().setAttribute("tourismInfo", tourismInfo);
				request.getRequestDispatcher("tourismDetail.jsp").forward(
						request, response);
			}
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

}
