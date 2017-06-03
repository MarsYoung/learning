package com.marsyoung.transation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhiyu on 16/7/15.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        MyService myService=applicationContext.getBean("myService",MyService.class);

        try {
            myService.doSomeThing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            myService.doSomeThing2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            myService.doSomeThing3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
