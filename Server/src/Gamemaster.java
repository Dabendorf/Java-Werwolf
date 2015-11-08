import java.util.List;
import java.util.Random;

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
		sendToAll("Das Spiel startet jetzt.\n");
		for(Player player : players){
			player.setRole(RoleId.Villager);
		}
		Random random = new Random();
		Player player;
		// This piece of codes sets a fourth of the players to be werewolves
		for(int i = 0; i < (int)players.size() / 4; i++){
			 player = players.get(random.nextInt(players.size()));
			 if(player.getRole() == RoleId.Villager){
				 player.setRole(RoleId.Werewolf);
				 handler.sendTo(player, "Du bist ein Werwolf!");
			 }
			 else
				 i--;
		}
		sendToAll("Nacht fällt über Düsterwald.\n"
				+ "Alle Bewohner des Dorfes schlafen ein.");
	}
	
	public void run(){
		
	}
}
