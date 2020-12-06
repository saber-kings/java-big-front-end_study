package com.dcits.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人信息
 *
 */
public class PersonInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int aid;
	private String realName;
	private int age;
	private String sex;
	private long card;
	private BigDecimal balance;
	private String address;
	private String phone;
	
	public PersonInfo() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getCard() {
		return card;
	}
	public void setCard(long card) {
		this.card = card;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "PersonInfo [id=" + id + ", aid=" + aid + ", realName=" + realName + ", age=" + age + ", sex=" + sex
				+ ", card=" + card + ", balance=" + balance + ", address=" + address + ", phone=" + phone + "]";
	}

}
