package com.saberking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

import com.saberking.pojo.Sign;
import com.saberking.utils.BaseDao;

/**
 * 签到数据访问层实现类
 * 
 * @author luanz
 *
 */
public class SignDao {

	public Sign getByUid(String uid) {
		String sql = "select * from t_sign where uid=(select id from t_user where id = ? and status = 1)";
		List<Sign> result = BaseDao.getAll(sql, Sign.class, uid);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Boolean add(String uid) {
		String sql = "insert into t_sign(uid) values(?)";
		int num = BaseDao.update(sql, uid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean update(Sign sign) {
		// 第一个为分隔符，第二个参数为字符串前缀，第三个参数为字符串后缀
		StringJoiner sql = new StringJoiner(", ", "update t_sign set ", " where id = ?");
		// 这个用于存储动态修改的参数
		List<Object> params = new ArrayList<>();
		if (isNotNullAndZero(sign.getTotalCount())) {
			sql.add("total_count = ?");
			params.add(sign.getTotalCount());
		}
		if (isNotNullAndZero(sign.getContCount())) {
			sql.add("cont_count = ?");
			params.add(sign.getContCount());
		}
		if (sign.getLastTime() != null) {
			sql.add("last_time = ?");
			params.add(sign.getLastTime());
		}

		if (StringUtils.isNotBlank(sign.getSignedTime())) {
			sql.add("signed_time = ?");
			params.add(sign.getSignedTime());
		}

		params.add(sign.getId());

		int num = BaseDao.update(sql.toString(), params.toArray());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNotNullAndZero(int num) {
		if ("".equals(String.valueOf(num)) || num <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean deleteById(String id) {
		String sql = "delete from t_sign where id = ?";
		int num = BaseDao.update(sql, id);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean deleteByUid(String uid) {
		String sql = "delete from t_sign where uid=(select id from t_user where id = ?)";
		int num = BaseDao.update(sql, uid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}
}
