package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 管理员实体类
 * 
 * @author luanz
 *
 */
@Data
public class Admin implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String uname;
	private String phone;
	private String upwd;
	private String realName;
	private String uimg;

	public String getIdString() {
		return new Integer(this.id).toString();
	}
}
