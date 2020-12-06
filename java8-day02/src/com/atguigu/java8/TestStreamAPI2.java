package com.atguigu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 一、Stream的是三个操作步骤
 * 
 * 1.创建 Stream
 * 
 * 2.中间操作
 * 
 * 3.终止操作（终端操作）
 * 
 * @author saber-kings
 *
 */
public class TestStreamAPI2 {
	
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66),
			new Employee(101, "张三", 18, 9999.99),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);

	// 中间操作
	/*
	 * 排序
	 * sorted()——自然排序(Comparable)
	 * sorted(Comparator com)——定制排序
	 */
	@Test
	public void test7(){
		List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
		list.stream()
			.sorted()
			.forEach(System.out::println);
		System.out.println("-----------------------------------");
		emps.stream()
			.sorted((e1, e2) -> {
				if (e1.getAge()==e2.getAge()) {
					return e1.getName().compareTo(e2.getName());
				} else {
					return Integer.compare(e1.getAge(), e2.getAge());
				}
			}).forEach(System.out::println);
	}
	/*
	 * 映射 
	 * map——接收 Lambda，将元素转换成其他形式或提取信息。
	 * 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
	 * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
	@Test
	public void test5() {
		List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
		list.stream().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("--------------------------------------");
		emps.stream().map(Employee::getName).forEach(System.out::println);
		System.out.println("--------------------------------------");
		Stream<Stream<Character>> stream = list.stream()
			.map(TestStreamAPI2::filterCharacter);//{a,a,a},{b,b,b}
		stream.forEach(sm -> sm.forEach(System.out::println));
		System.out.println("--------------------------------------");
		Stream<Character> sm = list.stream()
			.flatMap(TestStreamAPI2::filterCharacter);//{a,a,a,b,b,b}
		sm.forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharacter(String str) {
		//add(Object obj) addAll(Collection coll)
		List<Character> list = new ArrayList<>();
		for (Character ch : str.toCharArray()) {
			list.add(ch);
		}
		return list.stream();
	}
	
	/* 
	 * 内部迭代：迭代操作由 Stream API 完成
	 * filter(Predicate p) 接收 Lambda ， 从流中排除某些元素。
	 */
	@Test
	public void test6() {
		//中间操作：不会执行任何操作
		Stream<Employee> stream = emps.stream().filter(e -> {
			System.out.println("Stream API 中间操作");
			return e.getAge() > 35;
		});
		//终止操作：一次性执行全部操作，即“惰性求值”
		stream.forEach(System.out::println);
	}
	/*
	 * skip(long n) 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与 limit(n) 互补
	 * distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素 
	 */
	@Test
	public void test4() {
		emps.stream()
		.filter(e -> e.getSalary() > 5000)
		.skip(2)
		.distinct()
		.forEach(System.out::println);
	}
	
	/*
	 * limit(long maxSize) 截断流，使其元素不超过给定数量。 
	 */
	@Test
	public void test3() {
		emps.stream()
				.filter(e -> {
					System.out.println("短路!");// && ||
					return e.getSalary() > 5000;
				})
				.limit(2)
				.forEach(System.out::println);
	}
	
	/* 内部迭代：迭代操作由 Stream API 完成
	 * filter(Predicate p) 接收 Lambda ， 从流中排除某些元素。
	 */
	@Test
	public void test1() {
		//中间操作：不会执行任何操作
		Stream<Employee> stream = emps.stream().filter(e -> {
			System.out.println("Stream API 中间操作");
			return e.getAge() > 35;
		});
		//终止操作：一次性执行全部操作，即“惰性求值”
		stream.forEach(System.out::println);
	}

	//外部迭代：
	@Test
	public void test2() {
		Iterator<Employee> it = emps.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
