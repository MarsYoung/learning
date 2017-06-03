package com.marsyoung.learning.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OTest {

	private static Log log = LogFactory.getLog(OTest.class);

	public static void main(String[] args) {
		// Test1
		log.info("----------Test1---------");
		O o = new O();
		System.out.println(o.getClass().getName());

		// Test2
		log.info("----------Test2---------");
		Class<?> o1 = null;
		Class<?> o2 = null;
		Class<?> o3 = null;

		try {
			o1 = Class.forName("com.marsyoung.learning.reflect.O");
		} catch (ClassNotFoundException e) {
			log.error("error:", e);
		}

		o2 = new O().getClass();

		o3 = O.class;

		System.out.println("o1类名称 \t" + o1.getName());
		System.out.println("o2类名称 \t" + o2.getName());
		System.out.println("o3类名称 \t" + o3.getName());

		// Test3
		log.info("----------Test3---------");
		Class<?> oo = null;
		try {
			oo = Class.forName("com.marsyoung.learning.reflect.O");
		} catch (ClassNotFoundException e) {

		}

		O newO = null;
		try {
			newO = (O) oo.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("error:", e);
		}
		newO.setoName("marsyoung");
		System.out.println(newO.getoName());

		// Test4
		log.info("----------Test4---------");
		Constructor<?> cons[] = oo.getConstructors();
		//O newO1 = null;
		O newO2 = null;
		//O newO3 = null;
		try {
			newO2 = (O) cons[1].newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			log.error("error:", e);
		}
		newO2.toString();

	}
}
