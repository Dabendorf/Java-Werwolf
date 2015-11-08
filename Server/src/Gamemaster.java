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
	
	public enum Phase{
		werewolvesKilling,
		announceVictim,
		vote;
	}
	
	public Gamemaster(Handler handler){
		this.handler = handler;
	}
	
	public void registerPlayer(Player player){
		players.add(player);
		if(players.size() >= 8)
			start();
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
		sendToAll("Das Spiel startet jetzt.\nNacht fällt über Düsterwald, ");
	}
	
	public void run(){
		
	}
}
