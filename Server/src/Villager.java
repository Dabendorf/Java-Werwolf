public class Villager extends Role{

	public Villager() {
		super(RoleId.Villager);
	}

	@Override
	public String name() {
		return "Villager";
	}
}
