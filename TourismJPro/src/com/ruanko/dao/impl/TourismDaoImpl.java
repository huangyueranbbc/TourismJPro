package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruanko.dao.TourismDao;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismImage;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

public class TourismDaoImpl implements TourismDao {

	/**
	 * 添加旅游基本信息
	 * 
	 * @param tourism
	 * @return
	 * @throws AppException
	 */
	public int add(Tourism tourism) throws AppException {
		int identityId = -1;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取用户
			// 声明SQL操作语句 插入用户记录
			String sql = "insert into t_tourism(title,type,city,days,basePrice,minNumber,maxNumber,description) values(?,?,?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tourism.getTitle());
			ps.setInt(2, tourism.getType());
			ps.setString(3, tourism.getCity());
			ps.setInt(4, tourism.getDays());
			ps.setInt(5, tourism.getBasePrice());
			ps.setInt(6, tourism.getMinNumber());
			ps.setInt(7, tourism.getMaxNumber());
			ps.setString(8, tourism.getDescription());
			// 执行更新操作
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			connection.commit();
			// 3.根据查询记录，判断是否注册成功
			// 注册成功
			if (rs.next()) {
				identityId = rs.getInt(1);
			} else {
				identityId = -1;
			}
			// 注册失败
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.add(Tourism)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.add(Tourism)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.add(Tourism)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.add(Tourism)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return identityId;
	}

	/**
	 * 添加旅游图片
	 * 
	 * @param tourismImage
	 * @return
	 * @throws AppException
	 */
	public boolean add(TourismImage tourismImage) throws AppException {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			// 1.创建连接
			connection = DBUtil.getConnection();

			// 2.存取用户
			// 声明SQL操作语句 插入用户记录
			String sql = "insert into t_tourismimages(tourism_id,big,small,upload_time,last_modify,orders) values(?,?,?,?,?,?)";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismImage.getTourism_id());
			ps.setString(2, tourismImage.getBig());
			ps.setString(3, tourismImage.getSmall());
			ps.setTimestamp(4, tourismImage.getUpload_time());
			ps.setDate(5, new java.sql.Date(tourismImage.getLast_modify()
					.getTime()));
			ps.setInt(6, tourismImage.getOrders());
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
					"com.ruanku.dao.impl.TourismDaoImpl.add(TourismImage)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.add(TourismImage)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.add(TourismImage)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.add(TourismImage)");
				}
			}
			DBUtil.closeConnection(connection);
		}

		return flag;
	}

	/**
	 * 总记录数
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
			String sql = "select id from t_tourism";
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
					"com.ruanku.dao.impl.TourismDaoImpl.getCount()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getCount()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCount()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCount()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 根据每页开始记录和每页的记录的长度 查询出每页的记录
	 * 
	 * @param page当前页
	 * @param size每页的记录长度
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> getList(int page, int size) throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getList(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getList(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getList(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getList(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

	/**
	 * 根据ID返回指定一条旅游基本信息
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	public Tourism getTourism(int tourismId) throws AppException {
		Tourism tourism = new Tourism();// 存储返回的指定旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism where id =?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			if (result.next()) {
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getTourism(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getTourism(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getTourism(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getTourism(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourism;
	}

	/**
	 * 根据旅游类型返回分页数据
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param type旅游类型
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> getListByType(int page, int size, int type)
			throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism where type = ? limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setInt(2, (page - 1) * 10);
			ps.setInt(3, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListByType(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListByType(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListByType(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListByType(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

	/**
	 * 根据价钱对旅游信息进行排序
	 * 
	 * @param page当前页
	 * @param size每页数据量
	 * @param order排序方式
	 *            0升序 1降序
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> getListOrderByPrice(int page, int size, int order)
			throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = null;
			if (order == 1) {
				sql = "select * from t_tourism order by basePrice desc limit ?,?";
			} else if (order == 0) {
				sql = "select * from t_tourism order by basePrice asc limit ?,?";
			}
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 10);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListOrderByPrice(int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListOrderByPrice(int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListOrderByPrice(int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListOrderByPrice(int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

	/**
	 * 根据价格范围返回旅游信息数目
	 * 
	 * @param startprice最低价格
	 * @param endprice最高价格
	 * @return旅游信息数目
	 * @throws AppException
	 */
	public int getCountByScope(int startprice, int endprice)
			throws AppException {
		int pageCount;// 旅游信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select id from t_tourism where basePrice between ? and ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, startprice);
			ps.setInt(2, endprice);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			result.last();
			pageCount = result.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getCountByScope(int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getCountByScope(int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCountByScope(int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCountByScope(int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 根据价格范围返回旅游分页信息
	 * 
	 * @param page
	 * @param size
	 * @param startprice
	 * @param endprice
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> getListByPriceScope(int page, int size,
			int startprice, int endprice) throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism where basePrice between ? and ? limit ?,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, startprice);
			ps.setInt(2, endprice);
			ps.setInt(3, (page - 1) * 10);
			ps.setInt(4, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListByPriceScope(int, int, int, int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getListByPriceScope(int, int, int, int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListByPriceScope(int, int, int, int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getListByPriceScope(int, int, int, int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

	/**
	 * 根据旅游类型返回旅游信息数目
	 * 
	 * @param type
	 * @return
	 * @throws AppException
	 */
	public int getCountByType(int type) throws AppException {
		int pageCount;// 旅游信息总记录数
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select * from t_tourism where type = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, type);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			result.last();
			pageCount = result.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getCountByType(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismDaoImpl.getCountByType(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCountByType(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismDaoImpl.getCountByType(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return pageCount;
	}

	/**
	 * 判断用户是否具有出游资格
	 * 
	 * @param tourismid
	 * @param tourismTime
	 * @return
	 * @throws AppException
	 */
	public boolean isTourism(int tourismid, Date tourismTime)
			throws AppException {
		boolean flag;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出所有的旅游记录
			String sql = "select days from t_tourism where id = ?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			// 执行SQL查询操作
			ps.setInt(1, tourismid);
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 通过 当前时间>(出游时间+出游天数) 判断是否已经出游

			if (result.next()) {
				int days = result.getInt(1);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				// 出游回归时间
				String tourismEndDate = df.format(new Date(tourismTime
						.getTime() - days * 24 * 60 * 60 * 1000));
				Date nowdDate = new Date();
				// 如果出游回归时间比当前时间早 则已经出游回归
				System.out.println(tourismEndDate + ":"
						+ df.parse(tourismEndDate).before(nowdDate));
				if (df.parse(tourismEndDate).before(nowdDate)) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.isTourism(int, Date)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.isTourism(int, Date)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.isTourism(int, Date)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.isTourism(int, Date)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return flag;
	}

	/**
	 * 查询所有旅游信息记录
	 * 
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> getList() throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism where del = 0";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.getList()");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.getList()");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.getList()");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.getList()");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

	/**
	 * 根据ID排序返回指定长度的旅游信息记录
	 * 
	 * @param size
	 * @param type旅游类型
	 * @return
	 * @throws AppException
	 */
	public List<Tourism> findTourismListOrderById(int size, int type)
			throws AppException {
		List<Tourism> tourisms = new ArrayList<Tourism>();// 返回分页模型的旅游信息数据
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourism where type = ? and del = 0 order by id desc limit 0,?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setInt(2, size);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				Tourism tourism = new Tourism();
				tourism.setID(result.getInt(1));
				tourism.setTitle(result.getString(2));
				tourism.setType(result.getInt(3));
				tourism.setCity(result.getString(4));
				tourism.setDays(result.getInt(5));
				tourism.setBasePrice(result.getInt(6));
				tourism.setMinNumber(result.getInt(7));
				tourism.setMaxNumber(result.getInt(8));
				tourism.setDescription(result.getString(9));
				tourism.setDel(result.getInt(10));
				tourisms.add(tourism);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.findTourismListOrderById(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanko.dao.impl.TourismDaoImpl.findTourismListOrderById(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.findTourismListOrderById(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanko.dao.impl.TourismDaoImpl.findTourismListOrderById(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourisms;
	}

}
