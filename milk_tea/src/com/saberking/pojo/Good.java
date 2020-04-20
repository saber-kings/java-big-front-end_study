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

//	public String getgName() {
//		return gName;
//	}
//
//	public void setgName(String gName) {
//		this.gName = gName;
//	}
//
//	public int getgPrice() {
//		return gPrice;
//	}
//
//	public void setgPrice(int gPrice) {
//		this.gPrice = gPrice;
//	}
//
//	public String getgStuff() {
//		return gStuff;
//	}
//
//	public void setgStuff(String gStuff) {
//		this.gStuff = gStuff;
//	}
//
//	public String getgDesc() {
//		return gDesc;
//	}
//
//	public void setgDesc(String gDesc) {
//		this.gDesc = gDesc;
//	}

}
