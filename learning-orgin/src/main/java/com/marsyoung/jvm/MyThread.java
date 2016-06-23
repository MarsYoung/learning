package com.marsyoung.jvm;

import java.util.UUID;

public class MyThread extends Thread{

	@Override
	public void run() {
		super.run();
		int i=0;
		while(true){
			i++;
			System.out.println(new String("abc"+UUID.randomUUID().toString()));
			if(i==10000){
				System.gc();
			}
		}
	}
	
	public static void main(String[] args) {
		new MyThread().start();;
	}
}
