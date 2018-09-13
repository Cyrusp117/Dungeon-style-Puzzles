package entities;

public class Arrow extends Entity {
	public Arrow(Coordinate position) {
		super(position);
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "Arrow";
	}
	
}
