package com.dcits.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dcits.dao.MenuDao;
import com.dcits.pojo.Menu;

/**
 * 菜单Service层业务处理
 *
 */
public class MenuService {
	private MenuDao menuDao = new MenuDao();
	
	public String getByAid(String aid){
		List<Menu> tmenus = menuDao.getByAid(aid);
		return JSON.toJSON(tmenus).toString();
	}
}
