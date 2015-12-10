package com.ruanko.service;

import java.util.List;

import com.ruanko.dao.UserDao;
import com.ruanko.dao.impl.UserDaoImpl;
import com.ruanko.model.PageModel;
import com.ruanko.model.User;
import com.ruanko.model.UserBo;
import com.ruanko.utils.AppException;

public class UserService {
	// 准备userDao
	private UserDao userDao = new UserDaoImpl();

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 * @throws AppException
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
			throw new AppException("com.ruanku.service.UserService.register(User)");
		}
		return flag;
	}

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public UserBo login(String username, String password) throws AppException {
		UserBo user = null;
		try {
			user = userDao.findUserByNameAndPwd(username, password);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanku.service.UserService.login(String, String)");
		}

		return user;

	}

	/**
	 * 修改用户信息(密码和用户名)
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @throws AppException
	 */ 
	public UserBo updateUserById(int userId, String name, String password) throws AppException {
		UserBo userBo = userDao.updateUserNameAndPwdById(userId, name, password);
		return userBo;
	}

	/**
	 * 返回用户列表的分页信息
	 * @param page
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getList(int page, int size) throws AppException{
				// 根据记录总数求出 分页总数
				int count = userDao.getCount(); 
				PageModel pageModel = new PageModel(count, size, page);
				//查询分页后的用户数据
				List<User> userList = userDao.getList(pageModel.getPage(), pageModel.getSize());
				
				pageModel.setList(userList);
				//返回用户数据到显示层
				return pageModel;
	}

	/**
	 * 根据用户ID更改用户状态
	 * @param userId
	 * @param userStatus
	 * @return
	 * @throws AppException
	 */
	public boolean updateUserStatusById(int userId, int userStatus) throws AppException{
		boolean flag;
		flag=userDao.updateUserStatusById(userId,userStatus);
		return flag;
	}
}
