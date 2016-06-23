package com.marsyoung.file;

import java.io.File;

public class FileExists {

	public static void main(String[] args) {
		String path="/etc/nginx/sites-available/default";
		File f=new File(path);
		if(f.exists()){
			System.out.println(f.length());
		}else{
			System.out.println("文件找不到");
		}
	}
	
}
