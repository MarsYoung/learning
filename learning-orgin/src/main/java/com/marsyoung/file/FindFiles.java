package com.marsyoung.file;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FindFiles{

	void find(Set<File> files, String path, String keyWord) {
		File currentDir=new File(path);
		if (!currentDir.exists())
			return;
		File[] subfiles = currentDir.listFiles();
		if (subfiles != null) {
			for (File file : subfiles) {
				// 如果是文件夹，则继续遍历
				find(files, file.getAbsolutePath(), keyWord);
				// 如果是文件则判断是否符合keyWord
				if (FileUtils.getFile(file.getAbsolutePath()).isFile()) {
					try {
						FileUtils.readLines(file).forEach(st->{
							if(StringUtils.contains(st, keyWord)){
								files.add(file);
								System.out.println(file.getAbsolutePath());
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		new FindFiles().find(new HashSet<File>(), "D:/gitspace/marsyoung/cassandra", "main");
	}

}
