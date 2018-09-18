package com.marsyoung.learning.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*
* net.sf.cglib.proxy.MethodInterceptor
*
* cglib,它广泛的被许多AOP的框架使用，例如Spring AOP和dynaop，为他们提供方法的interception（拦截）。
* 最流行的OR Mapping工具hibernate也使用CGLIB来代理单端single-ended(多对一和一对一)关联（对集合的延迟抓取，是采用其他机制实现的）。
* EasyMock和jMock是通过使用模仿（moke）对象来测试java代码的包。
* 它们都通过使用CGLIB来为那些没有接口的类创建模仿（moke）对象。[现在cglib好像不怎么维护了，javassist比较火爆]
* */
public class CglibProxy {

    private static MarsInterface createCglibDynamicProxy(final MarsInterface delegate) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setInterfaces(new Class[] { MarsInterface.class });
        MarsInterface cglibProxy = (MarsInterface) enhancer.create();
        return cglibProxy;
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        CglibInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        public Object intercept(Object object, Method method, Object[] objects,
                                MethodProxy methodProxy) throws Throwable {
            return methodProxy.invoke(delegate, objects);
        }
    }

    public static void main(String[] args) {

    }

}
