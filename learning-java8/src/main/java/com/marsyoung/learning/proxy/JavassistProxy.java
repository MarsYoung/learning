package com.marsyoung.learning.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;
/*
*
* javassist,它是一个开源的分析、编辑和创建Java字节码的类库。
* 是由东京工业大学的数学和计算机科学系的 Shigeru Chiba （千叶 滋）所创建的。
* 它已加入了开放源代码JBoss 应用服务器项目,通过使用Javassist对字节码操作为JBoss实现动态AOP框架。
* javassist是jboss的一个子项目，其主要的优点，在于简单，而且快速。
* 直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类。
* */
public class JavassistProxy {

    private static MarsInterface createJavassistDynamicProxy(final MarsInterface delegate) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[] { MarsInterface.class });
        Class<?> proxyClass = proxyFactory.createClass();
        MarsInterface javassistProxy = (MarsInterface) proxyClass.newInstance();
        ((ProxyObject) javassistProxy).setHandler(new JavaAssitInterceptor(delegate));
        return javassistProxy;
    }

    private static class JavaAssitInterceptor implements MethodHandler {

        final Object delegate;

        JavaAssitInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object self, Method m, Method proceed,
                             Object[] args) throws Throwable {
            return m.invoke(delegate, args);
        }
    }
}
