package com.dcits.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.alibaba.fastjson.JSON;
import com.dcits.dao.AccountDao;
import com.dcits.dao.PersonInfoDao;
import com.dcits.mydatastructure.Singleton;
import com.dcits.pojo.Account;
import com.dcits.pojo.PersonInfo;
import com.dcits.utils.BaseDao;

/**
 * 账号Service层业务处理
 *
 */
public class AccountService {
	private AccountDao accountDao = new AccountDao();

	private PersonInfoDao infoDao = new PersonInfoDao();

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(String username, String password) {
		Account account = accountDao.login(username, password);
		if (account == null) {
			return "{\"msg\":\"error\"}";
		} else {
			Singleton singleton = Singleton.getSingleton();
			// 判断当前用户是否不在线，使用单例+WebScoket实现的单点登录
			Boolean b = singleton.isNotOnline(String.valueOf(account.getId()));
			if (b) {
				return JSON.toJSON(account).toString();
			} else {
				return "{\"msg\":\"logined\"}";
			}
		}
	}

	/**
	 * 事务管理方式：向下传递Connection。有侵入性。使用DBUtils 业务层事务管理转账的方法
	 * 
	 * @param from
	 * @param to
	 * @param money
	 */
	public String transfer(String from, String to, BigDecimal money) {
		// 调用dao层

		// DBUtils进行事务处理的原理，是在Service层获得连接，以保证事务处理过程中的Connection对象为同一个Connection。
		// 因为必须保证连接为同一个连接，所以在业务层获得连接，再将连接传递到持久层，代码具有侵入性。
		// DBUtils使用的方法
		Connection conn = null;
		DbUtils.loadDriver(BaseDao.connstr);
		try {
			// 获得连接
			conn = DriverManager.getConnection(BaseDao.dr, BaseDao.uid, BaseDao.pwd);
			// 设置事务不自动提交
			conn.setAutoCommit(false);
			// 调用持久层
			accountDao.outMoney(conn, from, money);

			// 如果有异常
			// int a = 1 / 0 ;
			accountDao.inMoney(conn, to, money);
			// 提交事务,并安静的关闭连接
			DbUtils.commitAndCloseQuietly(conn);
			return "{\"msg\":\"success\"}";
		} catch (SQLException e) {
			// 有异常出现时，回滚事务，并安静的关闭连接
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			return "{\"msg\":\"error\"}";
		}
	}
	
	/**
	 * 存取款功能修改余额
	 * @param id
	 * @param balance
	 * @return
	 */
	public String saveOrGetMoney(String id, BigDecimal balance) {
		Boolean b = accountDao.upBalance(id, balance);
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
		Account account = accountDao.loginedGetById(id);
		return JSON.toJSON(account).toString();
	}

	/**
	 * 修改密码
	 * @param id
	 * @param newPwd
	 * @return
	 */
	public String changePwd(String id, String newPwd) {
		Boolean b = accountDao.changePwd(id, newPwd);
		if (b) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	/**
	 * 修改个人信息
	 * @param info
	 * @return
	 */
	public String upInfo(PersonInfo info) {
		Account account = new Account();
		account.setRealName(info.getRealName());
		account.setId(info.getAid());
		accountDao.update(account);
		Boolean p = infoDao.update(info);
		if (p) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	/**
	 * 获取所有通知
	 * @param id
	 * @return
	 */
	public String getNotices(String id) {
		Singleton singleton = Singleton.getSingleton();
		List<String> messages = singleton.getMessages(id);
		if (messages != null) {
			return JSON.toJSON(messages).toString();
		} else {
			return null;
		}
	}

	/**
	 * 收到通知
	 * @param id
	 * @return
	 */
	public String receivedNotices(String id) {
		Singleton singleton = Singleton.getSingleton();
		boolean res = singleton.receive(id);
		if (res) {
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}
}
