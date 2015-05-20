package com.marsyoung.test;

public class BigString {

	public static void main(String[] args) {
		String s="x";
		for(int i=0;i<2e10;i++){
			s=s+"x";
		}
		System.out.println(s);
		//System.out.println(2e2==200);
	}
}
