package entities;



public class Pit extends Entity{
	public Pit(Coordinate position) {
		super(position);
	}

	public boolean interactWithPlayer(Player player,int x, int y) {
		if(player.hasItem("HoverPotion")) {
			int newX = position.getxPosition() + x;
		    int newY = position.getyPosition() + y;
		    Coordinate newPos = new Coordinate(newX, newY);
		    player.setPosition(newPos);
		}else{
			//player die
		}
		return false;
	}
	
	public boolean interactWithBoulder(Boulder boulder) {
		//delete boulder
		return true;
	}
	
	public String getName() {
		return "Pit";
	}
	
}