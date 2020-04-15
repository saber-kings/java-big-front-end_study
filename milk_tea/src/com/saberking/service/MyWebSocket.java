package com.saberking.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo/{id}")
public class MyWebSocket {

	//连接创建成功时
	@OnOpen
	public void open(Session session,@PathParam(value="id")String id) {
		System.out.println(id);
	}

	//连接关闭时
	@OnClose
	public void close(@PathParam(value="id")String id) {
		
	}
	
	//连接出错时
	@OnError
	public void error(Throwable error) {
		
	}
	
	//当连接过程中接收到消息时
	@OnMessage
	public void message(Session session,@PathParam(value="id")String id,String message) {
		
		
	}
	
}
