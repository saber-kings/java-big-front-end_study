package com.atguigu.java8;

@FunctionalInterface
public interface MyFunction2<T, R> {
	R getValue(T t1,T t2);
}
