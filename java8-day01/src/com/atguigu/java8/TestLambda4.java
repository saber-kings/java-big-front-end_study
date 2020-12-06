package com.atguigu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Java8 内置四大核心函数式接口 Consumer<T> ：消费型接口 void accept(T t);
 * 
 * Supplier<T> ：供给型接口 T get();
 * 
 * Function<T, R> ：函数型接口 R apply(T t);
 * 
 * Predicate<T> ：断言型接口 boolean test(T t);
 * 
 * @author saber-kings
 *
 */
public class TestLambda4 {
	//Predicate<T> 断言型接口：
	@Test
	public void test4() throws Exception {
		List<String> list = Arrays.asList("Hello", "atguigu","Lambda","www","ok");
		List<String> strList = filterStr(list, s -> s.length() > 3);
		for (String str : strList) {
			System.out.println(str);
		}
	}
	
	// 需求：将满足条件的字符串，放入集合中
	public List<String> filterStr(List<String> list, Predicate<String> pre) {
		List<String> strList = new ArrayList<String>();
		for (String str : list) {
			if (pre.test(str)) {
				strList.add(str);
			}
		}
		return strList;
	}

	// Function<T, R> 函数型接口：
	@Test
	public void test3() throws Exception {
		String trimStr = strHandler("\t\t\t 我小马哥威武！    ", str -> str.trim());
		System.out.println(trimStr);
		String subString = strHandler("\t\t\t 我小马哥威武！    ", str -> str.trim().substring(1, 4));
		System.out.println(subString);
	}

	// 需求：用于处理字符串
	public String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}

	// Supplier<T> 供给型接口：
	@Test
	public void test2() throws Exception {
		List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
		for (Integer n : numList) {
			System.out.println(n);
		}
	}

	// 需求：产生指定个数整数整数，并放入集合中
	public List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}

	// Consumer<T> 消费型接口：
	@Test
	public void test1() throws Exception {
		happy(10000, m -> System.out.println("老马喜欢大宝剑，每次消费：" + m + "元"));
	}

	public void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}
}