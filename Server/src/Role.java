public abstract class Role {
	
	public enum RoleId {
		Villager,
		Werewolf;
	}
	
	RoleId id;
	
	public Role(RoleId id){
		this.id = id;
	}
	
	public abstract String name();
	
	public RoleId getRoleId() {
		return this.id;
	}
}