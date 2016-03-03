package com.marsyoung.learning.io;

import java.io.File;

public class TestFile {

	
	public static void main(String[] args) {
		File f=new File("a.txt");
		System.out.println(f.exists());
		System.out.println(f.canRead());
		System.out.println(f.length());
		System.out.println(f.getAbsolutePath());
		System.out.println();
		
	}
}
