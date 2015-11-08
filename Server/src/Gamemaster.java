import java.util.List;

public class Gamemaster {
	
	private List<Player> players;
	private Handler handler;
	
	private boolean running = false;
	
	public enum Actions{
		// Basic Actions for the game
		login,
		sendMessage,
		vote,
		kill;
	}
	
	public Gamemaster(Handler handler){
		this.handler = handler;
	}
	
	public void registerPlayer(Player player){
		players.add(player);
	}
	
	public void sendToAll(String message){
		// send given message to all players
		/*
		for (Player player : players){
			handler.sendMessage(player, message);
		}
		*/
	}
	
	public void sendToRole(String message, RoleId role){
		//  send message to all werewolves
		/*
		for (Player player : players){
			if (Player.getRole() == role)
				handler.sendMessage(player, message);
		}
		*/
	}
	
	public void start(){
		
	}
	
	public void run(){
		
	}
}
