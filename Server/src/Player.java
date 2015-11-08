
public class Player {
	private String name;
	private boolean isAlive;
	public int countVotes = 0;
	
	public Player(String name){
		this.name = name;
		isAlive = true;
	}
	
	public void kill(){
		isAlive = false;
	}
	
	public void reanimate(){
		isAlive = true;
	}
	
	public String getName(){
		return this.name;
	}
	
	
}
