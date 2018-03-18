package com.marsyoung.designpattern.template;

public class ConcreteSort extends AbstractSort {

	@Override
	protected void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			selectSort(array, i);
		}

	}

	private void selectSort(int[] array, int index) {
		int MinValue = 32767;
		int indexMin = 0;
		int Temp;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < MinValue) {
				MinValue = array[i];
				indexMin = i;
			}
		}
		Temp = array[index];
		array[index] = array[indexMin];
		array[indexMin] = Temp;
	}
}
