package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Section implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String sname;
	
	private String address;
	
	public String getIdString() {
		return this.id.toString();
	}
	
}
