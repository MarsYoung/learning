package com.marsyoung.learning.web.ssb.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.marsyoung.learning.web.ssb.entity.User;
import com.marsyoung.learning.web.ssb.service.UserService;
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
public class DemoAction1 extends ActionSupport{

	private static final long serialVersionUID = 524844259348767473L;

	@Autowired
	UserService userService;
	
	public User user;
	public String resp;
	
	
	
	
	public String addUser(){
		if(user==null){
			return INPUT;
		}else{
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
	
}
