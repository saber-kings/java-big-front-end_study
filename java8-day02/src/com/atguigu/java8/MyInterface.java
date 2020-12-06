package com.atguigu.java8;

public interface MyInterface {

	default String getName() {
		return "小马哥被榨干了";
	}
	
	public static void show() {
		System.out.println("小马哥一夜十三郎");
	}
	
}
