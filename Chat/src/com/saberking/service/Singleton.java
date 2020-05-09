package com.saberking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.saberking.model.Group;
import com.saberking.model.UserOL;

public class Singleton {

	// 单例模式
	private Singleton() {
		this.groups = new HashMap<>();
		String result = HttpRequest.get("http://192.168.31.139:9090/GroupChat/getAllGroups.php").body();
		List<Group> all = JSON.parseArray(result, Group.class);
		all.forEach(g -> {
			this.groups.put(g.getIdString(), new Vector<UserOL>());
		});
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
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid", uid);
		map.put("msg", msg);
		users.forEach(u -> {
			try {
				u.getSession().getBasicRemote().sendText(JSON.toJSON(map).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void removeUser(String uid, String gid) {
		Vector<UserOL> users = this.groups.get(gid);
		for (int i = 0; i < users.size(); i++) {
			UserOL u = users.get(i);
			if (u.getUserId().equals(uid)) {
				users.remove(i);
			}
		}
//		users.forEach(u -> {
//			if (u.getUserId().equals(uid)) {
//				users.remove(u);
//			}
//		});
	}
}
