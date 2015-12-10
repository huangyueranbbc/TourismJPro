package com.ruanko.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库管理工具类
 * 
 * @author rkcoe
 * 
 */
public class DBUtil {

	private static String url = "jdbc:mysql://127.0.0.1:3306/tourismdb?useUnicode=true&amp;" + "characterEncoding=utf8";
	private static String user = "root";
	private static String password = "666666";

	/*
	 * 开启并返回数据库连接
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/*
	 * 关闭数据库连接
	 */
	public static void closeConnection(Connection connection) {
		try {
			if ((connection != null) & (!connection.isClosed())) {
				connection.setAutoCommit(true);
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection connection = getConnection();
		System.out.println(connection);
		closeConnection(connection);
	}
}
