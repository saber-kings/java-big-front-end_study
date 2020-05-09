package com.saberking.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.saberking.model.Player;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.User;
import com.saberking.utils.BeanUtil;
import com.saberking.utils.WebsocketUtil;

/**
 * 游戏房间WebSocket连接相关操作类
 * 
 * @author luanz
 *
 */
@ServerEndpoint("/room/{rid}/{id}")
public class RoomWebSocket {
	private UserService userService = new UserService();

	/**
	 * 连接创建成功时调用
	 * 
	 * @param session 会话
	 * @param rid     房间ID
	 * @param id      用户ID
	 */
	@OnOpen
	public void open(Session session, @PathParam(value = "rid") String rid, @PathParam(value = "id") String id) {
		Player player = new Player();
		String userJson = userService.getById(id);
		User user = JSON.parseObject(userJson, User.class);
		BeanUtil.copyProperties(user, player);
		player.setSession(session);
		Singleton singleton = Singleton.getSingleton();
		singleton.joinRoom(rid, player);
	}

	/**
	 * 连接关闭时调用
	 * 
	 * @param rid 房间ID
	 * @param id  用户ID
	 */
	@OnClose
	public void close(@PathParam(value = "rid") String rid, @PathParam(value = "id") String id) {
		Singleton singleton = Singleton.getSingleton();
		singleton.quitRoom(rid, id);
	}

	/**
	 * 连接出错时调用
	 * 
	 * @param session 会话
	 * @param error   错误
	 * @param rid     房间ID
	 * @param id      用户ID
	 */
	@OnError
	public void error(Session session, Throwable error, @PathParam(value = "rid") String rid,
			@PathParam(value = "id") String id) {
		System.out.println(
				"用户：" + id + "，连接出错：" + error.getLocalizedMessage() + "，地址：" + WebsocketUtil.getRemoteAddress(session));
		Singleton singleton = Singleton.getSingleton();
		singleton.quitRoom(rid, id);
	}

	/**
	 * 当连接过程中接收到消息时调用
	 * 
	 * @param session 会话
	 * @param rid     房间ID
	 * @param id      用户ID
	 * @param message 消息
	 */
	@OnMessage
	public void message(Session session, @PathParam(value = "rid") String rid, @PathParam(value = "id") String id,
			String message) {
		Singleton singleton = Singleton.getSingleton();
		singleton.getOpertation(session, rid, id, message);
	}

}
