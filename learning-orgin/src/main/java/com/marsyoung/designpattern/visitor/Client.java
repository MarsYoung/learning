package com.marsyoung.designpattern.visitor;

import java.util.List;

public class Client {
	public static void main(String[] args) {
		List<Element> list=ObjectStructure.getList();
		for (Element e:list) {
			e.accept(new Visitor());
		}
	}
}
