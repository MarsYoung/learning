package com.marsyoung.designpattern.builder;

public class ConcreteBuilder extends Builder {

	private Product product = new Product();

	public Product getProduct() {
		return product;
	}

	@Override
	public void setPart(String arg1, String arg2) {
		product.setName(arg1);
		product.setType(arg2);
	}
}
