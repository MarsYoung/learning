package com.marsyoung.learning.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileWrite {

	public static void main(String[] args) throws IOException {

		String[] message = { "write test", "write test" };
		// Selector selector = Selector.open();//use one single thread to manage
		// more channel
		FileOutputStream fo = new FileOutputStream(new File("a.txt"));
		FileChannel fc = fo.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(97);
		for (int i = 0; i < message.length; ++i) {
			buffer.put(message[i].getBytes());
		}

		buffer.flip();

		fc.write(buffer);

		fo.close();
	}
}
