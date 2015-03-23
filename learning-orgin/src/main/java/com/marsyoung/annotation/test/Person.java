package com.marsyoung.annotation.test;

import org.springframework.stereotype.Component;

import com.marsyoung.annotation.VisitorRole;

@Component
public class Person {

	private String userName = "marsyoung";

	// 自定义注解的使用。只有具有ADMIN角色才能调用本方法。
	@VisitorRole("ADMIN")
	public String say() {
		return "I'm " + userName;
	}

}
