package com.atguigu.java8;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 一、Lambda表达式的基础语法：Java8中引入了一个新的操作符“->”，该操作符为箭头操作符或Lambda操作符
 * 						 箭头操作符将Lambda表达式拆分成两部分：
 * 
 * 左侧：Lambda表达式参数列表 
 * 右侧：Lambda表达式中所需要执行的功能，即Lambda体
 * 
 * 语法格式一：无参数，无返回值 () -> System.out.println("Hello Lambda"); 
 * 语法格式二：有一个参数，无返回值 (x) -> System.out.println(x); 
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 语法格式四：有两个以上参数，有返回值，并且Lambda体中有多条语句 
 * 			(x,y) -> { 
 * 				TODO...
 * 				return ...; 
 * 			};
 * 语法格式五：若Lambda体中只有一条语句，return和大括号可以不写 
 * 			Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，
 * 			因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
 * 			(Integer x, Integer y) -> Integer.compare(x, y);
 * 
 * 口诀：
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 * 
 * 二、Lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用 @FunctionalInterface 注解修饰
 * 			可以检查是否是函数式接口
 * 
 * @author saber-kings
 *
 */
public class TestLambda2 {

	@Test
	public void test1() throws Exception {
		int num = 0;// jdk1.7前，必须是final；jdk1.8也只是可以不写（隐式声明了），但是这个变量的值还是不能改变

		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello World!" + num);
			}
		};
		r.run();
		System.out.println("------------------------------------");
		Runnable r1 = () -> System.out.println("Hello Lambda!" + num);
		r1.run();
	}

	@Test
	public void test2() throws Exception {
		Consumer<String> con = x -> System.out.println(x);
		con.accept("我大青软威武！");
	}

	@SuppressWarnings("unused")
	@Test
	public void test3() throws Exception {
		Comparator<Integer> com = (x, y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};
	}

	@SuppressWarnings("unused")
	@Test
	public void test4() throws Exception {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
	}

	//需求：对一个数进行运算
	@Test
	public void test5() throws Exception {
//		String[] strs = {"aaaa","bbbb","cccc"};
//		String[] strs;
//		strs = {"aaaa","bbbb","cccc"}
		Integer res = operation(100, x -> x * x);
		System.out.println(res);
		System.out.println("---------------------------");
		System.out.println(operation(200, y -> y + 200));
	}
	
	public Integer operation(Integer num, MyFun<Integer> mf) {
		return mf.getValue(num);
	}

}
