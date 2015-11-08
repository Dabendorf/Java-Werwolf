import java.util.List;
import java.util.Random;

public class Gamemaster {
	
	private List<Player> players;
	private Handler handler;
	private boolean running = false;
	private Phase currentPhase;
	
	private List<Player> villagers;
	private List<Player> werewolves;
	
	public enum Actions{
		// Basic Actions for the game
		Login,
		SendMessage,
		Vote,
		Kill;
	}
	
	public enum Phase{
		Preparation,
		WerewolvesKilling,
		AnnounceVictim,
		Vote;
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
	
	public void sendTo(String message, RoleId role){
		//  send message to all werewolves
		/*
		for (Player player : players){
			if (Player.getRole() == role)
				handler.sendMessage(player, message);
		}
		*/
	}
	
	public void start(){
		currentPhase = Phase.Preparation;
		sendToAll("Das Spiel startet jetzt.\n");
		for(Player player : players){
			player.setRole(RoleId.Villager);
			villagers.add(player);
		}
		Random random = new Random();
		Player player;
		// This piece of codes sets a fourth of the players to be werewolves
		for(int i = 0; i < (int)players.size() / 4; i++){
			 player = players.get(random.nextInt(players.size()));
			 if(player.getRole() == RoleId.Villager){
				 player.setRole(RoleId.Werewolf);
				 werewolves.add(player);
				 villagers.remove(player);
				 handler.sendMessage(player, "Du bist ein Werwolf!");
			 }
			 else
				 i--;
		}
		for(Player villager : villagers)
			handler.sendMessage(player, "Du bist ein Dorfbewohner!");
		
		sendToAll("Nacht fällt über Düsterwald.\n"
				+ "Alle Bewohner des Dorfes schlafen ein.");
		
		werwolfPhase();
	}
	
	public void run(){
		
	}
	
	private void werwolfPhase(){
		currentPhase = Phase.WerewolvesKilling;
		for(Player player : werewolves){
			handler.sendMessage(player, "Die Werwoelfe erwachen und stimmen für ein Opfer ab.");
		}
		// hier muss klargestellt werden, dass nur noch die woelfe chatten koennen
		Vote vote = new Vote(werewolves);
		// TODO auf votes warten und ggf. vote.receiveVote() aufrufen
		
	}
}
