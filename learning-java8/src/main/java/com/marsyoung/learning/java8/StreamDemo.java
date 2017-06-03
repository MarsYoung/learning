package com.marsyoung.learning.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * 每个Stream都有两种模式：顺序执行和并行执行，其 能够利用多核处理器的优势，并可以使用 fork/join并行方式来拆分任务和加速处理过程。
 * 
 * @author zhiyuma
 *
 */
public class StreamDemo {

	
	public static void main(String[] args) {
		
		List<ClassMate> cl= new ArrayList<ClassMate>();
		Double totalScore=cl.stream().mapToDouble(f->f.getScore()).sum();
		System.out.println(totalScore);
		List<ClassMate> cmL=cl.stream().filter(f->f.getAge()>21).collect(Collectors.toList());
		cmL.forEach(n->{System.out.println(n);});
	}
}

/**
 * @author zhiyuma
 *
 */
class ClassMate{
	int no;
	int name;
	int age;
	int score;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
