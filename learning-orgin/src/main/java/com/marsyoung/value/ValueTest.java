package com.marsyoung.value;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/*
*
* */

public class ValueTest {

    public static void main(String[] args) {
        ApplicationContext x= new ClassPathXmlApplicationContext("spring/spring.xml");
        Map<String,Human> beans= x.getBeansOfType(Human.class);
        beans.entrySet().forEach(
                bean->{
                    System.out.println(bean.getKey());
                    bean.getValue().doWork();
                }
        );
        System.out.println("start test for new a class");
        Doctor doctor =new Doctor();
        doctor.doWork();
    }

}
