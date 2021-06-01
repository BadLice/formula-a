package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",8000);
			System.out.println("I AM CONNECTED !");
			
			DataInputStream dis1 = new DataInputStream(socket.getInputStream());
			DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
			String message1 = null;
			message1 = dis1.readUTF();
			System.out.println(message1);
			while(true) {
				/*
				 * Scanner sc = new Scanner(System.in);
				 * System.out.println("WRITE A MESSAGE");
				 * String messageClient = sc.next();
				 */
				System.out.println("Write:");
				int messageClientInt = System.in.read();
				char messageClientChar = (char) messageClientInt;
				String messageClientString = Character.toString(messageClientChar);
				dos1.writeUTF(messageClientString);
				System.out.println(dis1.readUTF());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
