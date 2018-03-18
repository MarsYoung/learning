package com.marsyoung.jvm.heap;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author zhiyuma
 * java中的强引用，软引用，弱引用，虚引用
 */
public class Reference {

	public void strongReference() {
		Object object = new Object();
		Object o[] = new Object[1000];
		System.out.println(object+""+o[0]);
	}

	public void softReference() {
		SoftReference<String> sr = new SoftReference<String>(new String("hello"));
		System.out.println(sr);
	}

	public void weakReference() {
		WeakReference<String> wr = new WeakReference<String>(new String("weakReference"));
		System.out.println(wr.get());
		System.gc();
		System.out.println(wr.get());
	}

	public void phantomReference() {
		ReferenceQueue<String> queue = new ReferenceQueue<String>();
		PhantomReference<String> pr = new PhantomReference<String>(new String("phantomReference"), queue);
		System.out.println(pr);
	}

}
