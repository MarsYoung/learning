package com.marsyoung.learning.java8;

public interface StaticInterface {

	String getMyName();
	
	default void sayhello(){
		System.out.println("hello");
	}
}
