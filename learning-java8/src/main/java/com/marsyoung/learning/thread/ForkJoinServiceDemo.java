package com.marsyoung.learning.thread;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinServiceDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		final long[] array = { 1l, 2l, 3l, 4l };
		@SuppressWarnings("rawtypes")
		ForkJoinTask sort = new SortTask(array);
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.submit(sort);//将 ForkJoinTask 类的对象提交给 ForkJoinPool，ForkJoinPool 将立刻开始执行 ForkJoinTask。
		fjpool.shutdown();
		fjpool.awaitTermination(30, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。

	}

	static class SortTask extends RecursiveAction {

		private static final long serialVersionUID = 3608669709106550320L;
		final long[] array;
		final int lo;
		final int hi;
		private int THRESHOLD = 30;

		@Override
		protected void compute() {
			if (hi - lo < THRESHOLD) {
				sequentiallySort(array, lo, hi);
			} else {
				int pivot = partition(array, lo, hi);
				// coInvoke(new SortTask(array, lo, pivot - 1), new
				// SortTask(array, pivot + 1, hi));// RecursiveAction 提供的方法
				// coInvoke()。它表示：启动所有的任务，并在所有任务都正常结束后返回。如果其中一个任务出现异常，则其它所有的任务都取消。coInvoke()
				// 的参数还可以是任务的数组。
				new SortTask(array, lo, pivot - 1).compute();
				new SortTask(array, pivot + 1, hi).compute();
			}
		}

		public SortTask(long[] array, int lo, int hi) {
			super();
			this.array = array;
			this.lo = lo;
			this.hi = hi;
		}

		public SortTask(long[] array) {
			super();
			this.array = array;
			this.lo = 0;
			this.hi = array.length - 1;
		}

		private int partition(long[] array, int lo, int hi) {
			long x = array[hi];
			int i = lo - 1;
			for (int j = lo; j < hi; j++) {
				if (array[j] <= x) {
					i++;
					swap(array, i, j);
				}
			}
			swap(array, i + 1, hi);
			return i + 1;
		}

		private void swap(long[] array, int i, int j) {
			if (i != j) {
				long temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		private void sequentiallySort(long[] array, int lo, int hi) {
			Arrays.sort(array, lo, hi + 1);
		}

	}

}
