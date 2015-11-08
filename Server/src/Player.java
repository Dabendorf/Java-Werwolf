
public class Player {
	private String name;
	private boolean isAlive;
	public int countVotes = 0;
	private RoleId role;
	
	public Player(String name){
		this.name = name;
		isAlive = true;
	}
	
	public RoleId getRole(){
		return this.role;
	}
	
	public void kill(){
		this.isAlive = false;
	}

	public void setRole(RoleId role) {
		this.role = role;
	}
	
	public void reanimate(){
		isAlive = true;
	}
	
	public String getName(){
		return this.name;
	}
}
