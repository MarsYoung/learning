package com.marsyoung.learning.concurent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Created by mazhiyu on 2017/5/4.
 */
public class BlockingQueueLearn {

    public static void main(String[] args) {
        BlockingQueue queue1=new ArrayBlockingQueue(97);
        BlockingQueue queue2=new DelayQueue();
    }

}
