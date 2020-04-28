package com.saberking.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.saberking.model.UserOL;

public class Singleton {

	// 单例模式
	private Singleton() {
		this.groups = new HashMap<>();
		this.groups.put("1", new Vector<UserOL>());
		this.groups.put("2", new Vector<UserOL>());
		this.groups.put("3", new Vector<UserOL>());
		this.groups.put("4", new Vector<UserOL>());
		this.groups.put("5", new Vector<UserOL>());
	}

	private static Singleton singleton;

	public synchronized static Singleton getSingleton() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

	private Map<String, Vector<UserOL>> groups;
	
	public synchronized Boolean isExisted(String uid, String gid) {
		Vector<UserOL> users = this.groups.get(gid);
		return users.stream().anyMatch(u -> u.getUserId().equals(uid));
	}

	public synchronized void saveUser(String gid, UserOL userOL) {
		Vector<UserOL> users = this.groups.get(gid);
		System.out.println(users.size());
		if (!isExisted(userOL.getUserId(), gid)) {
			users.add(userOL);
		}
	}
	
	public synchronized void groupSend(String uid, String msg, String gid) {
		Vector<UserOL> users = this.groups.get(gid);
		users.forEach(u -> {
			try {
				u.getSession().getBasicRemote().sendText(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void removeUser(String uid, String gid) {
		Vector<UserOL> users = this.groups.get(gid);
		users.forEach(u -> {
			if (u.getUserId().equals(uid)) {
				users.remove(u);
			}
		});
	}
}
