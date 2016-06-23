package com.marsyoung.learning.reflect;

public class O {

	private String oName;

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}
	
	public O() {
		super();
	}

	public O(String oName) {
		super();
		this.oName = oName;
	}
	
}
