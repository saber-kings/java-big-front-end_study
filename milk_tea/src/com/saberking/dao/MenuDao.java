package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Menu;
import com.saberking.utils.DH;

public class MenuDao {

	public List<Menu> getByUid(String uid) {
		String sql = "SELECT * FROM t_menu WHERE id IN(SELECT mid FROM t_role_menu WHERE rid IN(SELECT rid FROM t_user_role WHERE uid=?))";
		return DH.getall(sql, Menu.class, uid);
	}

}
