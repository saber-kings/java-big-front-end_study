package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Workshop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String wname;
	
	private String address;

	private int uid;
	
	public String getIdString() {
		return this.id.toString();
	}
	
}
