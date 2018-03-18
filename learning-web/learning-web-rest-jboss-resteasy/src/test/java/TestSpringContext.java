import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.marsyoung.learning.web.resteasy.controller.RestDemo;


public class TestSpringContext {

	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext appContext=new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
		RestDemo rest=appContext.getBean(RestDemo.class);
		System.out.println(rest.getUser());
	}
}
