package com.marsyoung.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgorithmBase {

	List<Integer> disorderList;
	
	
	/**
	 * 用来生成数据
	 * @param n  生成的list包含元素个数
	 * @param max  最大值
	 */
	public void initDisorderList(int n,int max){
		disorderList=new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			disorderList.add(new Random().nextInt(max));
		}
	}
	
	/**
	 * 用来输出数据
	 * @param list
	 */
	public void printList(List<Integer> list){
		for (Integer i : list) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		AlgorithmBase ab=new AlgorithmBase();
		ab.initDisorderList(100, 100);
		for(Integer i:ab.disorderList){
			System.out.println(i);
		}
	}
}
