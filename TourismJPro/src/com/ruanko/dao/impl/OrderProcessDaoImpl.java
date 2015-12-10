package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.OrderProcessDao;
import com.ruanko.model.Order;
import com.ruanko.model.OrderProcess;
import com.ruanko.model.TourismRule;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 订单DAO实现
 * 
 * @author rkcoe
 * 
 */
public class OrderProcessDaoImpl implements OrderProcessDao {

	/**
	 * 添加订单流程信息
	 * 
	 * @param orderProcess
	 * @return
	 * @throws AppException
	 */
	public boolean add(OrderProcess orderProcess) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取订单流程
			// 声明SQL操作语句 插入订单流程记录
			String sql = "insert into t_orderprocess(user_id,order_id,type,dateTime,del) values(?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, orderProcess.getUser_id());
			ps.setInt(2, orderProcess.getOrder_id());
			ps.setInt(3, orderProcess.getType());
			ps.setTimestamp(4, orderProcess.getDateTime());
			ps.setInt(5, orderProcess.getDel());

			// 执行更新操作
			int result = ps.executeUpdate();
			connection.commit();
			// 3.根据返回记录，判断是否添加成功
			// 添加成功
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderProcessDaoImpl.add(OrderProcess)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderProcessDaoImpl.add(OrderProcess)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderProcessDaoImpl.add(OrderProcess)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderProcessDaoImpl.add(OrderProcess)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;
	}

	/**
	 * 根据订单ID更新订单流程状态
	 * 
	 * @param id
	 * @param type
	 * @return
	 * @throws AppException
	 */
	public boolean updateTypeByOrderId(int id,int type) throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;
		UserBo userBo = new UserBo();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_orderprocess set type = ? where id = ?";
			// 预编译SQL语句  
			ps = connection.prepareStatement(sql); 
			ps.setInt(1, type);  
			ps.setInt(2, id);
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
					"com.ruanko.dao.impl.OrderProcessDaoImpl.updateTypeByOrderId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderProcessDaoImpl.updateTypeByOrderId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderProcessDaoImpl.updateTypeByOrderId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderProcessDaoImpl.updateTypeByOrderId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}
}
