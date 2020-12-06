package com.dcits.dao;

import java.util.List;

import com.dcits.pojo.Menu;
import com.dcits.utils.BaseDao;

/**
 * 菜单Dao层数据库操作
 *
 */
public class MenuDao {

	public List<Menu> getByAid(String aid) {
		String sql = "SELECT * FROM t_menu WHERE id in(SELECT mid FROM t_role_menu WHERE rid in(SELECT rid FROM t_account_role WHERE aid=?))";
		return BaseDao.getList(sql, Menu.class, aid);
	}

}
