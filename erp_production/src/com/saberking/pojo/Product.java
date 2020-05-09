package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String pname;
	
	private int needNum;
	
	private String mould;
	
	private Integer wid;
	
	private String pline;
	
	public String getWidString() {
		return this.wid.toString();
	}
	
}
