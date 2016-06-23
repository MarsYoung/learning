package com.marsyoung.learning.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOSocketChannel {

	
	public static void main(String[] args) throws IOException {
		SocketChannel sc=SocketChannel.open();
		sc.configureBlocking(false);
		sc.connect(new InetSocketAddress("182.92.64.185", 80));
		
		
		ByteBuffer buf =ByteBuffer.allocate(97);
		while(sc.read(buf)!=-1){
			System.out.println(new String(buf.array()));
		}
		
		sc.close();
	}
}
