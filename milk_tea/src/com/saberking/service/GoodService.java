package com.saberking.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.GoodDao;
import com.saberking.pojo.Good;

public class GoodService {
	private GoodDao goodDao = new GoodDao();
	
	public String listAll() {
		return JSON.toJSON(goodDao.getAll()).toString();
	}
	
	public String add(Good good) {
		Boolean res = goodDao.save(good);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String delete(String uid) {
		Boolean res = goodDao.delete(uid);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String update(Good good) {
		Boolean res = goodDao.update(good);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
	
	public String search(Good good) {
		List<Good> res = goodDao.dynamicQuery(good, 0.0, 0.0);
		return JSON.toJSON(res).toString();
	}
}
