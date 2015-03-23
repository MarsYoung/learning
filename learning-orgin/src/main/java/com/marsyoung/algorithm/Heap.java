package com.marsyoung.algorithm;

public class Heap {

	Integer value;
	Heap left;
	Heap right;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Heap getLeft() {
		return left;
	}

	public void setLeft(Heap left) {
		this.left = left;
	}

	public Heap getRight() {
		return right;
	}

	public void setRight(Heap right) {
		this.right = right;
	}

}
