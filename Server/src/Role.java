
public abstract class Role {
	
	protected RoleId id;
	protected String name;
	
	public Role(RoleId id, String name){
		this.id = id;
		this.name = name;
	}
	
	//public abstract String name();
	
	public String getName(){
		return this.name;
	}
	
	public RoleId getRoleId() {
		return this.id;
	}
}