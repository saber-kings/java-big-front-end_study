package com.dcits.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

import com.dcits.pojo.PersonInfo;
import com.dcits.utils.BaseDao;

/**
 * 个人信息Dao层数据库操作
 *
 */
public class PersonInfoDao {

	public PersonInfo getByAid(String aid) {
		String sql = "select * from t_personinfo where aid=?";
		List<PersonInfo> result = BaseDao.getList(sql, PersonInfo.class, aid);
		return result.get(0);
	}

	/**
	 * 动态修改信息表信息
	 * 
	 * @param personInfo
	 * @return
	 */
	public Boolean update(PersonInfo personInfo) {
		// 接收更新的数据条数
		int num = 0;
		// 这个用于存储查询的条件参数的
		List<Object> params = new ArrayList<>();
		if (personInfo.getId() != 0) {
			StringJoiner sqlPre = new StringJoiner(",", "update t_personinfo set", ",");
			if (StringUtils.isNotBlank(personInfo.getRealName())) {
				sqlPre.add(" real_name = ?");
				params.add(personInfo.getRealName());
			}
			if (personInfo.getAge() >= 0 && personInfo.getAge() <= 150) {
				sqlPre.add(" age = ?");
				params.add(personInfo.getAge());
			}
			if (StringUtils.isNotBlank(personInfo.getSex())) {
				sqlPre.add(" sex = ?");
				params.add(personInfo.getSex());
			}
			if (StringUtils.isNotBlank(personInfo.getAddress())) {
				sqlPre.add(" address = ?");
				params.add(personInfo.getAddress());
			}
			if (StringUtils.isNotBlank(personInfo.getPhone())) {
				sqlPre.add(" phone = ?");
				params.add(personInfo.getPhone());
			}
			StringBuilder sql = new StringBuilder(sqlPre.toString());
			sql.deleteCharAt(sqlPre.length() - 1);
			params.add(personInfo.getId());
			sql.append(" where id = ?");
			if (params.size() > 1) {
				num = BaseDao.update(sql.toString(), params.toArray());
			}
		}
		return num > 0 ? true : false;
	}

}
