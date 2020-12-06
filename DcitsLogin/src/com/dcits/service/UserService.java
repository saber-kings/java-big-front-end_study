package com.dcits.service;

import com.dcits.pojo.User;

/**
 * 用户 Service 层接口
 */
public interface UserService {
	/**
	 * 用户登录的方法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	User login(String username, String password);
	
	/**
	 * 用户登出的方法
	 * 
	 * @param 用户信息
	 * @return
	 */
	boolean logout(User user);
}
