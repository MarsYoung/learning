package com.marsyoung.learning.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NewIO {

	public static void main(String[] args) throws IOException {
		
		RandomAccessFile aFile =new RandomAccessFile("a.txt","rw");
		FileChannel inChannel =aFile.getChannel();
		
		ByteBuffer buf =ByteBuffer.allocate(48);
		
		int bytesRead =inChannel.read(buf);
		while(bytesRead != -1){
			buf.flip();
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			buf.clear();
			bytesRead=inChannel.read(buf);
		}
		aFile.close();
	}
	
}
