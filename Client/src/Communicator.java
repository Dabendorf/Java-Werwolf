import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
	private DataOutputStream dops;
	private DataInputStream dips;
	private List<String> messages;
	
	public Communicator() {
		this.messages = new ArrayList<String>();
	}
	
	public void connect(String ip, int port) throws IOException{
		Socket sock = new Socket(ip, port);
		this.dops = new DataOutputStream(sock.getOutputStream());
		this.dips = new DataInputStream(sock.getInputStream());
		
		Thread t = new Thread(new receiveThread());
		t.start();
	}
	
	public void send(String message) throws IOException{
		this.dops.write(message.getBytes());
	}

	public synchronized String getLatestMessage(){
		String msg = null;
		if(this.messages.size() > 0){
			msg = this.messages.get(0);
		}
		this.messages.remove(0);
		return msg;
	}
	
	private class receiveThread implements Runnable{
		@Override
		public void run() {
			while(true){
				try {
					System.out.println(dips.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
