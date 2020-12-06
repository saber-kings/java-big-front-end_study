package com.dcits.service;

import com.alibaba.fastjson.JSON;
import com.dcits.dao.PersonInfoDao;
import com.dcits.pojo.PersonInfo;

/**
 * 个人信息Service层业务处理
 *
 */
public class PersonInfoService {
	private PersonInfoDao personInfoDao = new PersonInfoDao();

	public String getByAid(String aid) {
		PersonInfo info = personInfoDao.getByAid(aid);
		return JSON.toJSON(info).toString();
	}
}
