package com.marsyoung.designpattern.singleton;

/*
 * 这种单例是我比较喜欢的，觉得在这么多中是最好的。
 * 没有在类load的时候就初始化实例，并且只在需要的时候进行了synchronized，而且只有一次。
 *
 * */
public class Singleton7 {

	private volatile static Singleton7 instance;

	private Singleton7() {
	};

	public static Singleton7 getInstance() {
		if (instance == null) {
			synchronized (Singleton7.class) {
				if (instance == null) {
					instance = new Singleton7();
				}
			}
		}
		return instance;

	}
}
