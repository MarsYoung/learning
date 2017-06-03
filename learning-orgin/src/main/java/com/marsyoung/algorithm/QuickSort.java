package com.marsyoung.algorithm;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends AlgorithmBase {

	/**
	 * 快排的拆分比较方法
	 * @param list
	 * @param startPoint
	 * @param endPoint
	 * @return  拆分的位置
	 */
	public int sortUnit(List<Integer> list, int startPoint, int endPoint) {
		Integer pivot = list.get(startPoint);// 记录本次比较的中轴值，赋值给pivot
		endPoint++;
		while (startPoint < endPoint) {// 设置本次比较的结束条件，为循环过后开始指针和结束指针在同一个位置
			// 1.从结束指针开始比较
			// 这个地方可以减是由于最外层的循环刚判断完startPoint<endPoint
			endPoint--;
			// 开始指针减1
			while (startPoint < endPoint && list.get(endPoint) > pivot) {
				endPoint--;// 移动结束指针
			}
			// 不满足上面条件时，设置开始指针所指位置的值为结束指针所指的值
			list.set(startPoint, list.get(endPoint));
			if (startPoint < endPoint) {
				startPoint++;
			}
			// 2.然后换转方向继续比较
			while (startPoint < endPoint && list.get(startPoint) < pivot) {
				startPoint++;
			}
			// 不满足上面条件时，设置结束指针的位置为开始指针所指的值。
			list.set(endPoint, list.get(startPoint));
		}
		list.set(startPoint, pivot);
		// 结束时，startPoint和endPoint应该指向同一个点。
		return startPoint;
	}

	
	/**
	 * 快排递归方法
	 * @param list 要排序的列表
	 * @param start
	 * @param end
	 */
	public void quickSort(List<Integer> list, int start, int end) {
		if (start < end) {
			int middlePoint = sortUnit(list, start, end);
			// 2分思想，分别处理分成的两个list,middlePoint所在的值是不用比较的，这个可以叫做3分思想吧
			// 递归处理
			quickSort(list, start, middlePoint - 1);
			quickSort(list, middlePoint + 1, end);
		}
	}

	public void quickSort(List<Integer> list) {
		quickSort(list, 0, list.size() - 1);
	}

	/**
	 * 不计空间快排
	 * @param list
	 * @return
	 */
	public List<Integer> quickSort2(List<Integer> list) {
		if (list == null || list.size() < 2) {
			return list;
		} else {
			// 来个拆分
			int firstNumber = list.get(0);
			List<Integer> leftList = new ArrayList<Integer>();
			List<Integer> rightList = new ArrayList<Integer>();
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i) > firstNumber) {
					rightList.add(list.get(i));
				} else {
					leftList.add(list.get(i));
				}
			}
			// 对拆分的两个list进行递归排序
			rightList = quickSort2(rightList);
			leftList = quickSort2(leftList);
			// 拼成结果集
			List<Integer> resultList = new ArrayList<Integer>();
			for (Integer i : leftList) {
				resultList.add(i);
			}
			resultList.add(firstNumber);
			for (Integer i : rightList) {
				resultList.add(i);
			}
			return resultList;
		}
	}

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		qs.initDisorderList(5, 5);
		qs.printList(qs.disorderList);
		// qs.quickSort(qs.disorderList);
		qs.printList(qs.quickSort2(qs.disorderList));

	}
}
