package com.marsyoung.learning.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by mazhiyu on 2016/11/12.
 */
public class QueueLearning {

    public static void main(String[] args) {
        BlockingQueue queue1=new ArrayBlockingQueue(100);//基于数组实现的一个阻塞队列
        BlockingQueue queue2=new LinkedBlockingQueue();//基于链表实现的一个阻塞队列，在创建LinkedBlockingQueue对象时如果不指定容量大小，则默认大小为Integer.MAX_VALUE
        BlockingQueue queue3=new PriorityBlockingQueue();//无界阻塞队列


        Queue queue4=new ConcurrentLinkedDeque();//

        queue1.add("1");
        queue1.remove();//Retrieves and removes the head of this queue
        queue1.offer("1");//return exception when can't insert
        queue1.poll();//remove and return exception when no element to remove.


        //对于非阻塞队列，一般情况下建议使用offer、poll和peek三个方法，不建议使用add和remove方法。
        try {
            queue1.put("2");//向队尾存入元素，如果队列满，则等待；
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            queue1.take();//用来从队首取元素，如果队列为空，则等待；
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            queue1.offer("2",1000,TimeUnit.MILLISECONDS);//向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            queue1.poll(1000, TimeUnit.MILLISECONDS);//从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queue2.offer("1");
        queue2.offer("2");
        for(int i=0;i<10;i++){
            queue2.element();
        }
        for(int i=0;i<10;i++){
            try {
                queue2.poll(1000,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
