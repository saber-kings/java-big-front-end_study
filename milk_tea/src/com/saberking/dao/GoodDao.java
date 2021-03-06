package com.saberking.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saberking.pojo.Good;
import com.saberking.utils.DH;

public class GoodDao {

	public List<Good> getAll() {
		String sql = "select * from t_good";
		return DH.getall(sql, Good.class, (Object[]) null);
	}

	public Boolean save(Good good) {
		String sql = "insert into t_good(g_name,g_price,g_stuff,g_desc) values(?,?,?,?)";
		int num = DH.update(sql, good.getGName(), good.getGPrice(), good.getGStuff(), good.getGDesc());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Good getById(String uid) {
		String sql = "select * from t_good where id=?";
		List<Good> result = DH.getall(sql, Good.class, uid);
		return result.get(0);
	}

	public List<Good> dynamicQuery(Good good, double maxPrice, double minPrice) {
		String sql = "select * from t_good where 1=1";
		// 这个是用于存储查询的结果的
		List<Good> result = null;
		// 这个用于存储查询的条件参数的
		List<Object> params = new ArrayList<>();
		if (good.getId() != 0) {
			sql = sql + " and id = ?";
			params.add(good.getId());
		}
		if (StringUtils.isNotBlank(good.getGName())) {
			sql = sql + " and g_name like ?";
			params.add("%" + good.getGName().trim() + "%");
		}
		// 最高价格
		if (maxPrice != 0.0) {
			sql = sql + " and g_price < ?";
			params.add(maxPrice);
		}
		// 最低价格
		if (minPrice != 0.0) {
			sql = sql + " and g_price > ?";
			params.add(minPrice);
		}
		if (StringUtils.isNotBlank(good.getGStuff())) {
			sql = sql + " and g_stuff like ?";
			params.add("%" + good.getGStuff().trim() + "%");
		}
		if (StringUtils.isNotBlank(good.getGDesc())) {
			sql = sql + " and g_desc like ?";
			params.add("%" + good.getGDesc().trim() + "%");
		}
		if (params.size()!=0) {
			result = DH.getall(sql, Good.class, params.toArray());
		} else {
			result = DH.getall(sql, Good.class, (Object[]) null);
		}
		return result;
	}

	public Boolean update(Good good) {
		String sql = "update t_good set g_name=?,g_price=?,g_stuff=?,g_desc=? where id=?";
		int num = DH.update(sql, good.getGName(), good.getGPrice(), good.getGStuff(), good.getGDesc(), good.getId());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean delete(String uid) {
		String sql = "delete from t_good where id=?";
		int num = DH.update(sql, uid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

}
