package com.saberking.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.saberking.mydatastructure.Singleton;
import com.saberking.utils.WebsocketUtil;

/**
 * 管理员WebSocket连接相关操作类
 * 
 * @author luanz
 *
 */
@ServerEndpoint("/adminConn/{id}")
public class AdminWebSocket {

	/**
	 * 连接创建成功时调用
	 * 
	 * @param session 会话
	 * @param id      用户ID
	 */
	@OnOpen
	public void open(Session session, @PathParam(value = "id") String id) {
		System.out.println("管理员：" + id + "已进入后台");
		if (id != null) {
			Singleton singleton = Singleton.getSingleton();
			singleton.saveUser(id, true, session);
		}
	}

	/**
	 * 连接关闭时调用
	 * 
	 * @param id 用户ID
	 */
	@OnClose
	public void close(@PathParam(value = "id") String id) {
		Singleton singleton = Singleton.getSingleton();
		singleton.removeUser(id, true);
	}

	/**
	 * 连接出错时调用
	 * 
	 * @param session 会话
	 * @param error   错误
	 * @param id      用户ID
	 */
	@OnError
	public void error(Session session, Throwable error, @PathParam(value = "id") String id) {
		System.out.println(
				"用户：" + id + "，连接出错：" + error.getLocalizedMessage() + "，地址：" + WebsocketUtil.getRemoteAddress(session));
		Singleton singleton = Singleton.getSingleton();
		singleton.removeUser(id, true);
	}

	/**
	 * 当连接过程中接收到消息时调用
	 * 
	 * @param session 会话
	 * @param id      用户ID
	 * @param message 消息
	 */
	@OnMessage
	public void message(Session session, @PathParam(value = "id") String id, String message) {
		Singleton singleton = Singleton.getSingleton();
		boolean isAdmin = singleton.isNotOnline(id, true);
		try {
			if (isAdmin) {
				singleton.groupSend(message);
			} else {
				Map<String, String> map = new HashMap<>(5);
				map.put("name", "系统");
				map.put("msg", "你不是管理员不能发布公告");
				session.getBasicRemote().sendText(JSON.toJSON(map).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("发布公告失败");
		}
	}

}
