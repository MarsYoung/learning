package com.marsyoung.learning.web.ssb.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zhiyuma 
 * 
 * Action实现方式3： 
 * 
 * 定义一个普通类的同时定义一个excute方法。
 * 
 */
@Controller
@Scope("prototype")
public class DemoAction3 {

	public DemoAction3() {
		super();
	}

	public void execute() {
		System.out.println("hello");
	}

}
