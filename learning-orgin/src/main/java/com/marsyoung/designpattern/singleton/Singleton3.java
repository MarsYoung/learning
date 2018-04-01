package com.marsyoung.designpattern.singleton;
/*
 * 这种方法会在类加载的时候就实例化，可以保证多线程的使用，但是该单例不需要加载的时候就加载，无疑浪费了资源。
 * 这种方式利用了classloder的机制来保证初始化instance时只有一个线程
 * */
public class Singleton3 {

	private static Singleton3 instance = new Singleton3();

	private Singleton3() {
	};

	public static Singleton3 getInstance() {
		return instance;
	}

}
