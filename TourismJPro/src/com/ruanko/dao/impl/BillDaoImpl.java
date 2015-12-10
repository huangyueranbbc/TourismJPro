package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.BillDao;
import com.ruanko.model.Bill;
import com.ruanko.model.Order;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 账单的Dao实现
 * 
 * @author rkcoe
 * 
 */
public class BillDaoImpl implements BillDao {

	/**
	 * 添加账单信息
	 * 
	 * @param bill
	 * @return
	 * @throws AppException
	 */
	public boolean add(Bill bill) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取订单
			// 声明SQL操作语句 插入订单记录
			String sql = "insert into t_bill(user_id,order_id,NO,user_name,recv_account,bank,pay_account,currency,money,paymethod,type,status,remarks,del) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bill.getUser_id());
			ps.setInt(2, bill.getOrder_id());
			ps.setString(3, bill.getNO());
			ps.setString(4, bill.getUser_name());
			ps.setString(5, bill.getRecv_account());
			ps.setString(6, bill.getBank());
			ps.setFloat(7, bill.getPay_account());
			ps.setInt(8, bill.getCurrency());
			ps.setFloat(9, bill.getMoney());
			ps.setString(10, bill.getPaymethod());
			ps.setInt(11, bill.getType());
			ps.setInt(12, bill.getStatus());
			ps.setString(13, bill.getRemarks());
			ps.setInt(14, bill.getDel());

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
			throw new AppException("com.ruanko.dao.impl.BillDaoImpl.add(Bill)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.dao.impl.BillDaoImpl.add(Bill)");
		} finally {
			// 数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.add(Bill)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.add(Bill)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}

	/**
	 * 根据订单ID修改账单状态
	 * 
	 * @param del
	 * @param orderid
	 * @param status
	 * @return
	 * @throws AppException
	 */
	public boolean updateBillDelByOrderId(int del, int status, int orderid)
			throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;
		Bill bill = new Bill();
		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_bill set status = ?,del = ? where order_id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, del);
			ps.setInt(3, orderid);
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
					"com.ruanko.dao.impl.BillDaoImpl.updateBillDelByOrderId(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.updateBillDelByOrderId(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.updateBillDelByOrderId(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.updateBillDelByOrderId(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}

	/**
	 * 根据订单ID查询账单
	 * 
	 * @param orderid
	 * @return
	 * @throws AppException
	 */
	public Bill findBillByOrderId(int orderid) throws AppException {
		Bill bill = new Bill();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_bill where order_id =?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, orderid);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			if (result.next()) {
				bill.setID(result.getInt(1));
				bill.setUser_id(result.getInt(2));
				bill.setOrder_id(result.getInt(3));
				bill.setNO(result.getString(4));
				bill.setUser_name(result.getString(5));
				bill.setRecv_account(result.getString(6));
				bill.setBank(result.getString(7));
				bill.setPay_account(result.getFloat(8));
				bill.setCurrency(result.getInt(9));
				bill.setMoney(result.getFloat(10));
				bill.setPaymethod(result.getString(11));
				bill.setType(result.getInt(12));
				bill.setStatus(result.getInt(13));
				bill.setRemarks(result.getString(14));
				bill.setDel(result.getInt(15));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.findBillByOrderId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.findBillByOrderId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.findBillByOrderId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.findBillByOrderId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return bill;
	}

	/**
	 * 根据用户ID返回账单数量
	 * 
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public int getCountByUserId(int userId) throws AppException {
		int pageCount;// 旅游信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select id from t_bill where user_id = ?";
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
					"com.ruanko.dao.impl.BillDaoImpl.getCountByUserId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.getCountByUserId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.getCountByUserId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.getCountByUserId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 根据用户ID返回用户账户信息
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Bill> getListByUserId(int userId, int page, int size)
			throws AppException {

		List<Bill> bills = new ArrayList<Bill>();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的账户信息
			String sql = "select * from t_bill where user_id = ? limit ?,?";
			// 预编译SQL语句 
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, (page - 1) * 10);
			ps.setInt(3, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Bill bill = new Bill();
				bill.setID(result.getInt(1));
				bill.setUser_id(result.getInt(2));
				bill.setOrder_id(result.getInt(3));
				bill.setNO(result.getString(4));
				bill.setUser_name(result.getString(5));
				bill.setRecv_account(result.getString(6));
				bill.setBank(result.getString(7));
				bill.setPay_account(result.getFloat(8));
				bill.setCurrency(result.getInt(9));
				bill.setMoney(result.getFloat(10));
				bill.setPaymethod(result.getString(11));
				bill.setType(result.getInt(12));
				bill.setStatus(result.getInt(13));
				bill.setRemarks(result.getString(14));
				bill.setDel(result.getInt(15));
				bills.add(bill);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.getListByUserId(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BillDaoImpl.getListByUserId(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.getListByUserId(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BillDaoImpl.getListByUserId(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return bills;
	}

}
