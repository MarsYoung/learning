package com.marsyoung.learning.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by mazhiyu on 2017/2/22.
 */
public class LogTest {
    Logger logger= LoggerFactory.getLogger(LogTest.class);

    public void nullpointer(){
        List a=null;
        System.out.println(a.size());
    }

    public void test(){
        try {
            nullpointer();
        } catch (Throwable e) {
            logger.error("",e);
        }
    }

    public static void main(String[] args) {
        new LogTest().test();;
    }
}
