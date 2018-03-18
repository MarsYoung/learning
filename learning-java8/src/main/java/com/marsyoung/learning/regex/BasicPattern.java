package com.marsyoung.learning.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicPattern {

	public static final String start = "^";// 限制开头
	public static final String end = "$";// 限制结尾

	public static final String number = "\\d";// 数字
	public static final String charactar = "\\w";// 单独字符,可以是数字
	public static final String blank = "\\s";

	public static final String not_number = "\\D";// 非数字
	public static final String not_charactar = "\\W";// 非单独字符
	public static final String not_blank = "\\S";

	public static final String change_line = "\\n";
	public static final String enter = "\\r";

	public static final String a_z = "[a-z]";// a到z中的任意一个
	public static final String not_a_z = "[^a-z]";// 非a-z中的任何一个

	public static final String anything2 = ".*";// 任意多个字符，可以为0个
	public static final String biggerThan0 = ".+";// 任意多个字符，必须大于0个
	public static final String biggerThan3 = ".{3,}";// 任意字符出现大于3次
	public static final String biggerThan3LessThan5 = ".{3,5}";// 任意字符出现大于3次，小于5次

	public static final String or="3|5";//3或者5
	public static final String group ="([a-z])";
	
	public void demo(){
		//验证是否为单个字符
		{
			String str="A";
			Pattern pattern = Pattern.compile("\\w");
			Matcher matcher = pattern.matcher(str);
			System.out.println(matcher.matches());
		}
		
		//查找以136开头，任意结尾的字符串
		{
		Pattern pattern =Pattern.compile("^136.*");
		Matcher matcher =pattern.matcher("13672078678");
		System.out.println(matcher.matches());
		}
		//验证是否为邮箱地址
		{
			String str="bigmazhiyu@126.com";
			Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$",Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(str);
			System.out.println(matcher.matches());
			//分析
			// \\@是@的转义
			// \\.是.的转义
			// \\-是-的转义
			// ^()+ 表示以什么开头并且重复多次
			// [a-zA-Z0-9_\\.\\-] 表示a-z、A-Z、0-9、_、.、- 中的任何一个
		}
		//验证是否为手机号
		{
			String str="13672078678";
			Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(147)|(145)|(18[0-9])|(17[0-9]))\\d{8}$");
			Matcher matcher = pattern.matcher(str);
			System.out.println(matcher.matches());
			//分析
			//匹配13开头、15开头、147开头、145开头、18开头、17开头的手机号
			//手机号11位，前3位进行匹配，后8为均为数字
		}
		
		//匹配一串数字
		{
			String str="21sdfds454";
			Pattern pattern =Pattern.compile("^\\d*");
			Matcher matcher=pattern.matcher(str);
			System.out.println(matcher.matches()==false);
		}
		
	}
	
	public static void main(String[] args) {
		BasicPattern t=new BasicPattern();
		t.demo();
	}
}
