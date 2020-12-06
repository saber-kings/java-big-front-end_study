package com.dcits.service;

import com.alibaba.fastjson.JSON;
import com.dcits.mydatastructure.Singleton;
import com.dcits.pojo.PersonInfo;

/**
 * 个人信息相关业务操作
 *
 */
public class PersonInfoService {
	/**
	 * 查询账号所属用户信息
	 * @param aid
	 * @return
	 */
	public String getByAid(String aid) {
		Singleton singleton = Singleton.getSingleton();
		PersonInfo info = singleton.getByAid(aid);
		return JSON.toJSON(info).toString();
	}
}
