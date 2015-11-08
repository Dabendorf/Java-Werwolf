import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
	private ServerSocket ssock;
	private HashMap<Socket, PrintWriter> clients;
	private HashMap<Socket, String> messages;
	
	public Communicator (int port) throws IOException{
		this.ssock = new ServerSocket(port);
		System.out.println("Server started on port " + Integer.toString(port) + ".");
		
		this.clients = new HashMap<Socket, PrintWriter>();
		this.messages = new HashMap<Socket, String>();
		
		while(true) {
			Socket client = ssock.accept();
			System.out.println("A client has connected: " + client.getInetAddress().toString());
				
			clients.put(client, new PrintWriter(new OutputStreamWriter(client.getOutputStream())));
			new Thread(new receiveThread(client)).start();
			
		}
	}
	
	public void send(Socket s, String message) throws IOException {
		this.clients.get(s).println(message);
	}
	
	public ArrayList<Socket> getClients(){
		ArrayList<Socket> list = new ArrayList<Socket>();
		for (Socket s : this.clients.keySet()) {
			list.add(s);
		}
		return list;
	}
	
	public synchronized String getLatestMessage(){
		String msg = null;
		if(this.messages.size() > 0){
			msg = this.messages.get(0);
		}
		this.messages.remove(0);
		return msg;
	}

	
	private class receiveThread implements Runnable {
		private Socket socket;
		private BufferedReader br;
		
		public receiveThread(Socket socket) throws IOException {
			this.socket = socket;
			InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
			this.br = new BufferedReader(isReader);
		}
		
		@Override
		public void run() {
			String msg;
			try {
				while(!this.br.ready()){}
				while((msg = this.br.readLine()) != null) {
					messages.put(socket, msg);
					System.out.println("[" + socket.getInetAddress().getHostName().toString() + "]: " + msg);	
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
	}
}
