package com.marsyoung.learning.thread;

import java.util.concurrent.CountDownLatch;


/**
 * 这种方式是让10个线程干完活，主线程再往下走。
 * 在程序中，由于只有9个线程干活了，所以会一直阻塞到await处。
 * @author zhiyuma
 *
 */
public class CountDownLatchTest2 {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(10);//新建一个并发的计数器 
		for (int i = 0; i < 9; i++) {
				System.out.println("线程启动："+i);
				new Thread(()->{
					latch.countDown();// 当前线程调用此方法，则计数减一
				}).start();
		}
		latch.await();//调用此方法会一直阻塞当前线程，直到计时器的值为0
		System.out.println("干完了");
	}
	
	


}