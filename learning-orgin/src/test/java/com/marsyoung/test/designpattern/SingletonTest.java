package com.marsyoung.test.designpattern;

import com.marsyoung.designpattern.singleton.Singleton7;

public class SingletonTest {


	public static void main(String[] args) {

		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1:"+Singleton7.getInstance().toString());
				}
			}
		}.start();

		new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("2:"+Singleton7.getInstance().toString());
				}
			}
		}.run();
	}
}
