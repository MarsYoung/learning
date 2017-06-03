package com.marsyoung.learning.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mazhiyu on 2017/2/14.
 */
public class ExtendsAndSuper {

    public static void main(String[] args) {
       List<? extends A> list1=new ArrayList<A>();
        //list1.add(new A());
        A a=list1.get(0);
        List<? extends A> list2=new ArrayList<B>();
        List<? extends B> list3=new ArrayList<B>();
        //list3.add(new B());
        B b=list3.get(0);
        List<? super B> list4=new ArrayList<>();
        //list4.add(new B());
    }

    class A{}
    class B extends A{}
}
