package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruanko.dao.BankDao;
import com.ruanko.model.Bank;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 银行系统的Dao实现
 * 
 * @author rkcoe
 * 
 */
public class BankDaoImpl implements BankDao {

	/**
	 * 是否存在此银行用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public Bank getBankUserInfo(String name, String password)
			throws AppException {
		Connection connection = null;
		PreparedStatement ps = null;
		Bank bank = new Bank();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询银行账户记录是否存在
			String sql = "select * from t_bank where name = ? and password = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 3.根据查询记录，如果存在银行账户记录，则返回该银行账户数据
			if (result.next()) {
				// 存在此银行账户 返回此银行用户数据
				bank.setId(result.getInt(1));
				bank.setName(result.getString(2));
				bank.setPassword(result.getString(3));
				bank.setMoney(result.getInt(4));
				bank.setAppid(result.getString(5));
				return bank;
			} else {
				// 不存在此银行账户 返回Null
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.getBankUserInfo(String, String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.getBankUserInfo(String, String)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.getBankUserInfo(String, String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.getBankUserInfo(String, String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * 判断是否是银行伙伴账号 如果是 则返回该账户数据
	 * 
	 * @param recv_account
	 * @param appId
	 * @return
	 * @throws AppException
	 */
	public Bank isPartner(String recv_account, String appId)
			throws AppException {
		Connection connection = null;
		PreparedStatement ps = null;
		Bank bank = new Bank();

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询用户记录是否存在
			String sql = "select * from t_bank where name = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, recv_account);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 3.根据查询记录，如果存在该银行伙伴，则返回该银行账户记录
			if (result.next()) {
				// 存在此银行账户 返回此银行用户数据
				bank.setId(result.getInt(1));
				bank.setName(result.getString(2));
				bank.setPassword(result.getString(3));
				bank.setMoney(result.getInt(4));
				bank.setAppid(result.getString(5));
				// 判断是否是银行合作伙伴 如果是
				if (appId.equals(bank.getAppid())) {
					return bank;
				} else {
					return null;
				}
			} else {
				// 不存在此银行账户 返回Null
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.isPartner(String, String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.isPartner(String, String)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.isPartner(String, String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.isPartner(String, String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
	}

	/**
	 * 根据付款账号和支付账号进行付款
	 * 
	 * @param bank
	 * @param recv_bank
	 * @param money
	 * @return
	 * @throws AppException
	 */
	public int pay(Bank bank, Bank recv_bank, float money) throws AppException {
		Connection connection = null;
		PreparedStatement ps = null;
		int resultType = 30;// 返回的支付状态

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 进行支付
			// 准备数据
			bank.setMoney(bank.getMoney() - money);
			recv_bank.setMoney(recv_bank.getMoney() + money);
 
			String sql = "update t_bank set money = ? where id = ? ";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setFloat(1, bank.getMoney());
			ps.setInt(2, bank.getId());

			// 执行SQL更新操作
			int result = ps.executeUpdate();
			ps.setFloat(1, recv_bank.getMoney());
			ps.setInt(2, recv_bank.getId());
			result = ps.executeUpdate();
			connection.commit();
			// 3.根据支付成功 返回支付状态码
			if (result > 0) {
				resultType = 20;
			} else {
				resultType = 30;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.pay(Bank, Bank, float)");
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.BankDaoImpl.pay(Bank, Bank, float)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.pay(Bank, Bank, float)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.BankDaoImpl.pay(Bank, Bank, float)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return resultType;
	}
}
