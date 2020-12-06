package com.dcits.service;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.dcits.mydatastructure.Singleton;
import com.dcits.pojo.Account;

/**
 * 账号相关业务操作
 * @author saber-kings
 *
 */
public class AccountService {
	private Singleton singleton = Singleton.getSingleton();

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(String username, String password) {
		Account account = singleton.login(username, password);
		if (account == null) {
			return "{\"msg\":\"error\"}";
		} else {
			// 查询当前用户是否在线
			Boolean b = singleton.isNotOnline(String.valueOf(account.getId()));
			if (b) {
				return JSON.toJSON(account).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	/**
	 * 事务管理方式：业务层事务管理转账的方法
	 * 
	 * @param from
	 * @param to
	 * @param money
	 */
	public String transfer(String from, String to, BigDecimal money) {
		// 减钱
		boolean isOut = singleton.outMoney(from, money);
		// 加钱
		boolean isIn = singleton.inMoney(to, money);
		if (isIn && isOut) {
			return "{\"msg\":\"success\"}";
		} 
		// 模拟事务
		// 如果减钱失败，加钱成功
		if (!isOut&&isIn) {
			// 将加的钱减去
			singleton.outMoney(to, money);
		}
		// 反之一样
		if (!isIn&&isOut) {
			// 将减的钱加上
			singleton.inMoney(from, money);
		}
		return "{\"msg\":\"error\"}";
	}

	/**
	 * 存取款
	 * @param id
	 * @param balance
	 * @return
	 */
	public String saveOrGetMoney(String id, BigDecimal balance) {
		boolean b = singleton.upBalance(id, balance);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	/**
	 * 登出
	 * @param aid
	 * @return
	 */
	public String logout(String aid) {
		Singleton singleton = Singleton.getSingleton();
		singleton.removeUser(aid);
		return "{\"msg\":\"success\"}";
	}

	/**
	 * 查询密码
	 * @param id
	 * @return
	 */
	public String getPwd(String id) {
		Account account = singleton.getById(id);
		return JSON.toJSON(account).toString();
	}

	/**
	 * 修改密码
	 * @param id
	 * @param newPwd
	 * @return
	 */
	public String changePwd(String id, String newPwd) {
		boolean b = singleton.changePwd(id, newPwd);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
}
