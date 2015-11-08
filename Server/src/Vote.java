import java.util.ArrayList;

public class Vote {
	private ArrayList<Player> participants = new ArrayList<Player>(); 
	private ArrayList<Player> nominees = new ArrayList<Player>();
	
	public Vote(ArrayList<Player> participants){
		for(Player participant : participants)
			this.participants.add(participant);
	}
	
	public void receiveVote(Player player){
		if(!nominees.contains(player)){
			nominees.add(player);
			player.countVotes = 0;
		}
		player.countVotes++;
	}
	
	public Player performVote(){
		// handler and networking
		// end of votes
		/*
		Player loser = new Player();
		for(Player nominee : nominees){
			if(nominee.countVotes > loser.countVotes)
				loser = nominee;
		}
		return loser;
		*/
		return null; // vorerst
	}
}
