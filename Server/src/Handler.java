import java.io.IOException;
import java.net.Socket;
import java.util.*;
public class Handler {
	
	private Communicator com;
	private boolean running;
	private HashMap<Player, Socket> sockets;
	
	public Handler (List<Player> players){
		try {
			com = new Communicator(8084);
		} catch (IOException e){
			e.printStackTrace();
		}
		for(Player p : players){
			
		}
		new Thread(new incomingThread()).start();
	}
	
	//command for Gamemaster from empfänger
	
	public void sendToPlayer(Socket s, String msg) {
		try {
			com.send(s, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendall(String msg) {
		for (Socket s : com.getClients()) {
			sendToPlayer(s, msg);
		}
	}
	
	
	public void startGame(List<Player> players){
		StringBuilder str = new StringBuilder();
		str.append("players;");
		for (Player p : players){
			str.append(p.getName() + ";");
		}
		//Communicator stuff....
		ArrayList<Socket> s = com.getClients();
		com.send(s.get(0), str.toString());
		
	}
	
	public void handle(String cmd){
		String[] splitCmd = cmd.split(";");
		String action = splitCmd[0];
		
		switch(action) {
			case "msgAll" :
				for (Socket s : com.getClients()) {
					try {
						com.send(s, splitCmd[1]);
					} catch (IOException e) {e.printStackTrace();}
				}
				break;
			
			case "msg":
				String recver = splitCmd[1];
				String msg = splitCmd[2];
				com.send(/*HIER KOMMT EINE ABFRAGE SPIELER-> SOCKET*/, msg);
				break;
				
			case "vote":
				String victim = splitCmd[1];
				
		}
	}
	
	//commands for sender
	
	private class incomingThread implements Runnable{
		@Override
		public void run() {
			handle(com.getLatestMessage());
		}
		
	}

}
