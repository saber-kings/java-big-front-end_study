package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Admin;
import com.saberking.utils.BasePcAdminDao;

/**
 * 管理员数据访问层实现类
 * 
 * @author luanz
 *
 */
public class AdminDao {
	public Admin login(String un, String up) {
		String sql = "select * from t_admin where uname=? and upwd=?";
		List<Admin> result = BasePcAdminDao.getAll(sql, Admin.class, un, up);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Admin getById(String id) {
		String sql = "select * from t_admin where id=?";
		List<Admin> result = BasePcAdminDao.getAll(sql, Admin.class, id);
		return result.get(0);
	}

	public Boolean save(Admin admin) {
		String sql = "insert into t_admin(uname,phone,upwd,real_name) values(?,?,?,?)";
		int num = BasePcAdminDao.update(sql, admin.getUname(), admin.getPhone(), admin.getUpwd(), admin.getRealName());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean changePwd(String id, String newPwd) {
		String sql = "update t_admin set upwd=? where id=?";
		int num = BasePcAdminDao.update(sql, newPwd, id);
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}
}
