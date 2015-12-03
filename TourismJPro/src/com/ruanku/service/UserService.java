package com.ruanku.service;

import com.ruanku.dao.UserDao;
import com.ruanku.dao.impl.UserDaoImpl;
import com.ruanku.model.User;
import com.ruanku.model.UserBo;
import com.ruanku.utils.AppException;

public class UserService {
	// 准备userDao
	private UserDao userDao = new UserDaoImpl();

	/*
	 * 注册用户
	 */
	public boolean register(User user) throws AppException {
		boolean flag = false;
		// 1.判断是否存在此用户
		try {
			if (userDao.isExist(user.getName())) {
				// 如果存在
				flag = false;
			} else {
				// 如果不存在
				flag = userDao.add(user);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.service.UserService.register(User)");
		}
		return flag;
	}

	public UserBo login(String username, String password) throws AppException {
		UserBo user = null;
		try {
			user = userDao.findUserByNameAndPwd(username, password);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"com.ruanku.service.UserService.login(String, String)");
		}

		return user;

	}
}
