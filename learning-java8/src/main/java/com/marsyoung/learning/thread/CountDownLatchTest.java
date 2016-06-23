package com.marsyoung.learning.thread;

import java.util.concurrent.CountDownLatch;


/**
 * 这种方式是让10个线程并发
 * @author zhiyuma
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(10);// 新建一个并发的计数器，可以支持10个线程并发
		for (int i = 0; i < 10; i++) {
			System.out.println("线程启动：" + i);
			new Thread(() -> {
				try {
					latch.await();// 调用此方法会一直阻塞当前线程，直到计时器的值为0
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程在执行。。");
			}).start();
			latch.countDown();// 当前线程调用此方法，则计数减一
		}
	}

}