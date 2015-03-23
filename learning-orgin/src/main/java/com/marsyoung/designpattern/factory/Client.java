package com.marsyoung.designpattern.factory;

public class Client {

	public static void main(String[] args) {
		IFactory factory = new Factory();
		factory.createProduct1().show();
		factory.createProduct2().show();
	}
}
