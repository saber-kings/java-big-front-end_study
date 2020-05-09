package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Workshop;
import com.saberking.utils.BaseDao;

public class WorkshopDao {
	public List<Workshop> getAll() {
		String sql = "select * from t_workshop";
		return BaseDao.getall(sql, Workshop.class, (Object[]) null);
	}

	public List<Workshop> getById(String id) {
		String sql = "select * from t_workshop where id=?";
		return BaseDao.getall(sql, Workshop.class, id );
	}
}
