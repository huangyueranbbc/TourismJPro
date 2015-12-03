package com.ruanku.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ruanku.dao.UserDao;
import com.ruanku.model.User;
import com.ruanku.utils.AppException;
import com.ruanku.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	/*
	 * 判断是否存在此用户 若存在返回true
	 */
	public boolean isExist(String username) throws AppException {
		boolean flag = false;// 创建数据库连接
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
			// 根据查询记录，如果用户名存在，则返回true;
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
		// 如果存在此用户

		// 如果不存在此用户
		return flag;
	}

	/*
	 * 保存用户
	 */
	public boolean save(User user) throws AppException {
		boolean flag = false;// 创建数据库连接
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBUtil.getConnection();

			// 2.存取用户
			String sql = "insert into t_user(name,password,phone,email,type,status,del) values(?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getType());
			ps.setInt(6, user.getStatus());
			ps.setInt(7, user.getDel());
			int result = ps.executeUpdate(); 
			System.out.println(user.toString());
			// 注册成功
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			// 注册失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.save(User)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.dao.impl.UserDaoImpl.save(User)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.save(User)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.save(User)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;

	}
}
