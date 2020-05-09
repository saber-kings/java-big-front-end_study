package com.saberking.service;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.SignDao;
import com.saberking.pojo.Sign;

/**
 * 签到服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class SignService {
	private SignDao signDao = new SignDao();

	public String getByUid(String uid) {
		Sign sign = signDao.getByUid(uid);
		if (sign != null) {
			return JSON.toJSON(sign).toString();
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public Boolean register(String uid) {
		return signDao.add(uid);
	}

	public String update(Sign sign) {
		Boolean b = signDao.update(sign);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String deleteById(String id) {
		Boolean b = signDao.deleteById(id);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String deleteByUid(String uid) {
		Boolean b = signDao.deleteByUid(uid);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
}
