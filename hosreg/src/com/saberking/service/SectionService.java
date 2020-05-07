package com.saberking.service;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.SectionDao;

public class SectionService {
	private SectionDao sectionDao = new SectionDao();

	public String getAll() {
		return JSON.toJSON(sectionDao.getAll()).toString();
	}

	public String getById(String id) {
		return JSON.toJSON(sectionDao.getById(id)).toString();
	}
}
