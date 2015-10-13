package com.marsyoung.learning.java8;

public class FunctionalInterfaceDemo {

	public static void main(String[] args) {
		FunctionalInterfaceDemo f=new FunctionalInterfaceDemo();
		f.demo1();
	}
	
	public void demo1(){
		Converter<String,Integer> converter=(from)->Integer.valueOf(from);
		Integer converted=converter.convert("097");
		System.out.println(converted);
	}
	
}

interface Converter<F,T>{
	T convert(F from);
}