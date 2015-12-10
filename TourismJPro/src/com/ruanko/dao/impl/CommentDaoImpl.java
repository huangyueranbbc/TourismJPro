package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.CommentDao;
import com.ruanko.model.Comment;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 订单DAO层实现
 * 
 * @author rkcoe
 * 
 */
public class CommentDaoImpl implements CommentDao {

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
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取用户
			// 声明SQL操作语句 插入用户记录
			String sql = "insert into t_comment(toursim_id,user_id,userName,satisfaction,content) values(?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismId);
			ps.setInt(2, userId);
			ps.setString(3, name);
			ps.setInt(4, satisfaction);
			ps.setString(5, content);
			// 执行更新操作
			int result = ps.executeUpdate();
			connection.commit();
			// 3.根据查询记录，判断是否注册成功
			// 注册成功
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			// 注册失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.add(int, int, String, String, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.add(int, int, String, String, int)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.add(int, int, String, String, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.add(int, int, String, String, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;
	}

	/**
	 * 查询该旅游主题的评论
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public List<Comment> findListByTourismId(int tourismid) throws AppException {
		List<Comment> comments = new ArrayList<Comment>();// 返回旅游规则数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出该旅游主题下所有的评论
			String sql = "select * from t_comment where toursim_id = ? and del = 0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismid);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Comment comment = new Comment();
				comment.setID(result.getInt(1));
				comment.setTourism_id(result.getInt(2));
				comment.setUser_id(result.getInt(3));
				comment.setUserName(result.getString(4));
				comment.setSatisfaction(result.getInt(5));
				comment.setContent(result.getString(6));
				comment.setCreateDate(result.getTimestamp(7));
				comment.setDel(result.getInt(8));
				comments.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.findListByTourismId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.findListByTourismId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.findListByTourismId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.findListByTourismId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return comments;
	}

	/**
	 * 返回该旅游信息的满意度之和
	 * 
	 * @param tourismid
	 * @return
	 * @throws AppException
	 */
	public int getAllSatisfaction(int tourismid) throws AppException {
		int allSatisfaction = 0;// 旅游信息满意度之和
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select sum(satisfaction) from t_comment where toursim_id = ? and del = 0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismid);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			if (result.next()) {
				allSatisfaction = result.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getAllSatisfaction(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getAllSatisfaction(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getAllSatisfaction(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getAllSatisfaction(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return allSatisfaction;
	}

	/**
	 * 根据用户ID返回用户评论总数
	 * 
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public int getCountByUserId(int userId) throws AppException {
		int pageCount;// 用户评论信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的评论数量
			String sql = "select id from t_comment where user_id = ? and del =0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			result.last();
			pageCount = result.getRow();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getCountByUserId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getCountByUserId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getCountByUserId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getCountByUserId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 根据用户ID查询用户分页评论数据
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Comment> findListByUserId(int userId, int page, int size)
			throws AppException {
		List<Comment> comments = new ArrayList<Comment>();// 返回旅游规则数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出该旅游主题下所有的评论
			String sql = "select * from t_comment where user_id = ? and del = 0 limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, (page - 1) * 10);
			ps.setInt(3, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Comment comment = new Comment();
				comment.setID(result.getInt(1));
				comment.setTourism_id(result.getInt(2));
				comment.setUser_id(result.getInt(3));
				comment.setUserName(result.getString(4));
				comment.setSatisfaction(result.getInt(5));
				comment.setContent(result.getString(6));
				comment.setCreateDate(result.getTimestamp(7));
				comment.setDel(result.getInt(8));
				comments.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.findListByUserId(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.findListByUserId(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.findListByUserId(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.findListByUserId(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return comments;
	}

	/**
	 * 返回评论总数
	 * 
	 * @return
	 * @throws AppException
	 */
	public int getCount() throws AppException {
		int pageCount;// 用户评论信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的评论数量
			String sql = "select id from t_comment where del =0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			result.last();
			pageCount = result.getRow();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getCount()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getCount()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getCount()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getCount()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 返回所有评论信息的分页数据
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Comment> getList(int page, int size) throws AppException {
		List<Comment> comments = new ArrayList<Comment>();// 返回旅游规则数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出该旅游主题下所有的评论
			String sql = "select * from t_comment where del = 0 limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Comment comment = new Comment();
				comment.setID(result.getInt(1));
				comment.setTourism_id(result.getInt(2));
				comment.setUser_id(result.getInt(3));
				comment.setUserName(result.getString(4));
				comment.setSatisfaction(result.getInt(5));
				comment.setContent(result.getString(6));
				comment.setCreateDate(result.getTimestamp(7));
				comment.setDel(result.getInt(8));
				comments.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getList(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.getList(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getList(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.getList(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return comments;
	}

	/**
	 * 根据评论ID删除评论
	 * 
	 * @param commentId
	 * @return
	 * @throws AppException
	 */
	public boolean delete(int commentId) throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_comment set del = ? where id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, commentId);
			// 执行SQL查询操作
			int result = ps.executeUpdate();
			connection.commit();
			// 3.修改成功后 返回用户修改后的登录信息
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			// 登录成功
			// 登录失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.delete(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.CommentDaoImpl.delete(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException( 
							"com.ruanko.dao.impl.CommentDaoImpl.delete(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.CommentDaoImpl.delete(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag; 
	}

}
