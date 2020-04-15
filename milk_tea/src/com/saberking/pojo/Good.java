package com.saberking.pojo;

import java.io.Serializable;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public int getgPrice() {
		return gPrice;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public String getgStuff() {
		return gStuff;
	}

	public void setgStuff(String gStuff) {
		this.gStuff = gStuff;
	}

	public String getgDesc() {
		return gDesc;
	}

	public void setgDesc(String gDesc) {
		this.gDesc = gDesc;
	}

	@Override
	public String toString() {
		return "Good [id=" + id + ", gName=" + gName + ", gPrice=" + gPrice + ", gStuff=" + gStuff + ", gDesc=" + gDesc
				+ "]";
	}

}
