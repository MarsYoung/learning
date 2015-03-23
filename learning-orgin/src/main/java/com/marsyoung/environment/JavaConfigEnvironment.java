package com.marsyoung.environment;

import java.util.Map;
import java.util.Properties;

public class JavaConfigEnvironment {

	public static void main(String[] args) {
		Properties p = System.getProperties();
		for (Map.Entry<Object, Object> entry : p.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
}
