package com.marsyoung.number;

public class WeiYi {

	public static void main(String[] args) {
		WeiYi wy=new WeiYi();
		System.out.println(wy.convertIp(new int[]{182,92,64,185}));
	}

	private long convertIp(int[] array) {
		long gip = 0l;
		for (int i = 0; i < 4; ++i) {
			gip = gip << 8 | array[i];
		}
		return gip;
	}
}
