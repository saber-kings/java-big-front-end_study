package com.saberking.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.saberking.model.UserOL;

@ServerEndpoint("/echo/{uid}/{gid}")
public class MyWebSocket {

	// 连接创建成功时
	@OnOpen
	public void open(Session session, @PathParam(value = "uid") String uid, @PathParam(value = "gid") String gid) {
		System.out.println(uid + "已连接上");
		UserOL userOL = new UserOL();
		userOL.setUserId(uid);
		userOL.setSession(session);
		Singleton singleton = Singleton.getSingleton();
		singleton.saveUser(gid, userOL);
	}

	// 连接关闭时
	@OnClose
	public void close(@PathParam(value = "uid") String uid, @PathParam(value = "gid") String gid) {
		System.out.println(uid + "已断开");
		Singleton singleton = Singleton.getSingleton();
		singleton.removeUser(uid, gid);
	}

	// 连接出错时
	@OnError
	public void error(Throwable error) {

	}

	// 当连接过程中接收到消息时
	@OnMessage
	public void message(Session session, @PathParam(value = "uid") String uid, @PathParam(value = "gid") String gid,
			String message) {
		Singleton singleton = Singleton.getSingleton();
		singleton.groupSend(uid, message, gid);
	}

}
