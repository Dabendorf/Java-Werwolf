import java.io.*;
import java.net.*;
import java.util.*;

public class Communicator {
	private Socket socket;
	private List<String> messages;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Communicator() {
		this.messages = new ArrayList<String>();
	}
	
	public void connect(String ip, int port) throws IOException{
		this.socket = new Socket(ip, port);
		
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		Thread t = new Thread(new receiveThread());
		t.start();
	}
	
	public void send(String message) throws IOException{
		pw.println(message);
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
					
					String msg = br.readLine();
					System.out.println("Got a message from the server: " + msg);
					messages.add(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
