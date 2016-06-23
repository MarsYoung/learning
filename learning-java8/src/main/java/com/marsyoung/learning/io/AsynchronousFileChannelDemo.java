package com.marsyoung.learning.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * In Java 7 the AsynchronousFileChannel was added to Java NIO. The
 * AsynchronousFileChannel makes it possible to read data from, and write data
 * to files asynchronously. This tutorial will explain how to use the
 * AsynchronousFileChannel.
 * 
 * @author zhiyuma
 *
 */
public class AsynchronousFileChannelDemo {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("a.txt");
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		long position = 0;
		{
			Future<Integer> operation = fileChannel.read(buffer, position);
			while (!operation.isDone())
				;
			buffer.flip();
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			System.out.println(new String(data));
			buffer.clear();
		}

		{
			fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					System.out.println("result = " + result);

					attachment.flip();
					byte[] data = new byte[attachment.limit()];
					attachment.get(data);
					System.out.println(new String(data));
					attachment.clear();
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {

				}
			});
		}
	}

}
