package com.dcits.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账号
 *
 */
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String phone;
	private String password;
	private String realName;
	private BigDecimal balance;
	private int status;
	
	public Account() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", account=" + account + ", phone=" + phone + ", password=" + password
				+ ", realName=" + realName + ", balance=" + balance + ", status=" + status + "]";
	}

}
