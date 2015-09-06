package com.marsyoung.jvm.heap;

/**
 * @author zhiyuma 【分析】 参考如下： (MaxProcessMemory - JVMMemory - ReservedOsMemory)
 *         / (ThreadStackSize) = Number of threads MaxProcessMemory 指的是一个进程的最大内存
 *         JVMMemory JVM内存 ReservedOsMemory 保留的操作系统内存 ThreadStackSize 线程栈的大小
 *         如果JVM内存调的过大或者可利用率小于20%，可以建议将heap及perm的最大值下调，并将线程栈调小，即-Xss调小，如：-
 *         Xss128k 【解决方法】 在JVM内存不能调小的前提下，将-Xss设置较小，如：-Xss:128k
 */
public class OOMForNativeThreadCreated {

}
