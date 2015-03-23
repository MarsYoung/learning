package com.marsyoung.designpattern.singleton;

/**
 * @author Mars
 *         和第三种方式差不多，都是在类初始化即实例化instance。
 */
public class Singleton4 {

	private static Singleton4 instance = null;
	static {
		instance = new Singleton4();
	}

	private Singleton4() {
	};

	public static Singleton4 getInstance() {
		return instance;
	}
}
