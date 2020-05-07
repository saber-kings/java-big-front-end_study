package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Section;
import com.saberking.utils.BaseDao;

public class SectionDao {
	public List<Section> getAll() {
		String sql = "select * from t_section";
		return BaseDao.getall(sql, Section.class, (Object[]) null);
	}

	public List<Section> getById(String id) {
		String sql = "select * from t_section where id=?";
		return BaseDao.getall(sql, Section.class, id );
	}
}
