package com.dcits.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dcits.mydatastructure.Singleton;
import com.dcits.pojo.Menu;

/**
 * 菜单相关业务操作
 *
 */
public class MenuService {
	/**
	 * 查询菜单
	 * @return
	 */
	public String getMenus(){
		Singleton singleton = Singleton.getSingleton();
		List<Menu> tmenus = singleton.getMenus();
		return JSON.toJSON(tmenus).toString();
	}
}
