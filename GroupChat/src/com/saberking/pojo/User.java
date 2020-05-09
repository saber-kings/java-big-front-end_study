package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String uname;
	private String phone;
	private String upwd;
	private String realName;
	private String uimg;

}
