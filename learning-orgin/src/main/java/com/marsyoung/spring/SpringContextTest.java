package com.marsyoung.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringContextTest {

	public static void main(String[] args) {
		new SpringContextTest().loadSpringContext();
		
	}
	
	@SuppressWarnings("resource")
	public void loadSpringContext(){
		new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml","spring/spring-redis.xml"});
	}
}
