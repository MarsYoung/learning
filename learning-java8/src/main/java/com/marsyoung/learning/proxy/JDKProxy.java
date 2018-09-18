package com.marsyoung.learning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* JDK 动态代理
* java.lang.reflect.InvocationHandler
*
* jdk (jdk动态代理根据interface,生成的代理类会被缓存，每个接口只会生成一个代理类)
* */

public class JDKProxy {

    private static MarsInterface createJdkDynamicProxy(final MarsInterface delegate) {
        MarsInterface jdkProxy = (MarsInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{MarsInterface.class}, new JdkHandler(delegate));
        return jdkProxy;
    }

    private static class JdkHandler implements InvocationHandler {

        final Object delegate;

        JdkHandler(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object object, Method method, Object[] objects)
                throws Throwable {
            return method.invoke(delegate, objects);
        }
    }
}
