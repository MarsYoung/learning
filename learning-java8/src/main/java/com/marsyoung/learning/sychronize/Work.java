package com.marsyoung.learning.sychronize;

public class Work {

	int i;

	public synchronized void plus(String name) {
		System.out.println("线程" + name + "进入对应的同步方法");
		while (true) {
			i++;
			System.out.println("线程" + name + "正在执行");
			System.out.println(i);
			// try {
			// this.wait();
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void minus(String name) {
		System.out.println("线程" + name + "进入对应的非同步方法");
		synchronized (this) {
			System.out.println("线程" + name + "进入对应的代码块");
			while (true) {
				i--;
				System.out.println("线程" + name + "正在执行");
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void expect4j(String name) {
		System.out.println("线程" + name + "进入对应的同步方法");
		while (true) {
			i++;
			System.out.println("线程" + name + "正在执行");
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void noSychronizedMethod(String name) {
		System.out.println("线程" + name + "进入对应的非同步方法");
		System.out.println("线程" + name + "进入对应的代码块");
		while (true) {
			i--;
			System.out.println("线程" + name + "正在执行");
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
