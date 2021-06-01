package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		
		try {
			ServerSocket ss = new ServerSocket(8000);
			Socket client1 = ss.accept();
			Socket client2 = ss.accept();
			System.out.println(client1.toString()+" "+client2.toString());
			
			DataInputStream dis1 = new DataInputStream(client1.getInputStream());
			DataInputStream dis2 = new DataInputStream(client2.getInputStream());
			
			DataOutputStream dos1 = new DataOutputStream(client1.getOutputStream());
			DataOutputStream dos2 = new DataOutputStream(client2.getOutputStream());
			
			dos1.writeUTF("hola client 1");
			dos2.writeUTF("hola client 2");
			
			Runnable read1 = new ReadHandler(client1,client2);
			Runnable read2 = new ReadHandler(client2,client1);
			Thread thread1 = new Thread(read1);
			Thread thread2 = new Thread(read2);
			
			//Start the ReadHandler class
			thread1.start();
			thread2.start();
			
		} catch (IOException e) {
			throw new IOException(e);
		}

	}

}
