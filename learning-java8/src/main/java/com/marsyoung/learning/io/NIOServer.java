package com.marsyoung.learning.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

	
	private Selector selector;
	
	public void initServer(int port) throws IOException{
		ServerSocketChannel serverChannel =ServerSocketChannel.open();//打开一个通道
		serverChannel.configureBlocking(false);//可以阻塞和非阻塞
		serverChannel.socket().bind(new InetSocketAddress(port));//绑定到对应的serverSocket
		this.selector=Selector.open();//获得一个通道管理器
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);//注册op_accept事件
		//服务端接受客户端连接事件：op_accept
		//客户端连接服务端事件：op_connect
		//读事件：op_read
		//写事件：op_write
	}
	
	public void listen() throws IOException{
		System.out.println("server start successful.");
		while(true){
			selector.select();
			Iterator<?> ite= this.selector.selectedKeys().iterator();
			//ite.remove();
			while(ite.hasNext()){
				SelectionKey key =(SelectionKey)ite.next();
				ite.remove();
				if(key.isAcceptable()){
					ServerSocketChannel server =(ServerSocketChannel)key.channel();
					SocketChannel channel =server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("hello,client").getBytes()));
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
		System.out.println("the message server got is :"+new String(new String(buffer.array())));
		ByteBuffer outBuffer=ByteBuffer.wrap("has got the message".getBytes());
		channel.write(outBuffer);
	}

	public static void main(String[] args) throws IOException {
		NIOServer server =new NIOServer();
		server.initServer(9797);
		server.listen();
	}
}
