package com.marsyoung.learning.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyProxy {

    private void byteBuddyDemo() throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        new ByteBuddyProxy().byteBuddyDemo();
    }
}
