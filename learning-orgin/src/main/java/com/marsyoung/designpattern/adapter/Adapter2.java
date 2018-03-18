package com.marsyoung.designpattern.adapter;

public class Adapter2 implements Target{
	private Adaptee adaptee;
	
	public Adapter2(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	public void request() {
		adaptee.specificRequest();
	}

}
