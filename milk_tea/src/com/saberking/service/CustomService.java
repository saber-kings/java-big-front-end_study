package com.saberking.service;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.CustomDao;
import com.saberking.pojo.Custom;

public class CustomService {
	private CustomDao customDao = new CustomDao();
	
	public String listAll() {
		return JSON.toJSON(customDao.getAll()).toString();
	}
	
	public String add(Custom custom) {
		custom.setCreated(new Date());
		custom.setUpdated(new Date());
		Boolean res = customDao.save(custom);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String delete(String uid) {
		Boolean res = customDao.delete(uid);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String update(Custom custom) {
		custom.setUpdated(new Date());
		Boolean res = customDao.update(custom);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String search(Custom custom) {
		List<Custom> res = customDao.dynamicQuery(custom);
		return JSON.toJSON(res).toString();
	}
}
