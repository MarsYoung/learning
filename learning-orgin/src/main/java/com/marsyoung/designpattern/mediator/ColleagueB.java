package com.marsyoung.designpattern.mediator;

public class ColleagueB extends AbstractColleague {

	@Override
	public void setNumber(int number, AbstractMediator am) {
		this.number = number;
		am.BaffectA();
	}
}
