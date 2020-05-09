package com.saberking.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 菜单权限实体类
 * 
 * @author luanz
 *
 */
@Data
public class Menu implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String menuText;
	private String murl;

}
