package com.marsyoung.learning.lamdas;

import java.util.List;


/**
 * 通过runnable启动新线程的写法
 * @author zhiyuma
 *
 */
public class RunnableDemo {

	public void java7(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before java8");
			}
		}).start();
	}
	
	public void java8(){
		new Thread(()->{
			System.out.println("In java8");
		}).start();
	}
	
}
