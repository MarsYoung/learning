package com.marsyoung.designpattern.template;
/*
 * Note：
 * 模板模式，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。
 * eg：hibernate中关于session的管理。mx-job中关于不同job共有方法的处理。
 * 下面例子中，不同的排序有不同的实现，但是在
 * */
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
