package com.atguigu.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class TestLocalDateTime {

	// 6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	@Test
	public void test7() {
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
		System.out.println(ldt);

		LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
		System.out.println(zdt);
	}

	@Test
	public void test6() {
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}

	// 5. DateTimeFormatter : 解析和格式化日期或时间
	@Test
	public void test5() {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

		LocalDateTime ldt = LocalDateTime.now();
		
		String strDate = ldt.format(dtf);

		System.out.println(strDate);
		
		System.out.println("---------------------------------");
		
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

		String strDate2 = dtf2.format(ldt);
		
		System.out.println(strDate2);
		
		LocalDateTime newDate = LocalDateTime.parse(strDate2, dtf2);
		System.out.println(newDate);
	}

	// 4. TemporalAdjuster : 时间校正器
	@Test
	public void test4() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);

		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);

		// 自定义：下一个工作日
		LocalDateTime ldt5 = ldt.with(l -> {
			LocalDateTime ldt4 = (LocalDateTime) l;

			DayOfWeek dow = ldt4.getDayOfWeek();

			if (dow.equals(DayOfWeek.FRIDAY)) {
				return ldt4.plusDays(3);
			} else if (dow.equals(DayOfWeek.SATURDAY)) {
				return ldt4.plusDays(2);
			} else {
				return ldt4.plusDays(1);
			}
		});

		System.out.println(ldt5);

	}

	// 3.
	// Duration : 用于计算两个“时间”间隔
	// Period : 用于计算两个“日期”间隔
	@Test
	public void test3() {
		Instant ins1 = Instant.now();

		System.out.println("--------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		Instant ins2 = Instant.now();

		Duration duration = Duration.between(ins1, ins2);
		System.out.println("所耗费时间为：" + duration);
		System.out.println("所耗费时间为：" + duration.toMillis());

		System.out.println("----------------------------------");

		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2015, 1, 1);

		Period pe = Period.between(ld2, ld1);
		System.out.println(pe.getYears());
		System.out.println(pe.getMonths());
		System.out.println(pe.getDays());
	}

	// 2. Instant : 时间戳。 （使用 Unix 元年 1970年1月1日 00:00:00 到某个时间之间所经历的毫秒值）
	@Test
	public void test2() {
		Instant ins = Instant.now(); // 默认使用 UTC（世界协调时间即“格林威治时间”） 时区
		System.out.println(ins);

		OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);

		System.out.println(ins.toEpochMilli());

		Instant ins2 = Instant.ofEpochSecond(60);
		System.out.println(ins2);
	}

	// 1. LocalDate、LocalTime、LocalDateTime
	@Test
	public void test1() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ld2 = LocalDateTime.of(2020, 9, 24, 23, 10, 44);
		System.out.println(ld2);

		LocalDateTime ldt3 = ld2.plusYears(20);
		System.out.println(ldt3);

		LocalDateTime ldt4 = ld2.minusMonths(2);
		System.out.println(ldt4);

		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
	}

}
