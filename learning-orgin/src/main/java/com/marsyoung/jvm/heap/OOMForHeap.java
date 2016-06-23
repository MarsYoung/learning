package com.marsyoung.jvm.heap;

/**
 * @author zhiyuma 【分析】 此OOM是由于JVM中heap的最大值不满足需要，将设置heap的最大值调高即可，参数样例为：-Xmx2G
 *         【解决方法】 调高heap的最大值，即-Xmx的值调大。
 */
public class OOMForHeap {

	public static void main(String[] args) {
		int[] array=new int[1024*1024*1024];
		System.out.println(array);
	}
}

