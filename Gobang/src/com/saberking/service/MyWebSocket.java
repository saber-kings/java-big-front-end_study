package com.saberking.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.saberking.model.Room;
import com.saberking.mydatastructure.Singleton;
import com.saberking.utils.WebsocketUtil;

/**
 * 管理员客户端WebSocket连接相关操作类
 * 
 * @author luanz
 *
 */
@ServerEndpoint("/echo/{id}")
public class MyWebSocket {

	/**
	 * 连接创建成功时调用
	 * 
	 * @param session 会话
	 * @param id      用户ID
	 */
	@OnOpen
	public void open(Session session, @PathParam(value = "id") String id) {
		System.out.println("用户：" + id + "已进入游戏大厅");
		if (id != null) {
			Singleton singleton = Singleton.getSingleton();
			singleton.saveUser(id, false, session);
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
		singleton.removeUser(id, false);
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
	 * @param id      用户id
	 * @param message 消息
	 */
	@OnMessage
	public void message(Session session, @PathParam(value = "id") String id, String message) {
		String[] str = message.split(",");
		Room room = new Room();
		room.setRoomId(str[0]);
		room.setRoomName(str[1]);
		Singleton singleton = Singleton.getSingleton();
		singleton.createRoom(str[0], room);
	}

}
