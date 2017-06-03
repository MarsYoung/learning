package com.marsyoung.jvm.heap;

/**
 * @author zhiyuma 
 * 
 * 【分析】
 *         此类信息表明应用程序（或者被应用程序调用的APIs）试图分配一个大于堆大小的数组。例如，如果应用程序new一个数组对象，大小为512M，
 *         但是最大堆大小为256M，因此OutOfMemoryError会抛出，因为数组的大小超过虚拟机的限制。 【解决方法】
 *         （1）、首先检查heap的-Xmx是不是设置的过小
 *         （2）、如果heap的-Xmx已经足够大，那么请检查应用程序是不是存在bug，例如：应用程序可能在计算数组的大小时，存在算法错误，
 *         导致数组的size很大，从而导致巨大的数组被分配。
 *         
 *         参考：
 *         http://it.deepinmind.com/gc/2014/05/18/outofmemoryerror-on-overprovisioned-heap.html
 */
public class OOMForAllocateHugeArray {


}
