package com.marsyoung.learning.sychronize;

public class Demo {

	/**
	 * 1.当两个并发线程 访问同一个对象object中的这个synchronized(this)同步代码块时
	 * 一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。 Result:
	 * t1和t2操作的是同一个对象w的同步代码块：
	 * 
	 */
	public void test1() {
		Work w = new Work();
		MyThread t1 = new MyThread(w, "线程1", 1);
		MyThread t2 = new MyThread(w, "线程2", 1);
		new Thread(t1).start();
		new Thread(t2).start();
	}

	/**
	 * 2. 当一个线程访问object的一个synchronized(this)同步代码块时，
	 * 另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
	 */
	public void test2() {
		Work w = new Work();
		MyThread t1 = new MyThread(w, "线程1", 1);
		MyThread t2 = new MyThread(w, "线程2", 3);
		new Thread(t1).start();
		new Thread(t2).start();
	}
	
	/**
	 * 2. 当一个线程访问object的一个synchronized(this)同步代码块时，
	 * 其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
	 * Result:
	 * 只有一个sychronized块能执行
	 */
	public void test3() {
		Work w = new Work();
		MyThread t1 = new MyThread(w, "线程1", 1);
		MyThread t2 = new MyThread(w, "线程2", 2);
		new Thread(t1).start();
		new Thread(t2).start();
	}

	/**
	 * 主线程执行循环方法检测工作线程是否干完活了 工作线程自己循环干活 现在主线程里面让新的线程wait.
	 * wait的时候的代码块是sychronized 这个时候主线程是否可以继续
	 * Result:
	 * 是可以继续的。
	 * 因为主线程并没有和工作线程同时调用同一个被锁住的资源中的同步代码块。
	 */
	public void test() {
		Work w = new Work();
		MyThread r = new MyThread(w, "线程100", 100);
		Thread t = new Thread(r);
		t.start();
		synchronized (t) {
			System.out.println("主线程进入同步代码块");
			while (true) {
				System.out.println("主线程循环,w.i=" + w.i);
				if (w.i >= 5) {
					System.out.println("主线程检测到工作线程完成工作");
					r.waitForWorkDone(); 
					//终于理解了，这里不管调用wait方法的对象是谁，当前线程都将放弃对这个对象的锁，进入等待执行状态
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		new Demo().test();
	}

}
