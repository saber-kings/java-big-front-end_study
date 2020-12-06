package com.atguigu.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author saber-kings
 *
 */
public class TestLambda3 {
	
	List<Employee> emps = Arrays.asList(new Employee("张三", 18, 9999.99), new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 3333.99), new Employee("赵六", 16, 6666.99), new Employee("田七", 8, 7777.99));

	@Test
	public void test1() throws Exception {
		Collections.sort(emps, (e1, e2) -> {
			if (e1.getAge() == e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			} else {
				return -Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		for (Employee emp: emps) {
			System.out.println(emp);
		}
	}

	@Test
	public void test2() throws Exception {
		String trimStr = strHandler("\t\t\t 我大青软威武！    ", str -> str.trim());
		System.out.println(trimStr);
		System.out.println("------------------------------");
		String upper = strHandler("mingshun ma", str -> str.toUpperCase());
		System.out.println(upper);
		System.out.println("------------------------------");
		String subStr = strHandler(trimStr, str -> str.substring(2, 4));
		System.out.println(subStr);
	}

	//需求：用于处理字符串
	public String strHandler(String str, MyFunction mf) {
		return mf.getValue(str);
	}

	@Test
	public void test3() throws Exception {
		op(100L, 200L, (x, y) -> x + y);
		op(100L, 200L, (x, y) -> x * y);
	}

	//需求：对于两个Long型处理
	public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
		System.out.println(mf.getValue(l1, l2));
	}
}
