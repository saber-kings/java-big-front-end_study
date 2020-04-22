package com.saberking.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saberking.pojo.Custom;
import com.saberking.utils.DH;

public class CustomDao {

	public List<Custom> getAll() {
		String sql = "select * from t_custom";
		return DH.getall(sql, Custom.class, (Object[]) null);
	}

	public long save(Custom custom) {
		String sql = "insert into t_custom(cname,created,updated,phone,email) values(?,?,?,?,?)";
		long num = DH.insert(sql, custom.getCname(), custom.getCreated(), custom.getUpdated(), custom.getPhone(),
				custom.getEmail()).longValue();
//		System.out.println(num);
		if (num > 0L) {
			return num;
		} else {
			return 0L;
		}
	}

	public Custom getById(String cid) {
		String sql = "select * from t_custom where id=?";
		List<Custom> result = DH.getall(sql, Custom.class, cid);
		return result.get(0);
	}

	public List<Custom> dynamicQuery(Custom custom) {
		String sql = "select * from t_custom where 1=1";
		// 这个是用于存储查询的结果的
		List<Custom> result = new ArrayList<>();
		// 这个用于存储查询的条件参数的
		List<Object> params = new ArrayList<>();
		if (custom.getId() != 0) {
			sql = sql + " and id = ?";
			params.add(custom.getId());
		}
		if (StringUtils.isNotBlank(custom.getCname())) {
			sql = sql + " and cname like ?";
			params.add("%" + custom.getCname().trim() + "%");
		}
		if (StringUtils.isNotBlank(custom.getPhone())) {
			sql = sql + " and phone = ?";
			params.add(custom.getPhone().trim());
		}
		if (StringUtils.isNotBlank(custom.getEmail())) {
			sql = sql + " and email = ?";
			params.add(custom.getEmail().trim());
		}
		if (params.size() != 0) {
			for (Object param : params) {
				result = DH.getall(sql, Custom.class, param);
			}
		} else {
			result = DH.getall(sql, Custom.class, (Object[]) null);
		}
		return result;
	}

	public Boolean update(Custom custom) {
		String sql = "update t_custom set cname=?,updated=?,phone=?,email=? where id=?";
		int num = DH.update(sql, custom.getCname(), custom.getUpdated(), custom.getPhone(), custom.getEmail(),
				custom.getId());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean delete(String cid) {
		String sql = "delete from t_custom where id=?";
		int num = DH.update(sql, cid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

}
