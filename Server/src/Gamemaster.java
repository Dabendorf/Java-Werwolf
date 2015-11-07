import java.util.List;

public class Gamemaster {
	
	private List<Player> players;
	
	public enum Actions{
		// Basic Actions for the game
		login,
		sendMessage,
		vote,
		kill;
	}
	
	public Gamemaster(){
		
	}
	
	public void registerPlayer(Player player){
		players.add(player);
	}
	
	public void sendToAll(String message){
		// send given message to all players
		/*
		for (Player player : players){
			player.sendMessage(message);
		}
		*/
	}
	
	public void sendToWerewolves(String message){
		//  send message to all werewolves
		/*
		for (Player player : players){
			if (Player.getRole() == RoleId.Werewolf)
				player.sendMessage(message);
		}
		*/
	}
	
	public void start(){
		
	}
	
	public void tick(){
		
	}
}
