package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.OrderDao;
import com.ruanko.model.Order;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismBo;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 订单DAO层接口
 * 
 * @author rkcoe
 * 
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 * @throws AppException
	 */
	public int add(Order order) throws AppException {
		int identityId = -1;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取订单
			// 声明SQL操作语句 插入订单记录
			String sql = "insert into t_order(tourism_id,user_id,orderId,price,tourismTime,number,status,amount,remark,createTime,del) values(?,?,?,?,?,?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getTourism_id());
			System.out.println("旅游ID" + order.getTourism_id());
			ps.setInt(2, order.getUser_id());
			System.out.println("用户ID:" + order.getUser_id());
			ps.setString(3, order.getOrderId());
			ps.setFloat(4, order.getPrice());
			ps.setDate(5, new java.sql.Date(order.getTourismTime().getTime()));
			ps.setInt(6, order.getNumber());
			ps.setInt(7, order.getStatus());
			ps.setFloat(8, order.getAmount());
			ps.setString(9, order.getRemark());
			ps.setTimestamp(10, order.getCreateTime());
			ps.setInt(11, order.getDel());
			// 执行更新操作
			int result = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			connection.commit();
			// 3.根据返回记录，判断是否添加成功
			// 添加成功
			if (rs.next()) {
				identityId = rs.getInt(1);
			} else {
				identityId = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.add(Order)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.add(Order)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.add(Order)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return identityId;
	}

	/**
	 * 根据用户ID查询用户订单的个数
	 * 
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
			String sql = "select id from t_order where user_id = ?";
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
					"com.ruanku.dao.impl.OrderDaoImpl.getCountByUserId(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.getCountByUserId(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getCountByUserId(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getCountByUserId(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 根据用户ID查询并返回用户自身订单信息
	 * 
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Order> getListByUserId(int userId, int page, int size)
			throws AppException {
		List<Order> orders = new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的订单信息
			String sql = "select * from t_order where user_id = ? limit ?,?";
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
				Order order = new Order();
				order.setID(result.getInt(1));
				order.setTourism_id(result.getInt(2));
				order.setUser_id(result.getInt(3));
				order.setOrderId(result.getString(4));
				order.setPrice(result.getInt(5));
				order.setTourismTime(result.getDate(6));
				order.setNumber(result.getInt(7));
				order.setStatus(result.getInt(8));
				order.setAmount(result.getFloat(9));
				order.setRemark(result.getString(10));
				order.setCreateTime(result.getTimestamp(11));
				order.setDel(result.getInt(12));
				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.getListByUserId(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.getListByUserId(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getListByUserId(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getListByUserId(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return orders;
	}

	/**
	 * 根据订单号返回订单详细数据
	 * 
	 * @param orderId
	 * @return
	 * @throws AppException
	 */
	public Order getOrderByOrderId(String orderId) throws AppException {
		Order order = new Order();// 存储返回指定的订单数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_order where orderId =?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setString(1, orderId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			if (result.next()) {
				order.setID(result.getInt(1));
				order.setTourism_id(result.getInt(2));
				order.setUser_id(result.getInt(3));
				order.setOrderId(result.getString(4));
				order.setPrice(result.getInt(5));
				order.setTourismTime(result.getDate(6));
				order.setNumber(result.getInt(7));
				order.setStatus(result.getInt(8));
				order.setAmount(result.getFloat(9));
				order.setRemark(result.getString(10));
				order.setCreateTime(result.getTimestamp(11));
				order.setDel(result.getInt(12));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.getOrderByOrderId(String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.OrderDaoImpl.getOrderByOrderId(String)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getOrderByOrderId(String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.OrderDaoImpl.getOrderByOrderId(String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return order;
	}

	/**
	 * 根据订单号更新订单状态
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 * @throws AppException
	 */
	public boolean updateStatusById(String orderId, int type)
			throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;
		UserBo userBo = new UserBo();
		System.out.println("orderid" + orderId);
		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL更新语句 更新用户信息
			String sql = "update t_order set status = ? where orderId = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setString(2, orderId);
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
					"com.ruanko.dao.impl.OrderDaoImpl.updateStatusById(String)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.updateStatusById(String)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.updateStatusById(String)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.updateStatusById(String)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}

	/**
	 * 返回所有订单总数
	 * 
	 * @return
	 * @throws AppException
	 */
	public int getCount() throws AppException {
		int pageCount;// 旅游信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select id from t_order";
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
					"com.ruanko.dao.impl.OrderDaoImpl.getCount()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getCount()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getCount()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getCount()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 返回所有用户的订单信息
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Order> getList(int page, int size) throws AppException {
		List<Order> orders = new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的订单信息
			String sql = "select * from t_order limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Order order = new Order();
				order.setID(result.getInt(1));
				order.setTourism_id(result.getInt(2));
				order.setUser_id(result.getInt(3));
				order.setOrderId(result.getString(4));
				order.setPrice(result.getInt(5));
				order.setTourismTime(result.getDate(6));
				order.setNumber(result.getInt(7));
				order.setStatus(result.getInt(8));
				order.setAmount(result.getFloat(9));
				order.setRemark(result.getString(10));
				order.setCreateTime(result.getTimestamp(11));
				order.setDel(result.getInt(12));
				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getList(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getList(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getList(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getList(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return orders;
	}

	/**
	 * 判断该用户是否下订单并受理通过
	 * 
	 * @param userId
	 * @param tourismid
	 * @throws AppException
	 */
	public List<TourismBo> isTourism(int userId, int tourismid)
			throws AppException {
		List<TourismBo> tourismBos = new ArrayList<TourismBo>();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出该用户已经受理通过的订单
			String sql = "select tourism_id,tourismTime from t_order where tourism_id = ? and user_id = ? and status = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismid);
			ps.setInt(2, userId);
			ps.setInt(3, 3);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			while (result.next()) {
				TourismBo tourismBo = new TourismBo();
				tourismBo.setTourismid(result.getInt(1));// 返回满足条件的所有旅游记录ID
				tourismBo.setTourismTime(result.getDate(2));// 返回满足条件的旅游新信息对应的出游时间
				tourismBos.add(tourismBo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.isTourism(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.isTourism(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.isTourism(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.isTourism(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourismBos;
	}

	/**
	 * 返回需要受理订单总数
	 * 
	 * @return
	 * @throws AppException
	 */
	public int getAcceptCount() throws AppException {
		int pageCount;// 旅游信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select id from t_order where status not in(3,4,5)";
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
					"com.ruanko.dao.impl.OrderDaoImpl.getAcceptCount()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getAcceptCount()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getAcceptCount()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getAcceptCount()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 返回需要受理订单的数据
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Order> getAcceptList(int page, int size) throws AppException {
		List<Order> orders = new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的订单信息
			String sql = "select * from t_order where status not in(3,4,5) limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Order order = new Order();
				order.setID(result.getInt(1));
				order.setTourism_id(result.getInt(2));
				order.setUser_id(result.getInt(3));
				order.setOrderId(result.getString(4));
				order.setPrice(result.getInt(5));
				order.setTourismTime(result.getDate(6));
				order.setNumber(result.getInt(7));
				order.setStatus(result.getInt(8));
				order.setAmount(result.getFloat(9));
				order.setRemark(result.getString(10));
				order.setCreateTime(result.getTimestamp(11));
				order.setDel(result.getInt(12));
				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getAcceptList(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getAcceptList(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getAcceptList(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getAcceptList(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return orders;
	}

	/**
	 * 根据旅游信息分组 按照订单人数排序 返回指定长度的旅游信息数据
	 * 
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Integer> findTourismIdGroupByTourismidOrderbyNum(int size)
			throws AppException {
		List<Integer> tourismIds = new ArrayList<Integer>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出热门旅游信息
			String sql = "SELECT tourism_id FROM t_order GROUP BY tourism_id ORDER BY number DESC LIMIT 0,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();

			// 将查询的记录放入List中返回
			while (result.next()) {
				tourismIds.add(result.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.findTourismIdGroupByTourismidOrderbyNum(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.findTourismIdGroupByTourismidOrderbyNum(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.findTourismIdGroupByTourismidOrderbyNum(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.findTourismIdGroupByTourismidOrderbyNum(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourismIds;
	}

	/**
	 * 根据销量进行排序
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public List<Integer> getListBySales(int page, int size) throws AppException {
		List<Integer> tourismIds = new ArrayList<Integer>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出热门旅游信息
			String sql = "SELECT tourism_id FROM t_order GROUP BY tourism_id ORDER BY number DESC";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();

			// 将查询的记录放入List中返回
			while (result.next()) {
				tourismIds.add(result.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getListBySales(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.OrderDaoImpl.getListBySales(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getListBySales(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.OrderDaoImpl.getListBySales(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourismIds;
	}
}
