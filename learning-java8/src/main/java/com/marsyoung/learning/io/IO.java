package com.marsyoung.learning.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IO {

	public static void main(String[] args) throws IOException {
		
		InputStream inputstream = new FileInputStream("a.txt");

		int data = inputstream.read();
		while(data != -1) {
		  //do something with data...
		  System.out.println(data);

		  data = inputstream.read();
		}
		inputstream.close();
	}
}
