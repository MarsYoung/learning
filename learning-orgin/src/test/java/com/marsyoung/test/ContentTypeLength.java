package com.marsyoung.test;

import java.io.UnsupportedEncodingException;

public class ContentTypeLength {

	// 采用ISO8859-1编码方式时，一个中/英文都只占一个字节；采用GB2312或GBK编码方式时
	// ，一个中文占两个字节；而采用UTF-8编码方式时，一个中文占三个字节。
	public static void main(String[] args) throws UnsupportedEncodingException {
		// 运行结果：3
		System.out.println("马志宇".getBytes("ISO8859-1").length);
		// 运行结果：6
		System.out.println("马志宇".getBytes("GB2312").length);
		// 运行结果：6
		System.out.println("马志宇".getBytes("GBK").length);
		// 运行结果：9
		System.out.println("马志宇".getBytes("UTF-8").length);
		// 运行结果：3
		System.out.println("马志宇".length());
		// 运行结果：3
		System.out.println(new String("马志宇".getBytes("UTF-8")).length());
		// 运行结果：3
		System.out.println(new String("马志宇".getBytes("UTF-8"), "UTF-8").length());
	}

}
