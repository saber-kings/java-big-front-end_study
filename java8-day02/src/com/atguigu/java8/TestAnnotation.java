package com.atguigu.java8;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 重复注解与类型注解
 * @author saber-kings
 *
 */
public class TestAnnotation {
	
	//checker framework
	public /* @NonNull */ Object obj = null;
	
	@Test
	public void test1() throws Exception {
		Class<TestAnnotation> clazz = TestAnnotation.class;
		Method m1 = clazz.getMethod("show");
		MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
		for (MyAnnotation myAnnotation : mas) {
			System.out.println(myAnnotation.value());
		}
	}

	@MyAnnotation("Hello")
	@MyAnnotation("World")
	public void show(@MyAnnotation("abc") String str) {
		
	}
	
}
