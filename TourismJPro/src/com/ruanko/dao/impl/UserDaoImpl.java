package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.UserDao;
import com.ruanko.model.User;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 判断是否存在此用户 若存在返回true
	 * 
	 * @param username
	 * @return
	 * @throws AppException
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
			connection.commit();
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
	 * 
	 * @param user
	 * @return
	 * @throws AppException
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
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public UserBo findUserByNameAndPwd(String username, String password)
			throws AppException {
		Connection connection = null;
		PreparedStatement ps = null;
		UserBo user = new UserBo();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询用户记录是否存在
			String sql = "select id,name,type from t_user where name = ? and password = ? and status = 0";
			// 预编译SQL语句 
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 3.根据查询记录，如果存在用户记录，则返回该用户记录
			if (result.next()) {
				// 登录成功
				user.setUserId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setType(result.getInt("type"));
				return user;
			} else {
				// 登录失败
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.findUserByNameAndPwd(String, String)");
		} finally {
			// 关闭数据库资源
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

	/**
	 * 通过用户ID修改用户密码和姓名
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public UserBo updateUserNameAndPwdById(int userId, String name,
			String password) throws AppException {
		Connection connection = null;
		PreparedStatement ps = null;
		UserBo userBo = new UserBo();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_user set name = ?,password = ? where id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, userId);
			// 执行SQL查询操作
			int result = ps.executeUpdate();
			connection.commit();
			// 3.修改成功后 返回用户修改后的登录信息
			if (result > 0) {
				userBo.setName(name);
				userBo.setUserId(userId);
				return userBo;
			} else {
				return null;
			}
			// 登录成功
			// 登录失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateUserNameAndPwdById(int, String, String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.UserDaoImpl.updateUserNameAndPwdById(int, String, String)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.updateUserNameAndPwdById(int, String, String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.UserDaoImpl.updateUserNameAndPwdById(int, String, String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * 返回非管理员用户列表总数
	 * 
	 * @return
	 * @throws AppException
	 */
	public int getCount() throws AppException {
		int pageCount;// 非管理员信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的非管理员用户记录
			String sql = "select id from t_user where type = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, 0);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			result.last();
			pageCount = result.getRow();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.dao.impl.UserDaoImpl.getCount()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.dao.impl.UserDaoImpl.getCount()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.getCount()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.getCount()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 返回非管理员的用户列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<User> getList(int page, int size) throws AppException {
		List<User> userList = new ArrayList<User>();// 返回分页模型的用户信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的非管理员用户记录
			String sql = "select * from t_user where type = ? limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, (page - 1) * 10);
			ps.setInt(3, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				User user = new User();
				user.setID(result.getInt(1));
				user.setName(result.getString(2));
				user.setPassword(result.getString(3));
				user.setPhone(result.getString(4));
				user.setEmail(result.getString(5));
				user.setType(result.getInt(6));
				user.setStatus(result.getInt(7));
				user.setDel(result.getInt(8));
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.UserDaoImpl.getList(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.UserDaoImpl.getList(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.getList(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.getList(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return userList;
	}

	/**
	 * 根据用户ID更改用户状态
	 * 
	 * @param userId
	 * @param userStatus
	 * @return
	 * @throws AppException 
	 */
	public boolean updateUserStatusById(int userId, int userStatus)
			throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_user set status = ? where id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userStatus);
			ps.setInt(2, userId); 
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
					"com.ruanko.dao.impl.UserDaoImpl.updateUserStatusById(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.UserDaoImpl.updateUserStatusById(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.updateUserStatusById(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.UserDaoImpl.updateUserStatusById(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}
}
