package werwolfpackage;

public class Model {
	private gameState state;
	private Player [] players;
	
	public Model (int playerCount){
		players = new Player[playerCount];
	}
	
	private enum gameState {
		START,WEREWOLVES,VICTIM_ANNOUNCE,VOTE;
	}
	
	public gameState getGameState(){
		return state;
	}
	
	public void setGameState(gameState state){
		this.state = state;
	}
}
