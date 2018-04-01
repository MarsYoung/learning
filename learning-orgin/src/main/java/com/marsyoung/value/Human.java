package com.marsyoung.value;

public abstract class Human {

    int occupation;

    public void doWork(){

        if(occupation==1){
            System.out.println("i am 1");
        }else if(occupation==2){
            System.out.println("2");
        }else{
            System.out.println("not recognize");
        }
    }

}
