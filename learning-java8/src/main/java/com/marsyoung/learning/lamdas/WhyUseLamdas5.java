package com.marsyoung.learning.lamdas;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhiyuma stream+laziness => efficiency
 * 
 */
public class WhyUseLamdas5 {
	
	public static boolean isEven(int number) {
		return number % 2 == 0;
	}

	public static int doubleIt(int number) {
		return number * 2;
	}

	public static boolean isGreaterThan5(int number) {
		return number > 5;
	}
	
	public void before(){
		List<Integer> numbers =Arrays.asList(1,2,3,4,5,6);
		for(int number:numbers){
			if(number%2==0){
				int n2=number *2;
				if(n2>5){
					System.out.println(n2);break;
				}
			}
		}
	}
	
	public void showMe(){
		List<Integer> numbers =Arrays.asList(1,2,3,4,5,6);
		//numbers.forEach(System.out::println);
		System.out.println(
				numbers.stream().filter(WhyUseLamdas5::isEven).map(WhyUseLamdas5::doubleIt).filter(WhyUseLamdas5::isGreaterThan5).findFirst());
	}
	
	public static void main(String[] args) {
		new WhyUseLamdas5().showMe();
		new WhyUseLamdas5().before();
	}
}
