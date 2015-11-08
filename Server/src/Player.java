
public class Player {
	private boolean isAlive = true;
	public int countVotes = 0;
	private RoleId role;
	
	public Player(){
		
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
}
