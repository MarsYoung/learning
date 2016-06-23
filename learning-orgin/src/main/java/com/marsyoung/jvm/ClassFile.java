package com.marsyoung.jvm;

/**
 * @author zhiyuma
 * 通过jmap -heap观察 interned string的个数，发现每new 一个string或者=“xxx”都会增加一个interned string
 * 而。
 * 而在MyTHread这个类中循环new string的时候，interned string竟然不见增长。
 */
public class ClassFile {

	int a=1;//ConstantPool
	Integer b=2;//ConstantPool
	Integer c=new Integer(3);//JavaHeap Long是一个JavaObject
	Integer d=Integer.valueOf(4);//ConstantPool 看具体实现，绝对值小于等于128放在constantPool中
	String e="5";
	String f=new String("6");
	
	public void classMethod(){
		//基本测试
		System.out.println("ConstantPool中同一个value的两个值地址是否相同:");
		int aa=1;//ConstantPool
		System.out.println("a==aa:"+(a==aa));//
		System.out.println("ConstantPool中同一个value的两个值地址是否相同:");
		Integer bb=2;//ConstantPool
		System.out.println("b==bb:"+(b==bb));
		System.out.println("JavaHeap和JavaHeap中同一个value的两个值地址是否相同:");
		Integer cc=new Integer(3);//JavaHeap
		System.out.println("c=cc:"+(c==cc));
		System.out.println("JavaHeap和ConstantPool中同一个value的两个值地址是否相同:");
		Integer ccc=3;//ConstantPool
		System.out.println("c=ccc:"+(c==ccc));
		System.out.println("ConstantPool中同一个value的两个值地址是否相同:");
		Integer dd=4;//ConstantPool
		System.out.println("d=dd:"+(d==dd));
		
		
		String ee="5";
		System.out.println("e==ee:"+(e==ee));
		System.out.println("e==\"5\":"+(e=="5"));
		String eee=new String("5");
		System.out.println("e=eee:"+(e==eee));
		String xx="dsfdsf";
		System.out.println(xx);
	}
	
	public static void main(String[] args) {
		new ClassFile().classMethod();
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
