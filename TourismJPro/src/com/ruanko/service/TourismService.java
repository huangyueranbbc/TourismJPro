package com.ruanko.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ruanko.dao.CommentDao;
import com.ruanko.dao.OrderDao;
import com.ruanko.dao.TourismDao;
import com.ruanko.dao.TourismImagesDao;
import com.ruanko.dao.TourismRuleDao;
import com.ruanko.dao.impl.CommentDaoImpl;
import com.ruanko.dao.impl.OrderDaoImpl;
import com.ruanko.dao.impl.TourismDaoImpl;
import com.ruanko.dao.impl.TourismImagesDaoImpl;
import com.ruanko.dao.impl.TourismRuleDaoImpl;
import com.ruanko.model.Comment;
import com.ruanko.model.PageModel;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismBo;
import com.ruanko.model.TourismImage;
import com.ruanko.model.TourismInfo;
import com.ruanko.model.TourismRule;
import com.ruanko.utils.AppException;

public class TourismService {
	// 准备TourismDao
	private TourismDao tourismDao = new TourismDaoImpl();
	// 准备TourismRuleDao
	private TourismRuleDao tourismRuleDao = new TourismRuleDaoImpl();
	// 准备TourismImagesDao
	private TourismImagesDao tourismImagesDao = new TourismImagesDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private CommentDao commentDao = new CommentDaoImpl();

	/**
	 * 添加旅游相关信息
	 * 
	 * @param tourismInfo
	 * @return
	 * @throws AppException
	 */
	public boolean addToursimInfo(TourismInfo tourismInfo,
			HttpServletRequest request) throws AppException {
		boolean flag = false;
		// 先保存旅游基本并取出旅游基本信息的ID 跟旅游规则相关联
		int tourismId = tourismDao.add(tourismInfo.getTourism());
		request.getSession().setAttribute("tourismId", tourismId);
		// 遍历准备的TourismRule的List数据 进行存储
		for (TourismRule tourismRule : tourismInfo.getTourismRules()) {
			tourismRule.setTourism_id(tourismId);
			flag = tourismRuleDao.add(tourismRule);
		}
		if (tourismId != -1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 添加旅游图片
	 * 
	 * @param tourismImage
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public boolean addTourismImages(TourismImage tourismImage,
			HttpServletRequest request) throws AppException {
		// 给旅游图片添加旅游信息ID 进行关联
		int tourismId = Integer.parseInt(request.getSession()
				.getAttribute("tourismId").toString());
		tourismImage.setTourism_id(tourismId);
		// 进行存储图片
		boolean flag = tourismDao.add(tourismImage);
		return flag;
	}

	/**
	 * 分页返回旅游信息数据
	 * 
	 * @param page当前页码
	 * @param size每页多少条记录
	 * @return
	 * @throws AppException
	 */
	public PageModel getList(final int page, final int size)
			throws AppException {
		// 根据记录总数求出 分页总数
		int count = tourismDao.getCount();
		PageModel pageModel = new PageModel(count, size, page);
		List<Tourism> tourismList = tourismDao.getList(pageModel.getPage(),
				pageModel.getSize());
		// 将旅游相关的所有信息存入TourismInfo中
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		for (Tourism tourism : tourismList) {
			// 准备数据
			TourismInfo tourismInfo = new TourismInfo();
			int tourismId = tourism.getID();
			System.out.println("service:" + tourismId);
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(tourismId);
			List<TourismRule> tourismRules = tourismRuleDao
					.getListByTourismId(tourismId);
			// 准备TourismInfo
			tourismInfo.setTourism(tourism);
			tourismInfo.setTourismImages(tourismImages);
			tourismInfo.setTourismRules(tourismRules);
			// 将旅游相关信息对象存入集合中返回
			tourismInfos.add(tourismInfo);
		}
		pageModel.setList(tourismInfos);
		return pageModel;
	}

	/**
	 * 根据旅游信息ID获取相关的旅游规则
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public List<TourismRule> getListByTourism(int tourismid)
			throws AppException {
		List<TourismRule> tourismRules = tourismRuleDao
				.getListByTourismId(tourismid);
		return tourismRules;
	}

	/**
	 * 根据ID返回指定的旅游基本信息数据
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	public Tourism getTourismById(int tourismId) throws AppException {
		// 调用TourismDao层返回指定的旅游基本信息
		Tourism tourism = tourismDao.getTourism(tourismId);
		return tourism;
	}

	/**
	 * 根据旅游信息类型返回旅游数据
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param type旅游类型
	 * @return
	 * @throws AppException
	 */
	public PageModel getListByType(int page, int size, int type)
			throws AppException {
		// 根据记录总数求出 分页总数
		int count = tourismDao.getCountByType(type);
		PageModel pageModel = new PageModel(count, size, page);
		List<Tourism> tourismList = tourismDao.getListByType(
				pageModel.getPage(), pageModel.getSize(), type);
		// 将旅游相关的所有信息存入TourismInfo中
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		for (Tourism tourism : tourismList) {
			// 准备数据
			TourismInfo tourismInfo = new TourismInfo();
			int tourismId = tourism.getID();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(tourismId);
			List<TourismRule> tourismRules = tourismRuleDao
					.getListByTourismId(tourismId);
			// 准备TourismInfo
			tourismInfo.setTourism(tourism);
			tourismInfo.setTourismImages(tourismImages);
			tourismInfo.setTourismRules(tourismRules);
			// 将旅游相关信息对象存入集合中返回
			tourismInfos.add(tourismInfo);
		}
		pageModel.setList(tourismInfos);
		return pageModel;
	}

	/**
	 * 根据价格对旅游信息进行排序
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param order排序方式
	 *            0升序 1降序
	 * @return
	 * @throws AppException
	 */
	public PageModel getListOrderByPrice(int page, int size, int order)
			throws AppException {
		// 根据记录总数求出 分页总数
		int count = tourismDao.getCount();
		PageModel pageModel = new PageModel(count, size, page);
		List<Tourism> tourismList = tourismDao.getListOrderByPrice(
				pageModel.getPage(), pageModel.getSize(), order);
		// 将旅游相关的所有信息存入TourismInfo中
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		for (Tourism tourism : tourismList) {
			// 准备数据
			TourismInfo tourismInfo = new TourismInfo();
			int tourismId = tourism.getID();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(tourismId);
			List<TourismRule> tourismRules = tourismRuleDao
					.getListByTourismId(tourismId);
			// 准备TourismInfo
			tourismInfo.setTourism(tourism);
			tourismInfo.setTourismImages(tourismImages);
			tourismInfo.setTourismRules(tourismRules);
			// 将旅游相关信息对象存入集合中返回
			tourismInfos.add(tourismInfo);
		}
		pageModel.setList(tourismInfos);
		return pageModel;
	}

	/**
	 * 根据价格范围 来查询旅游信息
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param startprice最低价格
	 * @param endprice最高价格
	 * @return
	 * @throws AppException
	 */
	public PageModel getTourismByScope(int page, int size, int startprice,
			int endprice) throws AppException {
		// 根据记录总数求出 分页总数
		int count = tourismDao.getCountByScope(startprice, endprice);
		PageModel pageModel = new PageModel(count, size, page);
		System.out.println(pageModel.getSize());
		List<Tourism> tourismList = tourismDao.getListByPriceScope(
				pageModel.getPage(), pageModel.getSize(), startprice, endprice);
		// 将旅游相关的所有信息存入TourismInfo中
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		for (Tourism tourism : tourismList) {
			// 准备数据
			TourismInfo tourismInfo = new TourismInfo();
			int tourismId = tourism.getID();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(tourismId);
			List<TourismRule> tourismRules = tourismRuleDao
					.getListByTourismId(tourismId);
			// 准备TourismInfo
			tourismInfo.setTourism(tourism);
			tourismInfo.setTourismImages(tourismImages);
			tourismInfo.setTourismRules(tourismRules);
			// 将旅游相关信息对象存入集合中返回
			tourismInfos.add(tourismInfo);
		}
		pageModel.setList(tourismInfos);
		return pageModel;
	}

	/**
	 * 根据旅游基本信息ID返回旅游相关的所有信息 TourismInfo
	 * 
	 * @param tourismid
	 * @throws AppException
	 */
	public TourismInfo getToursimInfoById(int tourismId) throws AppException {
		TourismInfo tourismInfo = new TourismInfo();
		// 调用DAO查询出相关信息
		Tourism tourism = tourismDao.getTourism(tourismId);
		List<TourismImage> tourismImages = tourismImagesDao
				.getTourismImages(tourismId);
		List<TourismRule> tourismRules = tourismRuleDao
				.getListByTourismId(tourismId);
		// 将相关信息存入TourismInfo中返回
		tourismInfo.setTourism(tourism);
		tourismInfo.setTourismImages(tourismImages);
		tourismInfo.setTourismRules(tourismRules);

		return tourismInfo;
	}

	/**
	 * 判断是否有权限进行评论
	 * 
	 * @param userId
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public boolean isRightComment(int userId, int tourismid)
			throws AppException {
		boolean flag = false;
		// 判断是否对当前旅游信息出游资格 查询出具有出游资格的记录
		List<TourismBo> tourismBos = orderDao.isTourism(userId, tourismid);
		for (TourismBo tourismBo : tourismBos) {
			// 判断该用户是否已经出游回归
			boolean flag2 = tourismDao.isTourism(tourismBo.getTourismid(),
					tourismBo.getTourismTime());
			flag = flag2 | flag;
		}
		return flag;
	}

	/**
	 * 获取用户能够评论的旅游信息的分页Bean
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getUserCommentableList(int userId, int page, int size)
			throws AppException {
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();// 准备返回的数据
		List<Tourism> tourismlList = new ArrayList<Tourism>();
		List<Tourism> tList = tourismDao.getList();
		for (Tourism t : tList) {
			if (isRightComment(userId, t.getID())) {
				tourismlList.add(t);
			}
		}
		// List中进行分页
		int start = (page - 1) * 10;
		int end;
		if ((tourismlList.size() - start) < size) {
			end = tourismlList.size() - start;
		} else {
			end = start + size;
		}
		List<Tourism> tourismsResult = new ArrayList<Tourism>();
		for (int i = start; i < end; i++) {
			tourismsResult.add(tourismlList.get(i));
		}
		for (Tourism tourism : tourismsResult) {
			TourismInfo tourismInfo = new TourismInfo();
			List<TourismImage> tourismImages = tourismImagesDao
					.getTourismImages(tourism.getID());
			tourismInfo.setTourism(tourism);
			tourismInfo.setTourismImages(tourismImages);
			tourismInfos.add(tourismInfo);
		}
		PageModel pageModel = new PageModel(tList.size(), size, page);
		pageModel.setList(tourismInfos);
		return pageModel;
	}

	/**
	 * 根据旅游规则ID返回旅游规则数据
	 * 
	 * @param tourismtimeRuleId
	 * @return
	 * @throws AppException
	 */
	public TourismRule getTourismRuleById(int tourismtimeRuleId)
			throws AppException {
		TourismRule tourismRule = tourismRuleDao
				.getTourismRule(tourismtimeRuleId);
		return tourismRule;
	}

	/**
	 * 根据旅游类型和ID排序返回指定长度的旅游信息记录
	 * 
	 * @param size返回数据的长度
	 * @param tpye旅游类型
	 * @return
	 * @throws AppException
	 */
	public List<TourismInfo> getTourismListOrderById(int size, int type)
			throws AppException {
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		List<Tourism> tourisms = new ArrayList<Tourism>();
		tourisms = tourismDao.findTourismListOrderById(size, type);
		for (Tourism tourism : tourisms) {
			TourismInfo tourismInfo = new TourismInfo();
			// 计算满意度
			List<Comment> commentlList = commentDao.findListByTourismId(tourism
					.getID());
			double commentCount = commentlList.size();// 总评价次数
			double allSatisfaction = commentDao.getAllSatisfaction(tourism
					.getID());// 返回该旅游信息的满意度之和
			double satisfactionPercent;
			if (commentCount != 0) {
				satisfactionPercent = allSatisfaction / (3 * commentCount);// 满意度
			} else {
				satisfactionPercent = 1.0;
			}
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			String satisfactionPercentString = nt.format(satisfactionPercent);

			tourismInfo.setTourism(tourism);
			tourismInfo.setSatisfactionPercent(satisfactionPercentString);
			tourismInfo.setTourismImages(tourismImagesDao
					.getTourismImages(tourism.getID()));
			tourismInfos.add(tourismInfo);
		}
		return tourismInfos;
	}

	/**
	 * 根据旅游信息分组 按照订单人数排序 返回指定长度的旅游信息数据 返回热门旅游信息
	 * 
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<TourismInfo> getTourismListGroupByTourismidOrderbyNum(int size)
			throws AppException {
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();
		List<Tourism> tourisms = new ArrayList<Tourism>();
		List<Integer> tourismIds = new ArrayList<Integer>();
		tourismIds = orderDao.findTourismIdGroupByTourismidOrderbyNum(size);

		for (int tourismId : tourismIds) {
			Tourism tourism = tourismDao.getTourism(tourismId);
			tourisms.add(tourism);
		}
		for (Tourism tourism : tourisms) {
			TourismInfo tourismInfo = new TourismInfo();
			// 计算满意度
			List<Comment> commentlList = commentDao.findListByTourismId(tourism
					.getID());
			double commentCount = commentlList.size();// 总评价次数
			double allSatisfaction = commentDao.getAllSatisfaction(tourism
					.getID());// 返回该旅游信息的满意度之和
			double satisfactionPercent;
			if (commentCount != 0) {
				satisfactionPercent = allSatisfaction / (3 * commentCount);// 满意度
			} else {
				satisfactionPercent = 1.0;
			}
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			String satisfactionPercentString = nt.format(satisfactionPercent);

			tourismInfo.setTourism(tourism);
			tourismInfo.setSatisfactionPercent(satisfactionPercentString);
			tourismInfo.setTourismImages(tourismImagesDao
					.getTourismImages(tourism.getID()));
			tourismInfos.add(tourismInfo);
		}
		return tourismInfos;

	}

	/**
	 * 根据销量进行排行
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getListBySales(int page, int size) throws AppException {
		// 根据记录总数求出 分页总数
		List<TourismInfo> tourismInfos = new ArrayList<TourismInfo>();// 存放要返回的页面旅游数据
		int count = tourismDao.getCount();
		PageModel pageModel = new PageModel(count, size, page);
		List<Integer> tourismIds = orderDao.getListBySales(pageModel.getPage(),
				pageModel.getSize());
		// List中进行分页
		int start = (page - 1) * 10;
		int end;
		if ((tourismIds.size() - start) < size) {
			end = tourismIds.size() - start;
		} else {
			end = start + size;
		}

		for (int i = start; i < end; i++) {
			// 计算满意度
			List<Comment> commentlList = commentDao.findListByTourismId(i);
			double commentCount = commentlList.size();// 总评价次数
			double allSatisfaction = commentDao.getAllSatisfaction(i);// 返回该旅游信息的满意度之和
			double satisfactionPercent;
			if (commentCount != 0) {
				satisfactionPercent = allSatisfaction / (3 * commentCount);// 满意度
			} else {
				satisfactionPercent = 1.0;
			}
			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			String satisfactionPercentString = nt.format(satisfactionPercent);

			// 存放数据
			TourismInfo tourismInfo = new TourismInfo();
			tourismInfo.setTourism(tourismDao.getTourism(i));
			tourismInfo.setTourismImages(tourismImagesDao.getTourismImages(i));
			tourismInfo.setTourismRules(tourismRuleDao.getListByTourismId(i));
			tourismInfo.setSatisfactionPercent(satisfactionPercentString);
			tourismInfos.add(tourismInfo);
		} 

		pageModel.setList(tourismInfos);
		return pageModel;
	}
}
