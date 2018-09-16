package entities;


public class Coordinate {
	private int xPosition;
	private int yPosition;
	public static final Coordinate UP = new Coordinate(0, -32);
	public static final Coordinate LEFT = new Coordinate(-32, 0);
	public static final Coordinate RIGHT = new Coordinate(32, 0);
	public static final Coordinate DOWN = new Coordinate(0, 32);
	
	public Coordinate(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
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
	public int getyPosition() {
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
		if(xPosition == other.getxPosition() && yPosition == other.getyPosition()) {
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
		return "X Coordinate:" + getxPosition() + " Y Coordinate:" + getyPosition(); 
	}
	
	public static Coordinate addCoordinates(Coordinate x, Coordinate y) {
		int xSum = x.getxPosition() + y.getxPosition();
		int ySum = y.getyPosition() + x.getyPosition();
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
	
	
	

}
