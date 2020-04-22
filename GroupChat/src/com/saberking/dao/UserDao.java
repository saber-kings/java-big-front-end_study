package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.User;
import com.saberking.utils.DH;

public class UserDao {
	public User login(String un, String up) {
		String sql = "select * from t_user where uname=? and upwd=?";
		List<User> result = DH.getall(sql, User.class, un, up);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public User mLogin(String phone, String upwd) {
		String sql = "select * from t_user where phone=? and upwd=?";
		List<User> result = DH.getall(sql, User.class, phone, upwd);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public User loginedGetById(String uid) {
		String sql = "select * from t_user where id=?";
		List<User> result = DH.getall(sql, User.class, uid);
		return result.get(0);
	}

	public Boolean register(User user) {
		String sql = "insert into t_user(uname,phone,upwd,real_name) values(?,?,?,?)";
		int num = DH.update(sql, user.getUname(), user.getPhone(), user.getUpwd(), user.getRealName());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean changePwd(String uid, String newPwd) {
		String sql = "update t_user set upwd=? where id=?";
		int num = DH.update(sql, newPwd, uid);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}
}
