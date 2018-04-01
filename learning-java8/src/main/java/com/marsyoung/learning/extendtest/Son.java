package com.marsyoung.learning.extendtest;

public class Son extends Father{

    private int age=10;

    @Override
    protected int getMyAge() {
        return age;
    }

    public void saySomeThing(){
//        tellYourAge();
        super.tellYourAge();
    }

    public static void main(String[] args) {
        Son son=new Son();
        son.saySomeThing();
        try {
            Class<?> _class=Class.forName("com.marsyoung.learning.extendtest.Son");
            ((Son)_class.newInstance()).saySomeThing();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
