package com.marsyoung.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONToString {

	public static void main(String[] args) {
		String body="{\"aid\":1,\"app_id\":1,\"ip\":\"10.2.25.75\",\"passport\":\"test@sohu.com\",\"plat\":2,\"result\":true,\"time\":1448523643214,\"tvid\":3,\"url\":\"/check/permission\",\"vid\":2}";
		JSONObject jo=JSON.parseObject(body);
//		
//		JSONObject jo=new JSONObject();
//		jo.put("test", 1);
//		System.out.println(new String(jo.toString()));
//		
//		System.out.println(new String("\"test\":\"sdfdsfsdf\""));
//		String x="{\"cost-time\":10,\"request-id\":\"404\",\"entity\":\"{\\\"status\\\":200,\\\"statusText\\\":\\\"OK\\\",\\\"data\\\":{\\\"ignore\\\":{\\\"tvid\\\":[\\\"7774\\\"]}}}\",\"status\":200}";
//		System.out.println(x);
//		JSONObject j=JSONObject.parseObject(x);
//		System.out.println(j.toString());
//		String y="{\"cost-time\":10,\"request-id\":\"404\",\"entity\":{\"status\":200,\"statusText\":\"OK\",\"data\":{\"ignore\":{\"tvid\":[\"7774\"]}}},\"status\":200}";
//		JSONObject jy=JSONObject.parseObject(y);
//		System.out.println(jy.toString());
//		
//		
//		String o="{\"status\":200,\"statusText\":\"OK\",\"data\":{\"ignore\":{\"tvid\":[\"7774\"]}}}";
//		JSONObject result=new JSONObject();
//		result.put("entity", o);
//		System.out.println(result);
//		
//		JSONObject noBackSplashResult=new JSONObject();
//		noBackSplashResult.put("entity", new String(JSONObject.parseObject(o).toString()));
//		System.out.println(noBackSplashResult);
		
	}
	
}
