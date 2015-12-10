package com.ruanko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.dao.TourismImagesDao;
import com.ruanko.model.Tourism;
import com.ruanko.model.TourismImage;
import com.ruanko.model.TourismRule;
import com.ruanko.utils.AppException;
import com.ruanko.utils.DBUtil;

/**
 * 旅游图片信息DAO实现
 * 
 * @author rkcoe
 * 
 */
public class TourismImagesDaoImpl implements TourismImagesDao {

	/**
	 * 根据ID返回指定的旅游图片信息
	 * 
	 * @param tourismId
	 * @return
	 * @throws AppException
	 */
	public List<TourismImage> getTourismImages(int tourismId)
			throws AppException {
		List<TourismImage> tourismImages = new ArrayList<TourismImage>();// 存储返回的指定旅游图片信息
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// 1.创建数据库连接
			connection = DBUtil.getConnection();
			// 2.声明SQL查询语句 查询出分页后的旅游记录
			String sql = "select * from t_tourismimages where tourism_id =?";
			// 预编译SQL语句
			ps = connection.prepareStatement(sql);
			ps.setInt(1, tourismId);
			// 执行SQL查询操作
			ResultSet result = ps.executeQuery();
			connection.commit();
			// 将查询的记录放入List中返回
			while (result.next()) {
				TourismImage tourismImage = new TourismImage();
				tourismImage.setID(result.getInt(1));
				tourismImage.setTourism_id(result.getInt(2));
				tourismImage.setBig(result.getString(3));
				tourismImage.setSmall(result.getString(4));
				tourismImage.setUpload_time(result.getTimestamp(5));
				tourismImage.setLast_modify(result.getDate(6));
				tourismImage.setOrders(result.getInt(7));
				tourismImage.setDel(result.getInt(8));
				tourismImages.add(tourismImage);
			}  

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismImagesDaoImpl.getTourismImage(int)");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.dao.impl.TourismImagesDaoImpl.getTourismImage(int)");
		} finally {
			// 关闭数据库资源
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismImagesDaoImpl.getTourismImage(int)");
				} catch (Exception e) {
					e.printStackTrace();
					throw new AppException(
							"com.ruanku.dao.impl.TourismImagesDaoImpl.getTourismImage(int)");
				}
			}
			DBUtil.closeConnection(connection);
		}
		return tourismImages;
	}

}
