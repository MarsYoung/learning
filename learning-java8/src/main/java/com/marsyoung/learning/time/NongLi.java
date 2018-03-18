package com.marsyoung.learning.time;

import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;

/**
 * 农历
 * @author zhiyuma
 *
 */
public class NongLi {

	public static void main(String[] args) {

	}
	
	/**
	 * 民国历
	 */
	public void testMinguo(){
		MinguoDate date=MinguoChronology.INSTANCE.dateNow();
		System.out.println(date);
	}
}
