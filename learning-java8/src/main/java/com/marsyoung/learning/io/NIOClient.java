package com.marsyoung.learning.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

	private Selector selector;
	
	public void initClient(String ip,int port) throws IOException{
		SocketChannel channel =SocketChannel.open();
		channel.configureBlocking(false);
		this.selector=Selector.open();//获取一个通道管理器
		channel.connect(new InetSocketAddress(ip, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void listen() throws IOException{
		while(true){
			selector.select();
			Iterator<?> ite =this.selector.selectedKeys().iterator();
			while(ite.hasNext()){
				SelectionKey key= (SelectionKey)ite.next();
				//ite.remove();//illegalStateException
				if(key.isConnectable()){
					SocketChannel channel =(SocketChannel)key.channel();
					if(channel.isConnectionPending()){
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("hello,server").getBytes()));
					channel.register(this.selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					read(key);
				}
			}
		}
	}
	
	private void read(SelectionKey key)  throws IOException{  
			SocketChannel channel =(SocketChannel)key.channel();
			ByteBuffer buffer =ByteBuffer.allocate(97);
			channel.read(buffer);
			System.out.println("the message client got is :"+new String(new String(buffer.array())));
			ByteBuffer outBuffer=ByteBuffer.wrap("client has got the message".getBytes());
			channel.write(outBuffer);
	}

	public static void main(String[] args) throws IOException {
		NIOClient client =new NIOClient();
		client.initClient("localhost", 9797);
		client.listen();
	}
}
