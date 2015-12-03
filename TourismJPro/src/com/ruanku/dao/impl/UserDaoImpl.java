package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ruanku.dao.UserDao;
import com.ruanku.model.User;
import com.ruanku.model.UserBo;
import com.ruanku.utils.AppException;
import com.ruanku.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 判断是否存在此用户 若存在返回true
	 */
	public boolean isExist(String username) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建连接
			connection = DBUtil.getConnection();
			// 2.数据库进行查询 判断是否存在此用户
			// 声明SQL操作语句 根据用户名查询用户记录
			String sql = "select id from t_user where name = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			// 执行查询操作
			ResultSet result = ps.executeQuery();
			// 3.根据查询记录，如果用户名存在，则返回true;
			if (result.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.isExist(String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.isExist(String)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.isExist(String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.isExist(String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}

	/**
	 * 保存用户
	 */
	public boolean add(User user) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取用户
			// 声明SQL操作语句 插入用户记录
			String sql = "insert into t_user(name,password,phone,email,type,status,del) values(?,?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getType());
			ps.setInt(6, user.getStatus());
			ps.setInt(7, user.getDel());
			// 执行更新操作
			int result = ps.executeUpdate();

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
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.add(User)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.add(User)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.add(User)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.add(User)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;

	}

	/**
	 * 用户登录
	 */
	public UserBo findUserByNameAndPwd(String username, String password)
			throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		UserBo user = new UserBo();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询用户记录是否存在
			String sql = "select id,name,type from t_user where name = ? and password = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			// 3.根据查询记录，如果存在用户记录，则返回该用户记录
			if (result.next()) {
				user.setUserId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setType(result.getInt("type"));
				return user;
			} else {
				return null;
			}
			// 登录成功
			// 登录失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
				}
			}
			DBUtil.closeConnection(connection);
		}

	}
}
