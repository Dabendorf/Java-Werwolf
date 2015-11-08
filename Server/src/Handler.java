import java.net.Socket;
import java.util.*;
public class Handler {
	
	private Communicator com;
	private boolean running;
	
	public Handler (Communicator c){
		com = c;
		new Thread(new incomingThread()).start();
	}
	
	//command for Gamemaster from empfänger
	
	
	
	public void startGame(List<Player> players){
		StringBuilder str = new StringBuilder();
		str.append("players;");
		for (Player p : players){
			str.append(p.getName() + ";");
		}
		//Communicator stuff....
		ArrayList<Socket> s = (ArrayList<Socket>) com.getClients();
		com.send(s.get(0), str.toString());
		
	}
	
	public void handle(String cmd){
		String action = cmd.split(";", 1)[0];
		String context = cmd.split(";", 1)[1];
		
		switch(action){
		
		case "msg" :
			//METHODE(context)
			break;
		
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
