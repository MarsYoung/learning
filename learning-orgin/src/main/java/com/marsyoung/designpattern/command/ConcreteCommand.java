package com.marsyoung.designpattern.command;

public class ConcreteCommand extends Command{
	private Receiver receiver;

	public ConcreteCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}
	
	@Override
	public void execute() {
		this.receiver.doSomething();
	}
}
