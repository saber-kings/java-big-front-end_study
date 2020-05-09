package com.saberking.model;

import lombok.Data;

/**
 * 房间实体类
 * 
 * @author luanz
 *
 */
@Data
public class Room {

	public Room() {
		this.turn = "b";
	}

	private String roomId;
	private String roomName;
	private String turn;
	private Player leftPlayer;
	private Player rightPlayer;

}
