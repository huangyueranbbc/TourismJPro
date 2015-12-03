package com.ruanku.dao;

import com.ruanku.model.User;
import com.ruanku.utils.AppException;

public interface UserDao {

	/*
	 * 判断是否存在此用户 若存在返回true
	 */
	boolean isExist(String username) throws AppException;

	/*
	 * 保存用户
	 */
	boolean save(User user) throws AppException;
}
