package entities;

public abstract class Entity {
	protected Coordinate position;
	protected int keyCode;
	public Entity(Coordinate position) {
		this.position = position;
	}
	

	public boolean interactWithPlayer(Player player) {
		return false;
	}
	
//	public Coordinate move() {
//		System.out.println("Cant Move");
//		return null;
//	}


	public abstract String getName();

	/**
	 * 
	 * @return position, the Coordinate of the current entity
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
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
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
	public void getDesignerDescription() {
		System.out.println(this.getName() + " - " + (char)this.getKeyCode());

	}
	
	public int getHitTime() {
		return 0;
	}
	
	public void setHitTime() {
	}
}