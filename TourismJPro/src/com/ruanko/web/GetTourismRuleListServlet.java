package com.ruanko.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruanko.model.Tourism;
import com.ruanko.model.TourismRule;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class GetTourismRuleListServlet
 */
public class GetTourismRuleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService=new TourismService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTourismRuleListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 旅游规则显示界面
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 旅游规则显示界面
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取页面传递的旅游基本信息ID
		int tourismId = Integer.parseInt(request.getParameter("tourism_id"));
		try {
			//准备旅游规则数据
			List<TourismRule> tourismRules = tourismService.getListByTourism(tourismId);
			//将数据存入session
			request.getSession().setAttribute("tourismRules", tourismRules);
			//返回旅游规则先关联的旅游基本信息记录
			Tourism tourism = tourismService.getTourismById(tourismId);
			request.getSession().setAttribute("tourism", tourism);
			//跳转到旅游信息显示界面
			request.getRequestDispatcher("tourismRuleList.jsp").forward(request,
					response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
