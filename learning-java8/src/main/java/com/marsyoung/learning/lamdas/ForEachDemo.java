package com.marsyoung.learning.lamdas;

import java.util.Arrays;
import java.util.List;

/**
 * 遍历集合
 * @author zhiyuma
 *
 */
public class ForEachDemo {
	public void java7(){
		List<String> list=Arrays.asList("java5","java6","java7");
		for(String s:list){
			System.out.println(s);
		}
	}
	
	public void java8(){
		List<String> list=Arrays.asList("java8","java8","java8");
		list.forEach(n->System.out.println(n));
	}
}
