package com.ruanko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.PageModel;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class SearchTourismServlet
 */
public class SearchTourismServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService = new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchTourismServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 搜索功能
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 搜索功能
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PageModel pageModel;// 返回的分页数据
		int page = returnPage(request);// 要查询的页数
		// 获取并准备筛选字段 根据筛选字段判断进行那种类型的搜索
		// searchType 0全文(内容)搜索 搜索类型 1价格排序 2价格范围排序 3旅游类型搜索 4销量搜索 5好评搜索 6最新搜索
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		request.getSession().setAttribute("searchType", searchType);
		System.out.println("搜索类型:" + Integer.parseInt(request.getSession().getAttribute("searchType").toString()));
		switch (searchType) {
		case 0:
			// 0全文搜索
			try {
				pageModel = tourismService.getList(page, 20);
				request.getSession().setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("searchResult.jsp").forward(request, response);
				// response.sendRedirect("searchResult.jsp");
			} catch (AppException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 1:
			// 1价格排序
			try {
				pageModel = tourismService.getListOrderByPrice(page, 20, 0);
				request.getSession().setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("searchResult.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			// 价格范围搜索
			try {
				// 准备价格范围数据
				int startprice = Integer.parseInt(request.getParameter("startprice").toString());
				int endprice = Integer.parseInt(request.getParameter("endprice").toString());
				request.getSession().setAttribute("startprice", startprice);
				request.getSession().setAttribute("endprice", endprice);
				// 调用service层进行查询
				pageModel = tourismService.getTourismByScope(page, 20, startprice, endprice);
				request.getSession().setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("searchResult.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			// 3旅游类型搜索
			try {
				int tourismtype = Integer.parseInt(request.getParameter("tourismtype"));
				System.out.println(tourismtype + ":");
				pageModel = tourismService.getListByType(page, 20, tourismtype);
				request.getSession().setAttribute("tourismtype", tourismtype);
				request.getSession().setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("searchResult.jsp").forward(request, response);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			//4销量排行
			try {
				pageModel = tourismService.getListBySales(page, 20);
				request.getSession().setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("searchResult.jsp").forward(request, response);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
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
