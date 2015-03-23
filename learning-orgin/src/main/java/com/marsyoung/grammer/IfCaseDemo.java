package com.marsyoung.grammer;

public class IfCaseDemo {

	public static void main(String[] args) {
		String s1="hello";
		String s2="hello ";
		String s3="hellohello";
		String s4="hello";
		
		if(s1.equals(s2)){
			System.out.println("s1.equals(s2) is true");
		}
		if(s1.equals(s3)){
			System.out.println("s1.equals(s3) is true");
		}
		if(s1.equals(s4)){
			System.out.println("s1.equals(s4) is true");
		}
		
		if(s1 == s2){
			System.out.println("s1 == s2 is true");
		}
		if(s1 == s3){
			System.out.println("s1 == s3 is true");
		}
		if(s1 == s4){
			System.out.println("s1 == s4 is true");
		}
		
		String s5="hello";
		if(s1==""){
			System.out.println("s1==\"\"");
		}else if(s1==s2){
			System.out.println("s1==s2");
		}else if(s1==s3){
			System.out.println("s1==s3");
		}else if(s1==s4){
			System.out.println("s1==s4");//只输出一次，在这儿判断条件退出。
		}else if(s1==s5){
			System.out.println("s1==s5");
		}else{
			System.out.println("s1==nothing");
		}
		
		//说明，在不能确定所有判断条件都是互斥的，应该采用switch case的方法。
	}
}
