package com.atguigu.java8;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用是Lambda表达式的另一种表达形式）
 * 
 * 主要有三种语法格式：
 * 
 * 对象::实例方法名
 * 
 * 类::静态方法名
 * 
 * 类::实例方法名
 * 
 * 注意：
 * ①Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * ②若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * 
 * 二、构造器引用：
 * 
 * 格式：
 * 
 * ClassName::new
 * 
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 * 
 * 三、数组引用
 * 
 * Type::new
 * 
 * @author saber-kings
 *
 */
public class TestMethodRef {
	
	//数组引用
	@Test
	public void test7(){
		Function<Integer, String[]> fun = x -> new String[x];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		Function<Integer, String[]> fun1 = String[]::new;
		String[] strs2 = fun1.apply(20);
		System.out.println(strs2.length);
	}

	//构造器引用
	@Test
	public void test5(){
		Supplier<Employee> sub = () -> new Employee();
		sub.get();
		//构造器引用
		Supplier<Employee> sub1 = Employee::new;
		System.out.println(sub1.get());
	}

	@Test
	public void test6(){
		Function<Integer, Employee> fun = (x) -> new Employee(x);
		System.out.println(fun.apply(18));
		Function<Integer, Employee> fun1 = Employee::new;
		System.out.println(fun1.apply(800));
		BiFunction<Integer, String, Employee> bf = Employee::new;
		System.out.println(bf.apply(500, "小马哥"));
	}

	//类::静态方法名
	@SuppressWarnings("unused")
	@Test
	public void test4(){
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		BiPredicate<String, String> bp1 = String::equals;
	}

	//类::静态方法名
	@SuppressWarnings("unused")
	@Test
	public void test3(){
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;
		int res = com1.compare(100, 200);
		System.out.println(res);
	}

	//对象::实例方法名
	@SuppressWarnings("unused")
	@Test
	public void test1(){
		Consumer<String> con = x -> System.out.println(x);
		
		PrintStream ps = System.out;
		Consumer<String> con1 = ps::println;
		
		Consumer<String> con2 = System.out::println;
		con2.accept("小马哥");
	}

	@Test
	public void test2(){
		Employee emp = new Employee();
		Supplier<String> sup = () -> emp.getName();
		String str = sup.get();
		System.out.println(str);
		
		Supplier<Integer> sup1 = emp::getAge;
		Integer num = sup1.get();
		System.out.println(num);
	}
}
