package com.marsyoung.designpattern.visitor;

public class Visitor implements IVisitor{

	public void visit(ConcreteElement1 el1) {
		el1.doSomething();
		
	}

	public void visit(ConcreteElement2 el2) {
		el2.doSomething();
	}
	
}
