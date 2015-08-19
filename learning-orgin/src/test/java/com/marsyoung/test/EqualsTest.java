package com.marsyoung.test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class EqualsTest {

	public static void main(String[] args) {
		Long a=new Long(1);
		Long b=new Long(1);
		System.out.println(a==b);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("a", a);
		map.put("b", b);
		System.out.println((Long)map.get("a")==(Long)map.get("b"));
		System.out.println((long)map.get("a")==(long)map.get("b"));
	
		long c= 1;
		long d =1;
		map.put("c", c);
		map.put("d", d);
		System.out.println((Long)map.get("c")==(Long)map.get("d"));
		System.out.println((long)map.get("c")==(long)map.get("d"));

		JSONObject jo=new JSONObject();
		jo.put("a", a);
		jo.put("b", b);
		System.out.println((Long)jo.get("a")==(Long)jo.get("b"));
		System.out.println((long)jo.get("a")==(long)jo.get("b"));
	
		
		jo.put("c", c);
		jo.put("d", d);
		System.out.println((Long)jo.get("c")==(Long)jo.get("d"));
		System.out.println((long)jo.get("c")==(long)jo.get("d"));

	}
}
