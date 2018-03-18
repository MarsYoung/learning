package com.marsyoung.jvm.heap;

/**
 * @author zhiyuma 【分析】
 *         此OOM是由于JVM在GC时，对象过多，导致内存溢出，建议调整GC的策略，在一定比例下开始GC而不要使用默认的策略，
 *         或者将新代和老代设置合适的大小， 需要进行微调存活率。 【解决方法】
 *         改变GC策略，在老代80%时就是开始GC，并且将-XX:SurvivorRatio（-XX:SurvivorRatio=8）和-XX:
 *         NewRatio（-XX:NewRatio=4）设置的更合理。
 *         解决这种问题两种方法是，增加参数，-XX:-UseGCOverheadLimit，关闭这个特性，同时增加heap大小，-Xmx1024m。
 *         
 */
public class OOMForGC {

}
