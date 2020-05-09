package com.saberking.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saberking.pojo.User;
import com.saberking.utils.BaseDao;

/**
 * 用户数据访问层实现类
 * 
 * @author luanz
 *
 */
public class UserDao {

	public User mLogin(String phone, String upwd) {
		String sql = "select * from t_user where phone=? and upwd=? and status = 1";
		List<User> result = BaseDao.getAll(sql, User.class, phone, upwd);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public User getById(String uid) {
		String sql = "select * from t_user where id=? and status = 1";
		List<User> result = BaseDao.getAll(sql, User.class, uid);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public User getPwdById(String uid) {
		String sql = "select upwd from t_user where id=?";
		List<User> result = BaseDao.getAll(sql, User.class, uid);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Boolean add(User user) {
		String sql = "insert into t_user(nick_name,phone,upwd,real_name) values(?,?,?,?)";
		int num = BaseDao.update(sql, user.getNickName(), user.getPhone(), user.getUpwd(), user.getRealName());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean changePwd(String uid, String newPwd) {
		String sql = "update t_user set upwd=? where id=? and status = 1";
		int num = BaseDao.update(sql, newPwd, uid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean updateUser(User user) {
		StringBuilder sql = new StringBuilder("update t_user set");
		// 这个用于存储动态修改的参数
		List<Object> params = new ArrayList<>();
		userParamNotBlank(user, sql, params);

		if (StringUtils.isNotBlank(user.getBio())) {
			sql.append(" bio = ?,");
			params.add(user.getBio());
		}

		userParamNonNullAndZero(user, sql, params);

		if (isNotNullAndOne(user.getStatus())) {
			sql.append(" status = ?,");
			params.add(user.getStatus());
		}

		if (user.isNewcomer()) {
			sql.append(" newcomer = ?,");
			params.add(user.isNewcomer());
		}

		int lastComma = sql.lastIndexOf(",");
		sql.deleteCharAt(lastComma);

		sql.append(" where id = ?");
		params.add(user.getId());

		int num = BaseDao.update(sql.toString(), params.toArray());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void userParamNonNullAndZero(User user, StringBuilder sql, List<Object> params) {
		if (isNotNullAndZero(user.getWinGames())) {
			sql.append(" win_games = ?,");
			params.add(user.getWinGames());
		}
		if (isNotNullAndZero(user.getTotalGames())) {
			sql.append(" total_games = ?,");
			params.add(user.getTotalGames());
		}
		if (isNotNullAndZero(user.getGold())) {
			sql.append(" gold = ?,");
			params.add(user.getGold());
		}
		if (isNotNullAndZero(user.getJewel())) {
			sql.append(" jewel = ?,");
			params.add(user.getJewel());
		}

		if (isNotNullAndZero(user.getRank())) {
			sql.append(" rank = ?,");
			params.add(user.getRank());
		}
	}

	private void userParamNotBlank(User user, StringBuilder sql, List<Object> params) {
		if (StringUtils.isNotBlank(user.getNickName())) {
			sql.append(" nick_name = ?,");
			params.add(user.getNickName());
		}
		if (StringUtils.isNotBlank(user.getPhone())) {
			sql.append(" phone = ?,");
			params.add(user.getPhone());
		}

		if (StringUtils.isNotBlank(user.getRealName())) {
			sql.append(" real_name = ?,");
			params.add(user.getRealName());
		}
		if (StringUtils.isNotBlank(user.getWeixin())) {
			sql.append(" weixin = ?,");
			params.add(user.getWeixin());
		}
		if (StringUtils.isNotBlank(user.getQq())) {
			sql.append(" qq = ?,");
			params.add(user.getQq());
		}
		if (StringUtils.isNotBlank(user.getAvatar())) {
			sql.append(" avatar = ?,");
			params.add(user.getAvatar());
		}
		if (StringUtils.isNotBlank(user.getRemarks())) {
			sql.append(" remarks = ?,");
			params.add(user.getRemarks());
		}
	}

	private boolean isNotNullAndZero(int num) {
		if ("".equals(String.valueOf(num)) || num <= 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isNotNullAndOne(int num) {
		if ("".equals(String.valueOf(num)) || num < 0 || num == 1) {
			return false;
		} else {
			return true;
		}
	}

	public List<String> getAllIds() {
		String sql = "select id from t_user";
		List<String> result = BaseDao.getAll(sql, String.class, (Object[]) null);
		return result;
	}
}
