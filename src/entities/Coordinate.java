package entities;

import java.util.ArrayList;

public class Coordinate {
	private int xPosition;
	private int yPosition;
	private ArrayList<Entity> entities;
	public static final Coordinate UP = new Coordinate(0, -1);
	public static final Coordinate LEFT = new Coordinate(-1, 0);
	public static final Coordinate RIGHT = new Coordinate(1, 0);
	public static final Coordinate DOWN = new Coordinate(0, 1);
	
	
	public Coordinate(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.entities = new ArrayList<>();
	}

	/**
	 * @return the xPosition
	 */
	public int getX() {
		return xPosition;
	}

	/**
	 * @param xPosition the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getY() {
		return yPosition;
	}

	/**
	 * @param yPosition the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		
		if(!(obj instanceof Coordinate)) return false;
		
		Coordinate other = (Coordinate) obj;
		if(xPosition == other.getX() && yPosition == other.getY()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return the string displaying the current Coordinate of the entity
	 * The X and the Y Coordinate respectively
	 */
	public String returnPosition() {
		return "X Coordinate:" + getX() + " Y Coordinate:" + getY(); 
	}
	
	public static Coordinate addCoordinates(Coordinate x, Coordinate y) {
		int xSum = x.getX() + y.getX();
		int ySum = y.getY() + x.getY();
		return new Coordinate(xSum, ySum);
	}
	
	public Coordinate moveDown() {
		return addCoordinates(this, DOWN);
	}
	
	public Coordinate moveRight() {
		return addCoordinates(this, RIGHT);
	}
	
	public Coordinate moveUp() {
		return addCoordinates(this, UP);
	}
	
	public Coordinate moveLeft() {
		return addCoordinates(this, LEFT);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	/**
	 * @return the entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	

}
