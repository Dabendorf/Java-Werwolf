import java.util.ArrayList;
import java.util.Random;

public class Gamemaster {
	
	private final int MIN_PLAYERS = 4;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Handler handler;
	private boolean running = false;
	private Phase currentPhase;
	
	private ArrayList<Player> villagers = new ArrayList<Player>();
	private ArrayList<Player> werewolves = new ArrayList<Player>();
	private ArrayList<Player> alivePlayers = new ArrayList<Player>();
	
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
		Vote;
	}
	
	public Gamemaster(Handler handler){
		this.handler = handler;
	}
	
	public void registerPlayer(Player player){
		players.add(player);
		if(players.size() >= MIN_PLAYERS)
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
			alivePlayers.add(player);
		}
		Random random = new Random();
		Player player;
		// This piece of codes sets a fourth of the players to be werewolves
		for(int i = 0; i < (int)players.size() / 2; i++){
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
		
		running = true;
		Thread thread = new Thread( new Runnable(){
			public void run(){
				while (running){
					werewolfPhase();
					vote();
				}
				// Spiel ist hier zu Ende
			}
		});
	}
	
	private void werewolfPhase(){
		currentPhase = Phase.WerewolvesKilling;
		for(Player player : werewolves){
			handler.sendMessage(player, "Die Werwoelfe erwachen und stimmen für ein Opfer ab.");
		}
		// TODO hier muss klargestellt werden, dass nur noch die woelfe chatten koennen
		Vote vote = new Vote(werewolves);
		// TODO auf votes warten und ggf. vote.receiveVote() aufrufen, bis alle woelfe gevotet haben
		Player loser = vote.performVote();
		handler.sendToAll("Die Werwölfe haben sich für ein Opfer entschieden!");
			
		kill(loser);
		
		handler.sendToAll("Das Opfer war " + loser.getName() + ".");
	}
	
	private void vote(){
		currentPhase = Phase.Vote;
		// TODO ab jetzt sind wieder alle am Chat beteiligt
		handler.sendToAll("Alle waehlen nun fuer einen Mitbewohner ab, der exekutiert werden soll.");
		Vote vote = new Vote(alivePlayers);
		// TODO auf alle votes warten und vote.receiveVote() ausfuehren
		Player loser = vote.performVote();
		handler.sendToAll("Das Voting ist beendet! Das Opfer ist " + loser.getName() + " mit " + 
				loser.countVotes + " Votes.");
		
		loser.kill();
		alivePlayers.remove(loser);
		kill(loser);
	}
	
	private void kill(Player loser){
		loser.kill();
		alivePlayers.remove(loser);
		switch(loser.getRole())
		{
		case Villager:
			handler.sendToAll("Er war ein Dorfbewohner.");
		
		case Werewolf:
			handler.sendToAll("Er war ein Werwolf.");
		}
	}
}
