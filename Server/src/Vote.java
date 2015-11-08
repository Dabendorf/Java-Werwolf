import java.util.List;

public class Vote {
	private List<Player> participants; 
	private List<Player> nominees;
	
	public Vote(List<Player> participants){
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
