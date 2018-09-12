package entities;

public abstract class Entity {
	protected Coordinate position;
	public Entity(Coordinate position) {
		this.position = position;
	}
	
	public abstract Coordinate move();
	
	public Coordinate getPosition(){
		return position;
	}
	
	public int returnX() {
		return position.getxPosition();
	}
	
	public int returnY() {
		return position.getyPosition();
	}
	
	

}
