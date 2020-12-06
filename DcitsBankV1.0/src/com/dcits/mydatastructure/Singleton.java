package com.dcits.mydatastructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import com.dcits.pojo.Account;
import com.dcits.pojo.Menu;
import com.dcits.pojo.PersonInfo;

/**
 * 单例模式：封装了在线用户列表，加上WebSocket可以实现通知发送的效果以及实现单点登录
 *
 */
public class Singleton {
	private static final List<Account> ACCOUNTS = new ArrayList<>();

	/**
	 * 模拟数据库中的账号表
	 */
	static {
		ACCOUNTS.add(new Account(1, "1016041135", "15810000001", "12345678", "程阳", new BigDecimal(1120000), 1));
		ACCOUNTS.add(new Account(2, "B12110931", "15678946532", "12345678", "李四", new BigDecimal(9090), 1));
		ACCOUNTS.add(new Account(3, "1016041136", "15432568745", "12345678", "王五", new BigDecimal(10000020000L), 1));
	}

	private static final List<PersonInfo> PERSON_INFOS = new ArrayList<>();

	/**
	 * 模拟数据库中的用户信息表
	 */
	static {
		PERSON_INFOS.add(new PersonInfo(1, 1, "程阳", 23, "男", 320542000000000001L, "南邮", "15810000001"));
		PERSON_INFOS.add(new PersonInfo(2, 2, "李四", 23, "男", 320542000000000002L, "湖北武汉", "15678946532"));
		PERSON_INFOS.add(new PersonInfo(3, 3, "王五", 22, "男", 320542000000000001L, "南邮", "15432568745"));
	}

	private static final List<Menu> MENUS = new ArrayList<>();

	/**
	 * 模拟数据库中的菜单表
	 */
	static {
		MENUS.add(new Menu(1, "修改密码", "views/xgmm.html"));
		MENUS.add(new Menu(2, "存取款功能", "views/atm.html"));
		MENUS.add(new Menu(3, "转账功能", "views/transer.html"));
	}

	// 单例模式
	private Singleton() {
		// 初始化登陆的在线用户列表
		this.userOnline = new HashMap<>();
	}

	private static Singleton singleton;

	public synchronized static Singleton getSingleton() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

	// 登陆的在线用户列表
	private Map<String, Session> userOnline;

	/**
	 * 模拟登陆查询
	 * @param username
	 * @param password
	 * @return
	 */
	public Account login(String username, String password) {
		for (Account account : ACCOUNTS) {
			if (username.equals(account.getAccount()) && password.endsWith(account.getPassword())) {
				return account;
			}
		}
		return null;
	}

	/**
	 * 模拟根据账号Id查询用户信息
	 * @param aid
	 * @return
	 */
	public PersonInfo getByAid(String aid) {
		try {
			for (PersonInfo personInfo : PERSON_INFOS) {
				if (Integer.parseInt(aid) == personInfo.getAid()) {
					return personInfo;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 模拟根据账号Id查询账号
	 * @param id
	 * @return
	 */
	public Account getById(String id) {
		try {
			for (Account account : ACCOUNTS) {
				if (Integer.parseInt(id) == account.getId()) {
					return account;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 模拟修改密码
	 * @param id
	 * @param newPwd
	 * @return
	 */
	public boolean changePwd(String id, String newPwd) {
		try {
			for (Account account : ACCOUNTS) {
				if (Integer.parseInt(id) == account.getId()) {
					account.setPassword(newPwd);
					return true;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 模拟转账加钱
	 * @param account
	 * @param money
	 * @return
	 */
	public boolean inMoney(String account, BigDecimal money) {
		for (Account dbAccount : ACCOUNTS) {
			if (account.equals(dbAccount.getAccount())) {
				dbAccount.setBalance(dbAccount.getBalance().add(money));;
				return true;
			}
		}
		return false;
	}

	/**
	 * 模拟转账出钱
	 * @param account
	 * @param money
	 * @return
	 */
	public boolean outMoney(String account, BigDecimal money) {
		for (Account dbAccount : ACCOUNTS) {
			if (account.equals(dbAccount.getAccount())) {
				dbAccount.setBalance(dbAccount.getBalance().subtract(money));
				return true;
			}
		}
		return false;
	}

	/**
	 * 模拟修改余额
	 * @param id
	 * @param money
	 * @return
	 */
	public boolean upBalance(String id, BigDecimal money) {
		try {
			for (Account dbAccount : ACCOUNTS) {
				if (Integer.parseInt(id) == dbAccount.getId()) {
					dbAccount.setBalance(money);
					return true;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 模拟查询菜单
	 * @return
	 */
	public List<Menu> getMenus() {
		return MENUS;
	}

	public synchronized void saveUser(String aid, Session session) {
		// 加入在线用户列表
		this.userOnline.put(aid, session);
		// 打印在线用户数量
//		System.out.println(this.userOnline.size());
	}

	/**
	 * 判断用户是否不在线，单点登陆用
	 * @param aid
	 * @return
	 */
	public synchronized Boolean isNotOnline(String aid) {
		Session session = this.userOnline.get(aid);
		if (session == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 从在线列表移除下线用户
	 * @param aid
	 */
	public synchronized void removeUser(String aid) {
		this.userOnline.remove(aid);
	}
}
