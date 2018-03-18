package com.marsyoung.learning.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NIOChannelTransfer {

	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("a.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("b.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		toChannel.close();
		
		fromFile.close();
		toFile.close();
	}
}
