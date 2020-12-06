package com.atguigu.java8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {

	/**
	 * ForkJoin 框架
	 */
	@Test
	public void test1() {
		Instant start = Instant.now();

		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000000L);//2157-45594
		Long sum = pool.invoke(task);//减少线程数 35567
		System.out.println(sum);

		Instant end = Instant.now();

		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis() + " 毫秒");
	}

	@Test
	public void test2() {
		Instant start = Instant.now();//1107-33884
		//减少线程数 34587
		
		long sum = 0;
		
		for (long i = 0; i <= 100000000000L; i++) {
			sum += i;
		}
		
		System.out.println(sum);

		Instant end = Instant.now();

		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis() + " 毫秒");
	}
	
	/**
	 * java8并行流
	 */
	@Test
	public void test3() {
		Instant start = Instant.now();//26407
		
		long sum = LongStream.rangeClosed(0, 100000000000L)
				.parallel()
				.reduce(0, Long::sum);
		
		System.out.println(sum);
		
		Instant end = Instant.now();

		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis() + " 毫秒");
	}
}
