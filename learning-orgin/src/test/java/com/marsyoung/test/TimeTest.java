package com.marsyoung.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
	public static void main(String[] args) {
//		for (int i = 1; i < 100; i++) {
//			String time = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() - 27 * 24 * 3600
//					* 1000));
//			System.out.println(time);
//		}
		String time = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() - 27 * 24 * 3600
				* 1000l));
		System.out.println(time);
	}
}
