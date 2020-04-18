package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Good implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String gName;
	private int gPrice;
	private String gStuff;
	private String gDesc;

}
