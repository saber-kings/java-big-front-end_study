package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 角色实体类
 * 
 * @author luanz
 *
 */
@Data
public class Role implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleName;

}
