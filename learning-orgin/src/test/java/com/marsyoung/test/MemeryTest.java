package com.marsyoung.test;

import java.io.IOException;
class MemeryTest{

	String s1="s1";
	String s2;
	
	public static void main(String[] args) throws IOException{
		MemeryTest mt=new MemeryTest();
		mt.runMethod(2147483647);
		System.in.read();
	}
	
	public void runMethod(int x){
		int y=x;
		System.out.println(y);
	}
}
