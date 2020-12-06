package com.atguigu.java8;

public class Man {

	private String name;
	private Godness god;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Man() {
	}

	public Man(Godness god) {
		this.god = god;
	}

	public Godness getGod() {
		return god;
	}

	public void setGod(Godness god) {
		this.god = god;
	}

	@Override
	public String toString() {
		return "Man [name=" + name + ", god=" + god + "]";
	}

}
