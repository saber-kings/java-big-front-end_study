package com.saberking.model;

import lombok.Data;

@Data
public class Group {

	private int id;
	private String gname;
	private String gimg;
	private String gdesc;
	
	public String getIdString() {
		return new Integer(id).toString();
	}
	
}
