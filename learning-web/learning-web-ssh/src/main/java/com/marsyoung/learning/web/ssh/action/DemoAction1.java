package com.marsyoung.learning.web.ssh.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.marsyoung.learning.web.ssh.entity.User;
import com.marsyoung.learning.web.ssh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhiyuma
 * 
 * Action实现方式1： 
 * 
 * 继承自ActionSupport类。
 * 
 */
@Controller
@Scope("prototype")
public class DemoAction1 extends ActionSupport{

	private static final long serialVersionUID = 524844259348767473L;

	@Autowired
	UserService userService;
	
	public User user;
	public String resp;
	
	public String addUser(){
		if(user==null){
			resp="用户为空";
			return INPUT;
		}else{
			resp="添加成功";
			userService.addUser(user);
		}
		
		return SUCCESS;
	}
	
	public String deleteUser(){
		if(user==null||user.id==0l){
			return INPUT;
		}else{
			userService.deleteUser(user.id);
		}
		return SUCCESS;
	}
	
	public String selectUser(){
		User u=null;
		if(user==null||user.id==0l){
			return INPUT;
		}else{
			u=userService.selectUser(user.id);
		}
		setUser(u);
		return SUCCESS;
	}
	
	public String updateUser(){
		if(user==null){
			return INPUT;
		}else{
			userService.updateUser(user);
		}
		return SUCCESS;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	
	public static void main(String[] args) {
		ApplicationContext appContext=new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
		DemoAction1 demoAction1=appContext.getBean("demoAction1",DemoAction1.class);
		User user=new User();
		user.setUsername("marsyoung");
		demoAction1.setUser(user);
		System.out.println(demoAction1.getUser().getUsername());
	}
}
