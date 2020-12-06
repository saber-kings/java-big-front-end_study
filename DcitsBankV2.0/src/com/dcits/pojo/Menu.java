package com.dcits.pojo;

import java.io.Serializable;

/**
 * 菜单
 *
 */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String menuText;
	private String murl;
	
	public Menu() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuText=" + menuText + ", murl=" + murl + "]";
	}

}
