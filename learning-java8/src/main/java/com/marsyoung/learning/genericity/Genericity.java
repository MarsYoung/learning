package com.marsyoung.learning.genericity;

import java.util.Collection;

public class Genericity {

	
	static String $2="";
	
	public static String $(String x){
		return x;
	}
	
	public static <T> String getClassNmae(Class<T> x){
		return x.getName();
	}
	
	public static <Y> Y getClass(Y y){
		return y;
	} 
	
	static boolean add(Collection<?> p){
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println($2);
		System.out.println($("2"));
	}
	
	
}
