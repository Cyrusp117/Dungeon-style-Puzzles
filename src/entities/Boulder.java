package entities;

public class Boulder extends Entity {
	public Boulder(Coordinate position) {
		super(position);
	}
	
	public boolean interactWithPlayer(int x,int y) {
		int newX = position.getxPosition() + x;
	    int newY = position.getyPosition() + y;
	    Coordinate newPos = new Coordinate(newX, newY);
	    this.setPosition(newPos);
	    return false;
	}
	
	public boolean interactWithBomb() {
		//delete this boulder
		return true;
	}
	
	public String getName() {
		return "Boulder";
	}
	
}