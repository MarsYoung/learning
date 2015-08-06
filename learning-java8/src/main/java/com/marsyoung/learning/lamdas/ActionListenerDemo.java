package com.marsyoung.learning.lamdas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * 实现事件处理
 * @author zhiyuma
 *
 */
public class ActionListenerDemo {

	public void java7(){
		JButton show=new JButton("Show");
		show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("before java8");
			}
		});
	}
	
	public void java8(){
		JButton show =new JButton();
		show.addActionListener((e)->{System.out.println("In java8");});
	}
	
}
