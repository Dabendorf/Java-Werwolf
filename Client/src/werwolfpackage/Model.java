package werwolfpackage;

public class Model {
	//private static gameState state;
	private static String[] players;
	
	public static void setPlayers (String[] playerlist){
		players = playerlist;
	}
	
	public static String[] getPlayers(){
		return players;
	}
	
	/*private enum gameState {
		START,WEREWOLVES,VICTIM_ANNOUNCE,VOTE;
	}
	
	public gameState getGameState(){
		return state;
	}
	
	public void setGameState(gameState state){
		this.state = state;
	}*/
}
