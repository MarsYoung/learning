package com.marsyoung.test;

public class StringEquals {

	public static void main(String[] args) {
		String a="x";
		String b="x";
		System.out.println(a==b);
		
		String c=new String("x");
		System.out.println(a==c);
		
		a="y";
		System.out.println(a=="y");
		
	}
}
