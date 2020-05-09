package com.saberking.service;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.AdminDao;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.Admin;

/**
 * 管理员服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class AdminService {
	private AdminDao adminDao = new AdminDao();

	public String login(String un, String up) {
		Admin admin = adminDao.login(un, up);
		if (admin == null) {
			return "{\"msg\":\"error\"}";
		} else {
			Singleton singleton = Singleton.getSingleton();
			Boolean b = singleton.isNotOnline(admin.getIdString(), true);
			if (b) {
				return JSON.toJSON(admin).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	public String getPwd(String id) {
		Admin admin = adminDao.getById(id);
		return JSON.toJSON(admin).toString();
	}

	public String getAdminById(String id) {
		Admin admin = adminDao.getById(id);
		return JSON.toJSON(admin).toString();
	}

	public String changePwd(String id, String newPwd) {
		Boolean b = adminDao.changePwd(id, newPwd);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
}
