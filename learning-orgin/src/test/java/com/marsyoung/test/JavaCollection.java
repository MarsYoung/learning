package com.marsyoung.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class JavaCollection {

	Set<?> set1 = new HashSet<>();
	HashSet<?> set2 = new LinkedHashSet<>();
	Set<?> set3 = new TreeSet<>();
	SortedSet<?> set4 = new TreeSet<>();// SortedSet是个接口，它里面的（只有TreeSet这一个实现可用）中的元素一定是有序的。

	
	List<?> list = new Vector<>();//Vector是线程同步的
	List<?> list1 = new ArrayList<>();//性能上要比Vector好一些,但不同步
	List<?> list2 = new LinkedList<>();//LinkedList不同于前面两种List，它不是基于数组的，所以不受数组性能的限制。
	List<?> list3 =new Stack<>();
	Map<?,?> map =new HashMap<Integer,String>();
	Map<?,?> map1 =new TreeMap<Integer,String>();//SortedMap
	Map<?,?> map2 =new LinkedHashMap<Integer,String>();
	
	Hashtable<Integer, String> hb=new Hashtable<>();
	
	
	//test array 
	int capacity=16;
	String[] x=new String[capacity];
	
	class KeyValue<K,V> implements Map.Entry<K, V>{
		final K key;
        V value;
        KeyValue<K,V> next;
        final int hash;
        
        KeyValue(int h, K k, V v, KeyValue<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V newValue) {
			V oldValue=value;
			value= newValue;
			return oldValue;
		}
	}
	
	@SuppressWarnings("rawtypes")
	KeyValue[] kvArray=new KeyValue[capacity];
	
	
}