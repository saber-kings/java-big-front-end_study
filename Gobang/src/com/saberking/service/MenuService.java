package com.saberking.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.MenuDao;
import com.saberking.pojo.Menu;

/**
 * 管理员菜单权限服务层相关操作实现类
 * 
 * @author luanz
 *
 */
public class MenuService {
	private MenuDao menuDao = new MenuDao();

	public String getById(String id) {
		List<Menu> tmenus = menuDao.getById(id);
		return JSON.toJSON(tmenus).toString();
	}

}
