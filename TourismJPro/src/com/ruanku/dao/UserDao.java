package com.ruanku.dao;

import com.ruanku.model.User;
import com.ruanku.model.UserBo;
import com.ruanku.utils.AppException;

public interface UserDao {

	/**
	 * 判断是否存在此用户 若存在返回true
	 * 
	 * @param username
	 * @return
	 * @throws AppException
	 */
	boolean isExist(String username) throws AppException;

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	boolean add(User user) throws AppException;

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws AppException
	 */
	UserBo findUserByNameAndPwd(String username, String password)
			throws AppException;
}
