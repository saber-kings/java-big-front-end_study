package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleName;

}
