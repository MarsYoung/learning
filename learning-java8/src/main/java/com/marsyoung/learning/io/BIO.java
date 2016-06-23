package com.marsyoung.learning.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIO {

	ServerSocket serverSocket;
	
	public void init() throws IOException{
		serverSocket=new ServerSocket(9797);
	}
	
	public void bio() throws IOException{
		Socket socket =serverSocket.accept();//阻塞
		InputStream in =socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		byte[] b=new byte[2048];
		while(in.read(b, 0, 2048)!=-1){//阻塞在这里
			System.out.println(new String(b));
			out.write(b);
			if(new String(b).contains("shutdown")){
				break;
			}
		}
		out.close();
		in.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		BIO t= new BIO();
		t.init();
		t.bio();
	}
}
