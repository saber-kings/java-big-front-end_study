package com.saberking.model;

import javax.websocket.Session;

import lombok.Data;

/**
 * 玩家实体类
 * 
 * @author luanz
 *
 */
@Data
public class Player {
	private String id;
	private String nickName;
	private String avatar;
	/**
	 * 段位目前没加
	 */
	private int rank;
	/** 
	 * 准备状态（0：未准备，1：已准备）
	 */
	private int reday = 0;
	private Session session;
}
