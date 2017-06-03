package com.marsyoung.learning.sychronize;

public class MyThread implements Runnable {

	Work work;//线程所持有的工作类
	String name;//线程名称
	int testNumber;//测试编号

	/**
	 * 通过构造方法指定线程的操作类，名称以及测试编码
	 * @param work
	 * @param name
	 * @param testNumber
	 */
	public MyThread(Work work, String name,int testNumber) {
		super();
		this.work = work;
		this.name = name;
		this.testNumber=testNumber;
	}

	public void run() {
		System.out.println("线程"+name+"开始执行");
		switch (testNumber) {
		case 1:
			work.minus(name);
			break;
		case 2:
			work.plus(name);
			break;
		case 3:
			work.noSychronizedMethod(name);
			break;
		case 100:
			work.expect4j(name);
			break;
		default:
			break;
		}
		System.out.println();
	}
	
	public void waitForWorkDone(){
		synchronized (this) {
			System.out.println("进入线程暂停方法中");
			try {
				wait();//暂停的竟然是主线程？？？
				//理解：调用这个方法的是主线程，所以主线程释放该线程的锁，
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("退出线程暂停方法中");
		}
	}

}
