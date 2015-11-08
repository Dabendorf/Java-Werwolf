
public abstract class Role {
	
	RoleId id;
	
	public Role(RoleId id){
		this.id = id;
	}
	
	public abstract String name();
	
	public RoleId getRoleId() {
		return this.id;
	}
}