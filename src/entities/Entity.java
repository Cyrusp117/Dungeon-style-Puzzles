package entities;

public abstract class Entity {
	protected Coordinate position;
	
	public Entity(Coordinate position) {
		this.position = position;
	}
	
	public boolean interact(Player player) {
		return false;
	}
	
	public Coordinate move() {
		System.out.println("Cant Move");
		return null;
	}

	public abstract String getName();

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
