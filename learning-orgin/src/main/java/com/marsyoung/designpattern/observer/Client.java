package com.marsyoung.designpattern.observer;

public class Client {

	public static void main(String[] args) {
		Subject sub = new ConcreteSubject();
		sub.addObserver(new ConcreteObserver1());
		sub.addObserver(new ConcreteObserver2());
		sub.doSomething();
	}
}
