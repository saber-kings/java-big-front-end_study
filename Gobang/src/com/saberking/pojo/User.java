package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户实体类
 * 
 * @author luanz
 *
 */
@Data
public class User implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String nickName;
	private String phone;
	private String upwd;
	private int winGames;
	private int totalGames;
	private int gold;
	private int jewel;
	private String realName;
	private String weixin;
	private String qq;
	private String avatar;
	private String remarks;
	private int rank;
	private String bio;
	private int status;
	private boolean newcomer;
	private String roomId;

	public String getIdString() {
		return new Integer(this.id).toString();
	}
}
