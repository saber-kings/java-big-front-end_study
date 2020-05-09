package com.saberking.service;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.UserDao;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.User;

/**
 * 用户服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	private SignService signService = new SignService();

	public String mLogin(String phone, String upwd) {
		User user = userDao.mLogin(phone, upwd);
		if (user == null) {
			return "{\"msg\":\"error\"}";
		} else {
			Singleton singleton = Singleton.getSingleton();
			Boolean b = singleton.isNotOnline(user.getIdString(), false);
			if (b) {
				return JSON.toJSON(user).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	public String getPwd(String uid) {
		User user = userDao.getPwdById(uid);
		if (user != null) {
			return JSON.toJSON(user).toString();
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String getById(String id) {
		User user = userDao.getById(id);
		if (user != null) {
			return JSON.toJSON(user).toString();
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String changePwd(String id, String newPwd) {
		Boolean b = userDao.changePwd(id, newPwd);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String register(User user) {
		Boolean b = userDao.add(user);
		Boolean s = signService.register(user.getIdString());
		if (b&&s) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String update(User user) {
		Boolean b = userDao.updateUser(user);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

}
