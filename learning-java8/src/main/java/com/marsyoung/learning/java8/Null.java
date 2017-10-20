package com.marsyoung.learning.java8;

/**
 * Created by mazhiyu on 2017/10/20.
 */
public class Null {

    public static void say(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Null x=null;
        x.say();
        ((Null)x).say();
        ((Null)null).say();
    }
}
