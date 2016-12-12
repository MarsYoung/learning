package com.marsyoung.learning.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 
 * 对很多应用来说，时间和日期的概念都是必须的。像生日，租赁期，事件的时间戳和商店营业时长，等等，都是基于时间和日期的；然而，
 * Java却没有好的API来处理它们。在Java SE 8中，添加了一个新包：java.time，它提供了结构良好的API来处理时间和日期。
 * 
 * 历史
 * 
 * 在Java刚刚发布，也就是版本1.0的时候，对时间和日期仅有的支持就是java.util.Date类。大多数开发者对它的第一印象就是，它根本不代表一个“
 * 日期”。实际上，它只是简单的表示一个，从1970-01-01Z开始计时的，精确到毫秒的瞬时点。由于标准的toString()方法，
 * 按照JVM的默认时区输出时间和日期，有些开发人员把它误认为是时区敏感的。
 * 
 * 在升级Java到1.1期间，Date类被认为是无法修复的。由于这个原因，java.util.Calendar类被添加了进来。悲剧的是，
 * Calendar类并不比java.util.Date好多少。它们面临的部分问题是：
 * 
 * 可变性。像时间和日期这样的类应该是不可变的。 偏移性。Date中的年份是从1900开始的，而月份都是从0开始的。
 * 命名。Date不是“日期”，而Calendar也不真实“日历”。 格式化。格式化只对Date有用，Calendar则不行。另外，它也不是线程安全的。
 * 大约在2001年
 * ，Joda-Time项目开始了。它的目的很简单，就是给Java提供一个高质量的时间和日期类库。尽管被耽搁了一段时间，它的1.0版还是被发布。
 * 很快，它就成为了广泛使用和流行的类库。随着时间的推移，有越来越多的需求，要在JDK中拥有一个像Joda-Time的这样类库。在来自巴西的Michael
 * Nascimento Santos的帮助下，官方为JDK开发新的时间/日期API的进程：JSR-310，启动了。
 * 
 * @author zhiyuma
 *
 */
public class NewTimeFuction {

	public static void main(String[] args) {
		NewTimeFuction ntf=new NewTimeFuction();
		ntf.localDateDemo();
		ntf.localTimeDemo();
		ntf.localDateTimeDemo();
		ntf.instandDemo();
		ntf.zonedDateTimeDemo();
		ntf.durationDemo();
		ntf.periodDemo();
		ntf.dateTimeFormatterDemo();

		ntf.getTodayAndTheLastDayOfTheMonth();
	}
	
	void localDateDemo(){
		LocalDate localDate= LocalDate.of(2015, Month.AUGUST, 17);
		System.out.println("2015年是闰年么？"+localDate.isLeapYear());
		System.out.println(localDate.toString());
	}
	
	void localTimeDemo(){
		LocalTime localTime=LocalTime.of(20, 30);
		System.out.println(localTime);
		localTime=localTime.withSecond(3);
		System.out.println(localTime);
		localTime=localTime.plusSeconds(3);
		System.out.println(localTime);
	}
	
	void localDateTimeDemo(){
		LocalDateTime localDateTime= LocalDateTime.of(2015, 8, 17, 11, 13);
		System.out.println(localDateTime);
		localDateTime=localDateTime.plusWeeks(2);
		System.out.println(localDateTime);
	}
	
	void instandDemo(){
		Instant start = Instant.now();
		Instant end =Instant.now();
		System.out.println(start.isBefore(end));
	}
	
	void zonedDateTimeDemo(){
		ZoneId zone = ZoneId.of("Europe/Paris");
		  
		LocalDate date = LocalDate.of(2014, Month.JUNE, 10);
		ZonedDateTime zdt1 = date.atStartOfDay(zone);
		System.out.println(zdt1);  
		Instant instant = Instant.now();
		ZonedDateTime zdt2 = instant.atZone(zone);
		System.out.println(zdt2);
	}
	
	/**
	 * Duration表示以秒和纳秒为基准的时长。例如，“23.6秒”。
	 */
	void durationDemo(){
		Duration duration=Duration.ofDays(1);
		System.out.println(duration);
	}
	
	
	/**
	 *Period表示以年、月、日衡量的时长。例如，“3年2个月零6天”。
	 */
	void periodDemo(){
		Period sixMonth=Period.ofMonths(6);
		System.out.println(sixMonth);
	}
	
	void dateTimeFormatterDemo(){
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate date = LocalDate.parse("2015-08-17", dtf);
		System.out.println(date);
	}
	
	void getTodayAndTheLastDayOfTheMonth(){
		LocalDate now=LocalDate.now();
		System.out.println(now.lengthOfMonth()-now.getDayOfMonth());;
		
		LocalDateTime now1=LocalDateTime.now();
	}
}
