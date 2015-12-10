package com.ruanko.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanko.model.Tourism;
import com.ruanko.model.TourismInfo;
import com.ruanko.model.TourismRule;
import com.ruanko.model.UserBo;
import com.ruanko.service.TourismService;
import com.ruanko.utils.AppException;

/**
 * Servlet implementation class AddToursimServlet
 */
public class AddToursimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TourismService tourismService = new TourismService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToursimServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加旅游信息
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 添加旅游信息
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		TourismInfo tourismInfo = new TourismInfo();

		// 准备Tourism数据
		Tourism tourism = new Tourism();
		String title = request.getParameter("title");
		int type = Integer.parseInt(request.getParameter("type"));
		String city = request.getParameter("city");
		int days = Integer.parseInt(request.getParameter("days"));
		int basePrice = Integer.parseInt(request.getParameter("basePrice"));
		int minNumber = Integer.parseInt(request.getParameter("minNumber"));
		int maxNumber = Integer.parseInt(request.getParameter("maxNumber"));
		String description = request.getParameter("description");
		tourism.setBasePrice(basePrice);
		tourism.setCity(city);
		tourism.setDays(days);
		tourism.setDescription(description);
		tourism.setMaxNumber(maxNumber);
		tourism.setMinNumber(minNumber);
		tourism.setTitle(title);
		tourism.setType(type);
		// 准备TourismRule数据
		List<TourismRule> tourismRuleList = new ArrayList<TourismRule>();

		String[] tourismTimes = request.getParameterValues("tourismTime");
		String[] prices = request.getParameterValues("price");
		String[] discounts = request.getParameterValues("discount");
		String[] remarks = request.getParameterValues("remark");
		int deadline = Integer.parseInt(request.getParameter("deadline"));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date deadline = sdf.parse(request.getParameter("deadline"));

		// 管理员更新时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp modifyTime = Timestamp.valueOf(time);

		// 将旅游规则装入List容器
		for (int i = 0; i < tourismTimes.length; i++) {
			// 过滤空字符串
			if (prices[i] != "") {
				TourismRule tourismRule = new TourismRule();
				tourismRule.setDiscount(discounts[i]);
				tourismRule.setModifyTime(modifyTime);
				tourismRule.setPrice(Float.parseFloat((prices[i]) + ""));
				tourismRule.setRemark(remarks[i]);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date nowdate;
				try {
					nowdate = df.parse(tourismTimes[i]);
					df.format(nowdate);
					Date deadlineDate = new Date(nowdate.getTime()
							- (long) deadline * 24 * 60 * 60 * 1000);
					tourismRule.setDeadline(deadlineDate);
					tourismRule.setTourismTime(nowdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				tourismRuleList.add(tourismRule);
			}
		}

		// 准备TourismInfo数据
		tourismInfo.setTourism(tourism);
		tourismInfo.setTourismRules(tourismRuleList);

		// 开始添加数据
		try {
			boolean result = tourismService
					.addToursimInfo(tourismInfo, request);
			if (result) {
				HttpSession session = request.getSession();
				UserBo userBo = (UserBo) session.getAttribute("userBo");
				if (session.getAttribute("userBo") != null) {
					if (userBo.getType() == 1) {
						request.getRequestDispatcher(
								"ToAddTourismImagesServlet").forward(request,
								response);
					} else {
						session.removeAttribute("userBo");
						request.getRequestDispatcher("ToLoginServlet").forward(
								request, response);
					}
				} else {
					session.removeAttribute("userBo");
					request.getRequestDispatcher("ToLoginServlet").forward(
							request, response);
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
		}

	}

}
