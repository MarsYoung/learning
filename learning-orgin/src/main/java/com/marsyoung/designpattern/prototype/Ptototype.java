package com.marsyoung.designpattern.prototype;

/**
 * @author Mars
 *         Prototype类需要具备以下两个条件：
 *         实现Cloneable接口。
 *         重写Object类中的clone方法。
 */
public class Ptototype implements Cloneable {

	@Override
	public Ptototype clone() {
		Ptototype prototype = null;
		try {
			prototype = (Ptototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return prototype;
	}

}
