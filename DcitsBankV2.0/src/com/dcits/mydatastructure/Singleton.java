package com.dcits.mydatastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.dcits.dao.AccountDao;
import com.dcits.pojo.Account;

/**
 * 单例模式：封装了在线用户列表，加上WebSocket可以实现通知发送的效果以及实现单点登录
 *
 */
public class Singleton {
	private AccountDao accountDao = new AccountDao();

	// 单例模式
	private Singleton() {
		// 初始化登陆的在线用户列表
		this.userOnline = new HashMap<>();

		// 获得所有用户的 id 集合
		List<String> userIds = accountDao.getAllIds();
		// 初始所有用户的消息队列
		this.groupMessages = new HashMap<>();
		if (userIds != null) {
			userIds.forEach(s -> {
				this.groupMessages.put(s, new ArrayList<String>());
			});
		}

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

	// 每个用户的消息队列
	private Map<String, List<String>> groupMessages;

	public synchronized void saveUser(String aid, Session session) {
		// 加入在线用户列表
		this.userOnline.put(aid, session);
		// 打印在线用户数量
//		System.out.println(this.userOnline.size());
	}

	public synchronized List<String> getMessages(String aid) {
		// 取得该用户的所有公告
		return this.groupMessages.get(aid);
	}

	public synchronized Boolean isNotOnline(String aid) {
		Session session = this.userOnline.get(aid);
		if (session == null) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void sendMsg(String aid, String msg) {
		Session session = this.userOnline.get(aid);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void groupSend(String id, String msg) {
		Account account = accountDao.loginedGetById(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", account.getRealName());
		map.put("msg", msg);
		String notice = JSON.toJSON(map).toString();
		this.userOnline.forEach((k, v) -> {
			try {
				v.getAsyncRemote().sendText(notice);
				this.groupMessages.forEach((uid, messages) -> {
					// 将公告加到用户的消息队列里
					messages.add(notice);
					// 如果用户未登录且消息队列的消息超过20条就清空
					if (isNotOnline(uid) && messages.size() > 20) {
						messages.clear();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void removeUser(String aid) {
		this.userOnline.remove(aid);
	}

	// 当用户退出时，或者点击收到消息就清空该用户消息队列
	public synchronized boolean receive(String aid) {
		List<String> messages = this.groupMessages.get(aid);
		if (messages != null) {
			messages.clear();
			return true;
		} else {
			return false;
		}
	}

}
