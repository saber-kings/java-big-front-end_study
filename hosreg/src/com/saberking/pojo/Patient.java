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
	
	private Integer sid;
	
	private String ptype;
	
	public String getSidString() {
		return this.sid.toString();
	}
	
}
