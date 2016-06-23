package com.marsyoung.json;

import org.json.JSONObject;

public class JSONTest {

	public static void main(String[] args) {
		JSONObject jo=new JSONObject();
		jo.put("sdfsf", "dfsdf");
		jo.put("sdfds", false);
		jo.put("sdsfdsfdsfd", 1);
		System.out.println(jo.toString());
	}
}
