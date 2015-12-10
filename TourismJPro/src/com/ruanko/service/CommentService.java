package com.ruanko.service;

import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.CommentDao;
import com.ruanko.dao.TourismDao;
import com.ruanko.dao.impl.CommentDaoImpl;
import com.ruanko.dao.impl.TourismDaoImpl;
import com.ruanko.model.Comment;
import com.ruanko.model.CommentInfo;
import com.ruanko.model.PageModel;
import com.ruanko.model.Tourism;
import com.ruanko.utils.AppException;

/**
 * 评论的Service层
 * 
 * @author rkcoe
 * 
 */
public class CommentService {
	private CommentDao commentDao = new CommentDaoImpl();
	private TourismDao tourismDao=new TourismDaoImpl();

	/**
	 * 添加用户发表的评论
	 * 
	 * @param tourismId旅游信息ID
	 * @param userId评论用户ID
	 * @param name评论用户名
	 * @param content评论内容
	 * @param satisfaction评价度
	 * @return
	 * @throws AppException
	 */
	public boolean add(int tourismId, int userId, String name, String content,
			int satisfaction) throws AppException {
		boolean flag;
		flag = commentDao.add(tourismId, userId, name, content, satisfaction);
		return flag;
	}

	/**
	 * 查询出该旅游主题的所有评论
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public List<Comment> getListByTourismId(int tourismid) throws AppException {
		List<Comment> commentlList = commentDao.findListByTourismId(tourismid);
		return commentlList;
	}

	/**
	 * 返回该旅游信息的满意度之和
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public int getAllSatisfaction(int tourismid) throws AppException {
		int allSatisfaction;
		allSatisfaction = commentDao.getAllSatisfaction(tourismid);
		return allSatisfaction;
	}

	/**
	 * 根据用户ID 得到用户评论数据
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getListByUserId(int userId, int page, int size)
			throws AppException {
		List<CommentInfo> commentInfos=new ArrayList<CommentInfo>();
		// 根据记录总数求出 分页总数
		int count = commentDao.getCountByUserId(userId);
		PageModel pageModel = new PageModel(count, size, page);
		List<Comment> Comments = commentDao.findListByUserId(userId,
				pageModel.getPage(), pageModel.getSize());
		
		for(Comment comment:Comments){
			Tourism tourism = tourismDao.getTourism(comment.getTourism_id());
			CommentInfo commentInfo=new CommentInfo();
			commentInfo.setCommentTitle(tourism.getTitle());
			commentInfo.setComment(comment);
			commentInfos.add(commentInfo);
		}
		pageModel.setList(commentInfos);
		return pageModel; 
	}

	/**
	 * 获取所有用户评论信息
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getList(int page, int size) throws AppException{
		// 根据记录总数求出 分页总数
		int count = commentDao.getCount();
		//准备分页数据
		PageModel pageModel = new PageModel(count, size, page);
		List<Comment> Comments = commentDao.getList(pageModel.getPage(), pageModel.getSize());
		
		pageModel.setList(Comments);
		return pageModel; 
	}

	/**
	 * 根据ID删除评论
	 * @param commentId
	 * @return
	 * @throws AppException
	 */
	public boolean removeCommentById(int commentId)throws AppException {
		boolean flag;
		flag=commentDao.delete(commentId);
		return flag;
	}

}
