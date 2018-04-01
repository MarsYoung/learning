package com.marsyoung.designpattern.adapter;
/*
 * 适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁
 * 举个真实的例子，读卡器是作为内存卡和笔记本之间的适配器。
 * 
 * 您将内存卡插入读卡器，再将读卡器插入笔记本，这样就可以通过笔记本来读取内存卡
 * */
public class Adaptee {

	public void specificRequest(){
		System.out.println("this is a special request");
	}
}
