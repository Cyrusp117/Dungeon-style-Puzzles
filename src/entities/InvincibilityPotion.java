package entities;

public class InvincibilityPotion extends Entity {
	public InvincibilityPotion(Coordinate position) {
		super(position);
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "InvincibilityPotion";
	}
	
}
