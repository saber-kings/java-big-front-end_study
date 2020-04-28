package com.saberking.model;

import javax.websocket.Session;

import lombok.Data;

@Data
public class UserOL {
	private String userId;
	private Session session;
}
