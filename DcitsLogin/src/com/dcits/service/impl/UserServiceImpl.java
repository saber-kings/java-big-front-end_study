package com.dcits.service.impl;

import java.util.Date;

import com.dcits.dao.UserDao;
import com.dcits.dao.impl.UserDaoImpl;
import com.dcits.pojo.User;
import com.dcits.service.UserService;

/**
 * 用户 Service 层实现类
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		//创建用户实体封装查询参数
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//调用查询用户方法
		User findUser = userDao.findUser(user);
		//若登陆成功，设置登录状态和时间
		if (findUser != null) {
			findUser.setLoginStatus(1);
			findUser.setLoginTime(new Date());
			//调用修改用户状态方法，修改用户登录状态和时间
			userDao.updateSatus(findUser);
			return findUser;
		} else {
			return null;
		}
	}

	@Override
	public boolean logout(User user) {
		//修改用户登录状态
		user.setLoginStatus(0);
		//调用修改用户状态方法，修改用户登录状态
		int res = userDao.updateSatus(user);
		if (res != 0) {
			return true;
		} else {
			return false;
		}
	}

}
