package com.marsyoung.designpattern.singleton;

/**
 * @author Mars
 * 单例模式，这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。
 * 这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象
 *  这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
 */
public class Singleton1 {

	private static Singleton1 instance;

	private Singleton1() {
	};

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}
