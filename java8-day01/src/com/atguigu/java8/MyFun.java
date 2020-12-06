package com.atguigu.java8;

@FunctionalInterface
public interface MyFun<T> {
	T getValue(T t);
}
