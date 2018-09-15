package entities;

public class Treasure extends Entity {
	public Treasure(Coordinate position) {
		super(position);
	}
	
	@Override
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "Treasure";
	}
	
}
