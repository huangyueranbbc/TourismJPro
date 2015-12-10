package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.Comment;
import com.ruanko.utils.AppException;

/**
 * 评论DAO层接口
 * 
 * @author rkcoe
 * 
 */
public interface CommentDao {

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
	boolean add(int tourismId, int userId, String name, String content,
			int satisfaction) throws AppException;

	/**
	 * 查询该旅游主题的评论
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	List<Comment> findListByTourismId(int tourismid) throws AppException;

	/**
	 * 返回该旅游信息的满意度之和
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	int getAllSatisfaction(int tourismid) throws AppException;

	/**
	 * 根据用户ID返回用户评论总数
	 * 
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	int getCountByUserId(int userId) throws AppException;

	/**
	 * 根据用户ID查询用户分页评论数据
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Comment> findListByUserId(int userId, int page, int size)
			throws AppException;

	/**
	 * 返回评论总数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getCount() throws AppException;

	/**
	 * 返回所有评论信息的分页数据
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<Comment> getList(int page, int size) throws AppException;

	/**
	 * 根据评论ID删除评论
	 * 
	 * @param commentId
	 * @return
	 * @throws AppException
	 */
	boolean delete(int commentId)throws AppException;

}
