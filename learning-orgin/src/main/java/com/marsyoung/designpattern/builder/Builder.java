package com.marsyoung.designpattern.builder;

abstract class Builder {

	public abstract void setPart(String arg1,String arg2);
	public abstract Product getProduct();
}
