package entities;



public class Treasure extends Entity {
	public Treasure(Coordinate position) {
		super(position);
	}

	@Override
	public Coordinate move() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean interact(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "Treasure";
	}
	
}
