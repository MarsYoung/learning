package com.marsyoung.value;

public abstract class Human {

    protected int occupation;

    public int getOccupation(){
        return occupation;
    }

    public void doWork() {
        System.out.println("occupation is " + occupation);
        System.out.println(getOccupation());
    }

}
