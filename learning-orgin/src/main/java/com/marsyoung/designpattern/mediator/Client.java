package com.marsyoung.designpattern.mediator;

public class Client {
	public static void main(String[] args) {
		AbstractColleague collA=new ColleagueA();
		AbstractColleague collB=new ColleagueB();
		AbstractMediator am=new Mediator(collA,collB);

		collA.setNumber(1000,am);
		
		System.out.println(collA.getNumber());
		System.out.println(collB.getNumber());
		
		collB.setNumber(1000,am);
		System.out.println(collB.getNumber());
		System.out.println(collA.getNumber());
		
	}
}
