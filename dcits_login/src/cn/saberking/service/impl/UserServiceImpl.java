package cn.saberking.service.impl;

import java.util.Date;

import cn.saberking.dao.UserDao;
import cn.saberking.dao.impl.UserDaoImpl;
import cn.saberking.pojo.User;
import cn.saberking.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User findUser = userDao.findUser(user);
		if (findUser != null) {
			findUser.setLoginStatus(1);
			findUser.setLoginTime(new Date());
			userDao.updateSatus(findUser);
			return findUser;
		} else {
			return null;
		}
	}

	@Override
	public boolean logout(User user) {
		user.setLoginStatus(0);
		int res = userDao.updateSatus(user);
		if (res != 0) {
			return true;
		} else {
			return false;
		}
	}

}
