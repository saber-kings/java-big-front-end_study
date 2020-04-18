package com.saberking.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Custom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String cname;
	private Date created;
	private Date updated;
	private String phone;
	private String email;

}
