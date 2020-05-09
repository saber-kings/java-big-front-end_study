package com.saberking.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.MenuDao;
import com.saberking.pojo.Menu;

public class MenuService {
	private MenuDao menuDao = new MenuDao();
	
	public String getByUid(String uid){
		List<Menu> tmenus = menuDao.getByUid(uid);
		return JSON.toJSON(tmenus).toString();
	}
	
}
