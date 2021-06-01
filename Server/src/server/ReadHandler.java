package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ReadHandler implements Runnable{
	Socket client1;
	Socket client2;
	DataInputStream dis1;
	DataInputStream dis2;
	DataOutputStream dos1;
	DataOutputStream dos2;
	int positionY = 0;
	
	public ReadHandler(Socket client1,Socket client2) {
		super();
		this.client1 = client1;
		try {
			dis1 = new DataInputStream(client1.getInputStream());
			dos1 = new DataOutputStream(client1.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.client2 = client2;
		try {
			dis2 = new DataInputStream(client2.getInputStream());
			dos2 = new DataOutputStream(client2.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				String message = dis1.readUTF();
				System.out.println(message);
				if(message.charAt(0)==' ') {
					Random rdm = new Random();
					int number = rdm.nextInt(10);
					positionY += number;
					dos1.writeUTF(positionY+"");
					dos2.writeUTF(positionY+"");
				}else {
					dos1.writeUTF("0");
					dos2.writeUTF("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
