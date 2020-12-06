package com.dcits.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dcits.dao.JdbcTemplate;
import com.dcits.dao.UserDao;
import com.dcits.pojo.User;

/**
 * 用户 Dao 层实现类
 */
public class UserDaoImpl extends JdbcTemplate implements UserDao {

	@Override
	public User findUser(User user) {
		//封装查询条件，用List集合存储
		List<Object> params = new ArrayList<Object>();
		//拼接SQL语句
		StringBuilder sql = new StringBuilder("select * from t_user where 1=1");
		//动态添加查询参数，若没有就不查询
		if (StringUtils.isNotBlank(user.getUsername())) {
			params.add(user.getUsername());
			sql.append(" and username=?");
		} 
		if (StringUtils.isNotBlank(user.getPassword())) {
			params.add(user.getPassword());
			sql.append(" and password=?");
		}
		//使用 JDBC 模板类的查询方法，注意最后的可变参数必须以数组的形式传递，所以将List集合转成数组
		return executeQuery(sql.toString(), new BeanHandler<User>(User.class), params.toArray());
	}

	@Override
	public int updateSatus(User user) {
		//编写修改的SQL语句
		String sql = "update t_user set loginStatus = ?, loginTime = ? where id = ?";
		//使用 JDBC 模板类的增删改通用方法，注意最后的可变参数，需要按照上方SQL语句中?占位符的顺序一一传递
		return executeUpdate(sql, user.getLoginStatus(), user.getLoginTime(), user.getId());
	}

}
