package com.saberking.service;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.WorkshopDao;

public class WorkshopService {
	private WorkshopDao workshopDao = new WorkshopDao();

	public String getAll() {
		return JSON.toJSON(workshopDao.getAll()).toString();
	}

	public String getById(String id) {
		return JSON.toJSON(workshopDao.getById(id)).toString();
	}
}
