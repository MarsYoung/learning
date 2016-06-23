package com.marsyoung.algorithm;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class BloomFilter {

	private static final Integer DEFAULT_SIZE = 1 << 25;
	private static List<Integer> seeds = Arrays.asList(5, 7, 11, 13, 31, 37, 61);

	private BitSet bits = new BitSet(DEFAULT_SIZE);

	public void add(String value) {
		seeds.forEach(seed -> bits.set(hash(value, DEFAULT_SIZE, seed), true));
	}

	public Boolean contains(String value) {
		if (value == null) {
			return false;
		}
		boolean ret = true;
		for (Integer seed : seeds) {
			ret = ret && bits.get(hash(value, DEFAULT_SIZE, seed));
		}
		return ret;
	}

	public Integer hash(String value, int cap, int seed) {
		int result = 0;
		int len = value.length();
		for (int i = 0; i < len; i++) {
			result = seed * result + value.charAt(i);
		}
		return (cap - 1) & result;
	}

}
