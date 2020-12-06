package com.atguigu.java8;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.atguigu.java8.Employee.Status;

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
public class TestStreamAPI3 {
	// 3.终止操作
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 79, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(106, "马明顺", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	/*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	*/
	@Test
	public void test3() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sum = list.stream().reduce(0, (x, y) -> x + y);
		System.out.println(sum);
		System.out.println("-----------------------------------");
		Optional<Double> op = emps.stream()
				.map(Employee::getSalary)
				.reduce(Double::sum);
		System.out.println(op.get());
	}
	
	/*
	 * 收集
	 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test4() {
		List<String> list = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toList());
		list.forEach(System.out::println);
		System.out.println("--------------------------------");
		Set<String> set = emps.stream()
				.map(Employee::getName)
				.collect(Collectors.toSet());
		set.forEach(System.out::println);
		System.out.println("--------------------------------");
		HashSet<String> hs = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toCollection(HashSet::new));
		hs.forEach(System.out::println);
	}

	@Test
	public void test5() {
		//总数
		Long count = emps.stream()
				.collect(Collectors.counting());
		System.out.println(count);
		System.out.println("--------------------------------");
		//平均值
		Double avg = emps.stream()
			.collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);
		System.out.println("--------------------------------");
		//总和
		Double sum = emps.stream()
				.collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		System.out.println("--------------------------------");
		//最大值
		double maxSalary = emps.stream()
				.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())))
				.get().getSalary();
		System.out.println(maxSalary);
		//最小值
		double minSalary = emps.stream()
				.map(Employee::getSalary)
				.collect(Collectors.minBy(Double::compare))
				.get();
		System.out.println(minSalary);
	}
	
	//分组
	@Test
	public void test6() {
		Map<Status, List<Employee>> map = emps.stream()
				.collect(Collectors.groupingBy(Employee::getStatus));
		map.keySet().forEach(s -> {
			System.out.println(s + " 组");
			map.get(s).forEach(System.out::println);
		});
	}
	
	//多级分组
	@Test
	public void test7() {
		Map<Status, Map<String, List<Employee>>> map = emps.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
					if (((Employee) e).getAge() <= 35) {
						return "青年";
					} else if (((Employee) e).getAge() <= 50) {
						return "中年";
					} else {
						return "老年";
					}
				})));
		map.keySet().forEach(s -> {
			System.out.println(s + " 组");
			Map<String, List<Employee>> map2 = map.get(s);
			map2.keySet().forEach(a -> {
				System.out.println(a + ":");
				map2.get(a).forEach(System.out::println);
			});
		});
	}
	
	//分区
	@Test
	public void test8() {
		Map<Boolean, List<Employee>> map = emps.stream()
			.collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
		map.keySet().forEach(s -> {
			if (s) {
				System.out.println("工资8000以上的员工：");
			} else {
				System.out.println("工资8000以下的员工：");
			}
			map.get(s).forEach(System.out::println);
		});
	}

	//其他
	@Test
	public void test9() {
		DoubleSummaryStatistics dss = emps.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getCount());
		System.out.println(dss.getSum());
		System.out.println(dss.getAverage());
		System.out.println(dss.getMax());
		System.out.println(dss.getMin());
	}

	@Test
	public void test10() {
		String str = emps.stream()
				.map(Employee::getName)
				.collect(Collectors.joining("," , "----", "----"));
		System.out.println(str);
	}
	
	/*
	 * 查找与匹配 
	 * allMatch(Predicate p) 检查是否匹配所有元素 
	 * anyMatch(Predicate p) 检查是否至少匹配一个元素
	 * noneMatch(Predicate p) 检查是否没有匹配的元素 
	 * findFirst() 返回第一个元素
	 * findAny() 返回当前流中的任意元素
	 * count() 返回流中元素总数
	 * max(Comparator c) 返回流中最大值
	 * min(Comparator c) 返回流中最小值
	 * 
	 */
	@Test
	public void test2() {
		long count = emps.stream().count();
		System.out.println(count);
		
		Optional<Employee> max = emps.stream()
			.max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(max.get());

		Optional<Double> min = emps.stream()
				.map(Employee::getSalary)
				.min(Double::compare);
		System.out.println(min.get());
	}

	@Test
	public void test1() {
		boolean b1 = emps.stream()
			.allMatch(e -> e.getStatus().equals(Status.BUSY));
		System.out.println(b1);
		boolean b2 = emps.stream()
				.anyMatch(e -> e.getStatus().equals(Status.BUSY));
		System.out.println(b2);
		boolean b3 = emps.stream()
				.noneMatch(e -> e.getStatus().equals(Status.BUSY));
		System.out.println(b3);
		Optional<Employee> op = emps.stream()
		 		.sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
				.findFirst();
		System.out.println(op.get());
		Optional<Employee> op1 = emps.parallelStream()
				.filter(e -> e.getStatus().equals(Status.FREE))
				.findAny();
		System.out.println(op1.get());
	}

}
