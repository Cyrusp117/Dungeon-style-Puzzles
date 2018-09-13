package entities;

public class Hunter extends AI {

	public Hunter(Coordinate position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Hunter";
	}

	public boolean interact(Player player) {
		Coordinate playerPos = player.getPosition();
		if(playerPos.equals(this.position)) {
			player.setState(0);
			return false;
		}
		return false;

	}
	
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	

}
