import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
	private ServerSocket ssock;
	private List<Socket> clients;
	private HashMap<Socket, String> messages;
	
	public Communicator (int port) throws IOException{
		this.ssock = new ServerSocket(port);
		System.out.println("Server started on port " + Integer.toString(port) + ".");
		
		this.clients = new ArrayList<Socket>();
		this.messages = new HashMap<Socket, String>();
	}
	
	public static void main(String[] args) {
		try{
			Communicator c = new Communicator(8080);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void send(Socket s, String message) throws IOException {
		DataOutputStream dops = new DataOutputStream(s.getOutputStream());
		dops.write(message.getBytes());
	}
	
	public synchronized String getLatestMessage(){
		String msg = null;
		if(this.messages.size() > 0){
			msg = this.messages.get(0);
		}
		this.messages.remove(0);
		return msg;
	}
	
	private class acceptThread implements Runnable {
		@Override
		public void run() {
			System.out.println("The server now accepts connections from clients.");
			while(true) {
				try {
					Socket client = ssock.accept();
					DataOutputStream dops = new DataOutputStream(client.getOutputStream());
					
					clients.add(client);
					new Thread(new receiveThread(client)).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private class receiveThread implements Runnable {
		private Socket socket;
		private DataInputStream s;
		
		public receiveThread(Socket socket) throws IOException {
			this.socket = socket;
			this.s = new DataInputStream(this.socket.getInputStream());
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					messages.put(socket, s.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
