package com.marsyoung.designpattern.observer;

public class ConcreteSubject extends Subject {

	public void doSomething() {
		System.out.println("被观察者时间发生");
		this.notifyObserver();
	}

}
