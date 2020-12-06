package com.atguigu.java8;

import java.util.Optional;

import org.junit.Test;

import com.atguigu.java8.Employee.Status;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

	@Test
	public void test4() {
		Optional<Employee> op = Optional.ofNullable(new Employee(1, "马明顺", 22, 888.88, Status.FREE));
		Optional<String> str = op.map(Employee::getName);
		System.out.println(str.get());
		Optional<String> str2 = op.flatMap(e -> Optional.of(e.getName()));
		System.out.println(str2.get());
	}

	@Test
	public void test3() {
		Optional<Employee> op = Optional.ofNullable(null);
//		if (op.isPresent()) {
//			System.out.println(op.get());
//		}
//		Employee emp = op.orElse(new Employee(1, "马明顺", 22, 888.88, Status.FREE));
//		System.out.println(emp);
		Employee emp = op.orElseGet(Employee::new);
		System.out.println(emp);
	}

	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty();
		System.out.println(op.get());
	}

	@Test
	public void test1() {
		Optional<Employee> op = Optional.of(new Employee());
		Employee emp = op.get();
		System.out.println(emp);
	}

	@Test
	public void test5() {
		Man man = new Man();
		man.setName("马明顺");
		String name = getGodnessName(man);
		System.out.println(man.getName() + "心中的女神：" + name);
	}

	// 需求：获取一个男人心中女神的名字
	public String getGodnessName(Man man) {
		if (man != null) {
			Godness g = man.getGod();

			if (g != null) {
				return g.getName();
			}
		}

		return "苍老师";
	}
	
	@Test
	public void test6() {
//		Optional<Godness> gn = Optional.ofNullable(null);
		Optional<Godness> gn = Optional.ofNullable(new Godness("波多老师"));
		NewMan man = new NewMan(gn);
		man.setName("马明顺");
//		String name = getGodnessName2(Optional.ofNullable(null));
		String name = getGodnessName2(Optional.ofNullable(man));
		System.out.println(man.getName() + "心中的女神：" + name);
//		System.out.println(name);
	}
	
	public String getGodnessName2(Optional<NewMan> man){
		return man.orElse(new NewMan())
				  .getGodness()
				  .orElse(new Godness("苍老师"))
				  .getName();
	}

}
