package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Menu;
import com.saberking.utils.BasePcAdminDao;

/**
 * 菜单权限数据访问层实现类
 * 
 * @author luanz
 *
 */
public class MenuDao {

	public List<Menu> getById(String aid) {
		String sql = "SELECT * FROM t_menu WHERE id IN(SELECT mid FROM t_role_menu WHERE rid IN(SELECT rid FROM t_admin_role WHERE aid=?))";
		return BasePcAdminDao.getAll(sql, Menu.class, aid);
	}

}
