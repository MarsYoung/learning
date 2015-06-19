package com.marsyoung.learning.web.resteasy.runner;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application; 

import com.marsyoung.learning.web.resteasy.controller.RestDemo;
public class MarsYoungApplication extends Application{
		private Set<Object> singletons = new HashSet<Object>();
		private Set<Class<?>> classes = new HashSet<Class<?>>();

		public MarsYoungApplication() {
//			classes.add(UserServlet.class);
			singletons.add(new RestDemo());
		}

		@Override
		public Set<Class<?>> getClasses() {
			return classes;
		}

		@Override
		public Set<Object> getSingletons() {
			return singletons;
		}
}
