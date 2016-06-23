package com.marsyoung.learning.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NIO {

	
	public static void main(String[] args) throws IOException {
		InputStream in =new FileInputStream(new File("a.txt"));
		BufferedReader reader =new BufferedReader(new InputStreamReader(in));
		String nameLIne=reader.readLine();
		String ageLine=reader.readLine();
		String emailLine=reader.readLine();
		String phoneLine=reader.readLine();
		System.out.println(nameLIne);
		System.out.println(ageLine);
		System.out.println(emailLine);
		System.out.println(phoneLine);
		reader.close();
		
	}
}
