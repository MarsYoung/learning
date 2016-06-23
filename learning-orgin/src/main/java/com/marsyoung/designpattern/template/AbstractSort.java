package com.marsyoung.designpattern.template;

public abstract class AbstractSort {

	protected abstract void sort(int[] array);

	public void showSortResult(int[] array) {
		this.sort(array);
		System.out.println("排序结果：");
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%3s", array[i]);
		}
	}
}
