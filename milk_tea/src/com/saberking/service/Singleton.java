package com.saberking.service;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class Singleton {

	// 单例模式
	private Singleton() {
		this.usersMap = new HashMap<>();
	}

	private static Singleton singleton;

	public synchronized static Singleton getSingleton() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

	private Map<String, Session> usersMap;

	public synchronized void saveUser(String uid, Session session) {
		this.usersMap.put(uid, session);
	}

	public synchronized void removeUser(String uid) {
		this.usersMap.remove(uid);
	}

	public synchronized Boolean isUser(String uid) {
		Session session = this.usersMap.get(uid);
		if (session == null) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void sendMsg(String uid, String msg) {
		Session session = this.usersMap.get(uid);
		try {
			session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void groupSend(String msg) {
		this.usersMap.forEach((k, v) -> {
			try {
				v.getBasicRemote().sendText(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
