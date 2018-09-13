package entities;

public class Sword extends Entity {
	int hitTime;
	public Sword(Coordinate position) {
		super(position);
		this.hitTime = 5;
	}
	
	public boolean interactWithPlayer(Player player) {
		if(!player.hasItem("Sword")) {
			player.pickUp(this);
			return true;
		}
		System.out.println("You already have sword, you can only carry one sword!!");
		return false;
	}
	
	@Override
	public int getHitTime() {
		return hitTime;
	}
	
	@Override
	public void setHitTime() {
		hitTime--;
	}

	public String getName() {
		return "Sword";
	}
	
}
