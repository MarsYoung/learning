package com.marsyoung.learning.web.resteasy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.marsyoung.learning.web.resteasy.entity.User;


@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class RestDemo {

	@Path("/getUser")
	public Map<String,Object> getUser(){
		User u=new User();
		Map<String,Object> m= new HashMap<String,Object>();
		m.put("user", u);
		return m;
	}
	
}
