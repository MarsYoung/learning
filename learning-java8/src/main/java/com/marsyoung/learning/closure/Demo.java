package com.marsyoung.learning.closure;

/**
 * Created by mazhiyu on 2016/11/23.
 */

//在JAVA中，闭包是通过“接口+内部类”实现
public class Demo {

    private int length =0;

    private class InnerClass implements ILog{
        @Override
        public void write(String message) {
            length=message.length();
            System.out.println(length);
        }
    }
    interface ILog{
        void write(String message);
    }


    public ILog getLogger(){
        return new InnerClass();
    }

    public static void main(String[] args) {
        Demo demo=new Demo();
        demo.getLogger().write("hello");

        Demo2 demo2=new Demo2();
        demo2.getLogger().write("hello2");

        Demo3 demo3=new Demo3();
        demo3.getLogger().write("hello33");
    }


}

 class Demo2 {

    private int length =0;

    interface ILog{
        void write(String message);
    }

    public ILog getLogger(){
        class InnerClass implements ILog
        {
            public void write(String message) {
                length=message.length();
                System.out.println(length);
            }
        }
        return new InnerClass();
    }

}


class Demo3 {

    private int length =0;

    interface ILog{
        void write(String message);
    }


    public ILog getLogger(){
        return new ILog() {
            @Override
            public void write(String message) {
                length=message.length();
                System.out.println(length);
            }
        };
    }

}
