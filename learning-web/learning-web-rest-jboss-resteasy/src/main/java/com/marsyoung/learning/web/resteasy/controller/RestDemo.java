package com.marsyoung.learning.web.resteasy.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.marsyoung.learning.web.resteasy.entity.User;

@Path("/demo")
@Controller
public class RestDemo {

	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		User u = new User();
		u.setUsername("jboss-resteasy");
		u.setSex(true);
		return u;
	}

	@GET
	@Path("/createUser")
	@Produces(MediaType.APPLICATION_XML)
	public User createUser() {
		return new User();
	}

}
