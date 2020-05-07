package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String pname;
	
	private int age;
	
	private String sex;
	
	private int sid;
	
	private String ptype;
	
}
