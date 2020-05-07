package com.saberking.utils;
 
 
public class ClassUtil {
	public static Class<?> loadClassByName(String className) throws ClassNotFoundException{
		
		Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
		System.out.println("====被装载的类=======" + clazz.getName());
		return clazz;
	}
}
 
 
 
