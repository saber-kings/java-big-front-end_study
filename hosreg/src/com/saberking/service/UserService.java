package com.saberking.service;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
import com.saberking.dao.UserDao;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.User;

public class UserService {
	private UserDao userDao = new UserDao();

	public String login(String un, String up) {
		User user = userDao.login(un, up);
		if (user == null) {
			return "{\"msg\":\"error\"}";
		} else {
			Singleton singleton = Singleton.getSingleton();
			Boolean b = singleton.isNotOnline(String.valueOf(user.getId()));
//			Boolean b = true;
			if (b) {
//			System.out.println("=================方式一："+JSON.toJSONString(u1));
//			System.out.println("=================方式二："+JSON.toJSON(u1).toString());
//			System.out.println("=================方式三："+JSON.toJSONString(u1, SerializerFeature.WriteMapNullValue));
				return JSON.toJSON(user).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	public String mLogin(String phone, String upwd) {
		User user = userDao.mLogin(phone, upwd);
		if (user == null) {
			return "{\"msg\":\"error\"}";
		} else {
			Singleton singleton = Singleton.getSingleton();
			Boolean b = singleton.isNotOnline(String.valueOf(user.getId()));
//			Boolean b = true;
			if (b) {
				return JSON.toJSON(user).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	public String getPwd(String uid) {
		User user = userDao.loginedGetById(uid);
		return JSON.toJSON(user).toString();
	}

	public String changePwd(String id, String newPwd) {
		Boolean b = userDao.changePwd(id, newPwd);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
}
