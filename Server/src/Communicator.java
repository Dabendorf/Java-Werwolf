import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
	private ServerSocket ssock;
	private List<DataOutputStream> streams;
	
	public Communicator (int port) throws IOException{
		this.ssock = new ServerSocket(port);
		System.out.println("Server started on port " + Integer.toString(port) + ".");
		
		this.streams = new ArrayList<DataOutputStream>();
	}
	
	private class acceptThread implements Runnable {
		@Override
		public void run() {
			System.out.println("The server now accepts connections from clients.");
			while(true) {
				try {
					Socket c = ssock.accept();
					DataOutputStream dops = new DataOutputStream(c.getOutputStream());
					DataInputStream dips = new DataInputStream(c.getInputStream());
					
					streams.add(dops);
					new Thread(new receiveThread()).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private class receiveThread implements Runnable {
		public public receiveThread() {
			
		}
		
		@Override
		public void run() {
			while(true) {
				
			}
		}
		
	}
}
