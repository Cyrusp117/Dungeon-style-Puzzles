package entities;

public abstract class Entity {
	protected Coordinate position;
	public Entity(Coordinate position) {
		this.position = position;
	}
	
	public abstract Coordinate move();
	
	/**
	 * 
	 * @return position, the Coordinate of the current entity
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	/**
	 * 
	 * @return the x coordinate of the entity (int)
	 */
	public int returnX() {
		return position.getxPosition();
	}
	
	/**
	 * 
	 * @return the y coordinate of the entity (int)
	 */
	public int returnY() {
		return position.getyPosition();
	}

	public boolean willCollide(Coordinate otherPos) {
		return position.equals(otherPos);
		
	}
}
