package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.TourismRuleDao;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismRule;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

public class TourismRuleDaoImpl implements TourismRuleDao {

	/**
	 * 添加旅游规则信息
	 */
	public boolean add(TourismRule tourismRule) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取用户
			// 声明SQL操作语句 插入用户记录
			String sql = "insert into t_tourismrule(tourism_id,deadline,tourismTime,price,discount,remark,modifyTime,status,del) values(?,?,?,?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismRule.getTourism_id());
			ps.setDate(2,
					new java.sql.Date(tourismRule.getDeadline().getTime()));
			ps.setDate(3, new java.sql.Date(tourismRule.getTourismTime()
					.getTime()));
			ps.setFloat(4, tourismRule.getPrice());
			ps.setString(5, tourismRule.getDiscount());
			ps.setString(6, tourismRule.getRemark());
			ps.setTimestamp(7, tourismRule.getModifyTime());
			ps.setInt(8, tourismRule.getStatus());
			ps.setInt(9, tourismRule.getDel());
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
					"com.ruanku.dao.impl.TourismRuleDaoImpl.add(TourismRule)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismRuleDaoImpl.add(TourismRule)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismRuleDaoImpl.add(TourismRule)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismRuleDaoImpl.add(TourismRule)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;

	}

	/**
	 * 根据旅游信息ID获取所有的旅游规则
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	public List<TourismRule> getListByTourismId(int tourismId)
			throws AppException {
		List<TourismRule> tourismRules = new ArrayList<TourismRule>();// 返回旅游规则数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出该旅游主题下所有的旅游规则
			String sql = "select * from t_tourismrule where tourism_id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			// 将查询的记录放入List中返回
			while (result.next()) {
				TourismRule tourismRule = new TourismRule();
				tourismRule.setID(result.getInt(1));
				tourismRule.setTourism_id(result.getInt(2));
				tourismRule.setDeadline(result.getDate(3));
				tourismRule.setTourismTime(result.getDate(4));
				tourismRule.setPrice(result.getFloat(5));
				tourismRule.setDiscount(result.getString(6));
				tourismRule.setStatus(result.getInt(7));
				tourismRule.setRemark(result.getString(8));
				tourismRule.setModifyTime(result.getTimestamp(9));
				tourismRule.setDel(result.getInt(10));
				tourismRules.add(tourismRule);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismRuleDaoImpl.getListByTourismId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismRuleDaoImpl.getListByTourismId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismRuleDaoImpl.getListByTourismId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismRuleDaoImpl.getListByTourismId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourismRules;
	}

	/**
	 * 根据旅游规则ID返回旅游规则数据
	 * 
	 * @param tourismtimeRuleId
	 * @return
	 * @throws AppException
	 */
	public TourismRule getTourismRule(int tourismtimeRuleId)
			throws AppException {
		TourismRule tourismRule = new TourismRule();// 存储返回的指定旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourismrule where ID = ? and del = 0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismtimeRuleId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			if (result.next()) {
				tourismRule.setID(result.getInt(1));
				tourismRule.setTourism_id(result.getInt(2));
				tourismRule.setDeadline(result.getDate(3));
				tourismRule.setTourismTime(result.getDate(4));
				tourismRule.setPrice(result.getFloat(5));
				tourismRule.setDiscount(result.getString(6));
				tourismRule.setStatus(result.getInt(7));
				tourismRule.setRemark(result.getString(8));
				tourismRule.setModifyTime(result.getTimestamp(9));
				tourismRule.setDel(result.getInt(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismRuleDaoImpl.getTourismRule(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismRuleDaoImpl.getTourismRule(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismRuleDaoImpl.getTourismRule(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismRuleDaoImpl.getTourismRule(int)");
				} 
			}
			DBUtil.closeConnection(connection);
		}
		return tourismRule;
	}
}
