package com.ruanko.dao;

import java.util.List;

import com.ruanko.model.User;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;

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

	/**
	 * 通过用户ID修改用户密码和姓名
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @return
	 * @throws AppException
	 */
	UserBo updateUserNameAndPwdById(int userId, String name, String password)
			throws AppException;

	/**
	 * 返回非管理员用户列表总数
	 * 
	 * @return
	 * @throws AppException
	 */
	int getCount() throws AppException;

	/**
	 * 返回非管理员的用户列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	List<User> getList(int page, int size) throws AppException;

	/**
	 * 根据用户ID更改用户状态
	 * 
	 * @param userId
	 * @param userStatus
	 * @return
	 * @throws AppException
	 */
	boolean updateUserStatusById(int userId, int userStatus)
			throws AppException;
}
