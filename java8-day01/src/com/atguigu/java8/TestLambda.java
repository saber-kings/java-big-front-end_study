package com.atguigu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestLambda {

	// 原匿名内部类
	@Test
	public void test1() throws Exception {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}

		};

		TreeSet<Integer> ts = new TreeSet<Integer>(comparator);
	}

	// Lambda方式
	@Test
	public void test2() throws Exception {
		Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

		TreeSet<Integer> ts = new TreeSet<Integer>(comparator);
	}

	List<Employee> employees = Arrays.asList(new Employee("张三", 18, 9999.99), new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 3333.99), new Employee("赵六", 16, 6666.99), new Employee("田七", 8, 7777.99));

	// 需求：获取当前公司中员工年龄大于35的员工信息
	@Test
	public void test3() throws Exception {
		List<Employee> list = filterEmployees(employees);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	public List<Employee> filterEmployees(List<Employee> list) {
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : list) {
			if (emp.getAge() > 35) {
				emps.add(emp);
			}
		}
		return emps;
	}

	// 需求：获取当前公司中员工工资大于5000的员工信息
	// 优化方式一：策略设计模式
	@Test
	public void test4() throws Exception {
		// 策略一
		List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
		for (Employee employee : list) {
			System.out.println(employee);
		}

		// 策略二
		System.out.println("----------------------------");

		List<Employee> list2 = filterEmployee(employees, new FilterEmployeeBySalary());
		for (Employee employee : list2) {
			System.out.println(employee);
		}
	}

	// 优化方式二：匿名内部类
	@Test
	public void test5() throws Exception {
		List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
			@Override
			public boolean test(Employee t) {
				return t.getSalary() <= 5000;
			}
		});
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	// 优化方式三：Lambda表达式
	@Test
	public void test6() throws Exception {
		List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() <= 5000);
		list.forEach(System.out::println);
	}

	// 优化方式四：Lambda表达式
	@Test
	public void test7() throws Exception {
		employees.stream().filter((e) -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
		System.out.println("---------------------------------------");
		employees.stream().map(Employee::getName).forEach(System.out::println);
	}

	public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : list) {
			if (mp.test(emp)) {
				emps.add(emp);
			}
		}
		return emps;
	}

	public List<Employee> filterEmployees2(List<Employee> list) {
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : list) {
			if (emp.getSalary() > 5000) {
				emps.add(emp);
			}
		}
		return emps;
	}
}
