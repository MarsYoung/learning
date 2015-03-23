package com.marsyoung.algorithm;

import java.util.List;

/**
 * @author Mars
 *         堆的定义：(1)堆是一个完全二叉树(2)根结点的值或者大于左右子树的值或者小于左右子树的值(3)左右子树也是一个堆
 *         叶子节点就是树中最底端的节点，叶子节点没有子节点。
 *         非叶子结点，就是非  叶子节点。
 */
public class HeapSort extends AlgorithmBase {

	/**
	 * 调整堆，其过程就是 比较节点和他所对应的左孩子和右孩子的大小， 如果满足条件，要交换，交换后要对被交换的节点再进行 调整堆（这是一个递归的过程。）
	 * 
	 * @param list
	 * @param i 节点位置
	 * @param size 结束位置
	 */
	public void heapAdjust(List<Integer> list, int heapPosition, int endPosition) {
		int lchild = 2 * heapPosition + 1;
		int rchild = 2 * heapPosition + 2;
		int max = heapPosition;
		//判断 堆的位置和无序队列结束位置 满足条件 
		//假设，有3个数字，那么堆的位置为0,结束位置为2 2/2=1 >=0;假设有2个数字，那么堆的位置为0 1/2=0 >=0;
		if (heapPosition <= endPosition / 2) {
			if (lchild <= endPosition && list.get(lchild) > list.get(max)) {
				max = lchild;
			}
			if (rchild <= endPosition && list.get(rchild) > list.get(max)) {
				max = rchild;
			}
			if (max != heapPosition) {
				// 交换
				int temp = list.get(max);
				list.set(max, list.get(heapPosition));
				list.set(heapPosition, temp);
				heapAdjust(list, max, endPosition);
			}
		}
	}

	/**
	 * 建堆，其过程就是从最后一个非叶子节点开始，执行调整堆操作。
	 * 
	 * @param list
	 * @param size
	 */
	public void buildHeap(List<Integer> list, int size) {
		// size/2-1为最后一个非叶子节点 例如，有3个数字时，那么非叶子节点的位置应该为0,有4个数字时，最后一个非叶子节点的位置为1. 当有5个数字时，最后一个非叶子结点的位置为1.
		for (int i = size / 2 - 1; i >= 0; i--) {
			heapAdjust(list, i, size - 1);
		}
	}

	/**
	 * 堆排序的全部过程
	 * 堆排序包括两个步骤
	 * (1)初始堆
	 * (2)调整堆(当初始小顶堆之后，堆顶元素是最小的元素，取出最小的元素与最后一个元素相交换，再把剩下n-1个元素调整成堆,依次调整直到1为止)
	 * 
	 * @param list
	 */
	public void heapSort(List<Integer> list) {
		int size = list.size();
		buildHeap(list, size);
		printList(list);
		for (int i = size - 1; i >= 0; i--) {
			// 交换堆顶和最后一个元素，即每次将剩余元素中的最大者放到最后面，每次交换都有一个有序的值被沉淀到了最后
			int temp = list.get(0);
			list.set(0, list.get(i));
			list.set(i, temp);
			// 调整剩余元素组成的堆
			heapAdjust(list, 0, i-1);
			printList(list);
		}
	}
	
	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		hs.initDisorderList(10, 100);
		hs.printList(hs.disorderList);
		hs.heapSort(hs.disorderList);
		hs.printList(hs.disorderList);
	}
}
