package com.marsyoung.jvm.heap;

/**
 * @author zhiyuma
 *【分析】
 此OOM是由于JVM中perm的最大值不满足需要，将设置perm的最大值调高即可，参数样例为：-XX:MaxPermSize=512M
【解决方法】
调高heap的最大值，即-XX:MaxPermSize的值调大。
另外，注意一点，Perm一般是在JVM启动时加载类进来，如果是JVM运行较长一段时间而不是刚启动后溢出的话，
很有可能是由于运行时有类被动态加载进来，此时建议用CMS策略中的类卸载配置。
如：-XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled
 */
public class OOMForPerm {

}
