package com.marsyoung.learning.reflect;

/**
 * @author zhiyuma
 * 我们添加任意多个子类的时候，工厂类就不需要修改。
 */
public class ReflectFactory {

	/**
	 * 用了反射的工厂方法，增加新类时不需要修改
	 * @param className
	 * @return
	 */
	public static Animal getAnimal(String className){
		Animal a=null;
		try {
			a=(Animal)Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	/**
	 * 沒用反射的工厂方法，增加新类时需要修改
	 * @param className
	 * @return
	 */
	public static Animal getAnimalNoReflect(String className){
		Animal a=null;
		if("Pig".equals(className)){
			a=new Pig();
			
		}else if("Dog".equals(className)){
			a=new Dog();
		}
		return a;
	}
	
	/**
	 * Test Reflect Factory
	 * @param args
	 */
	public static void main(String[] args) {
		Animal a= ReflectFactory.getAnimal("com.marsyoung.learning.reflect.Dog");
		a.eat();
	}
	
}



interface Animal{ public void eat();}

class Dog implements Animal{

	@Override
	public void eat() {
		System.out.println("dog eat.");
	}
}

class Pig implements Animal{

	@Override
	public void eat() {
		System.out.println("pig eat.");
	}
}



