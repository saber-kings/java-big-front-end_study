package com.saberking.service;

public class Singleton {

	//单例模式
	private Singleton() {
		
	}
	
	private static Singleton singleton;
	
	public synchronized static Singleton getSingleton() {
		if (singleton==null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
}
