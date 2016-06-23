package com.marsyoung.designpattern.visitor;

public class ConcreteElement1 extends Element {

	public void doSomething() {
		System.out.println("这时元素1");
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
