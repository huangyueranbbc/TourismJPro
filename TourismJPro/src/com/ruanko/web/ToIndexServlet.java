package com.ruanko.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.Tourism;
import com.ruanko.model.TourismInfo;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class ToIndexServlet
 */
public class ToIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 准备TourismService
	private TourismService tourismService = new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 访问主页
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 访问主页 并准备数据
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		// 准备首页旅游信息数据
		try {
			// 准备跟团游信息
			List<TourismInfo> tourisms0 = tourismService.getTourismListOrderById(4,
					0);// 获取跟团游信息
			System.out.println("跟团游:" + tourisms0.size());
			// 准备自助游信息
			List<TourismInfo> tourisms1 = tourismService.getTourismListOrderById(4,
					1);// 获取自助游信息
			System.out.println("自助游:" + tourisms1.size());
			// 准备热门旅游信息 订单人数最多
			List<TourismInfo> tourismsHot = tourismService
					.getTourismListGroupByTourismidOrderbyNum(4);
			System.out.println("热门游:" + tourismsHot.size());
			session.setAttribute("tourisms0", tourisms0);
			session.setAttribute("tourisms1", tourisms1);
			session.setAttribute("tourismsHot", tourismsHot);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response); 
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
