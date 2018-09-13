package entities;

public class HoverPotion extends Entity {
	public HoverPotion(Coordinate position) {
		super(position);
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "HoverPotion";
	}
	
}
