package entities;


public class Player extends Entity{
	
	private int dx; // Current x velocity
	private int dy; // Current y velocity
	
	public Player(Coordinate position){
		super(position);
		dx = 0;
		dy = 0;
	}

	/*
	 * 
	 * @return the newPosition requested to be moved to by the player
	 */
	public Coordinate move() {
		System.out.println("DX = " + dx + " DY = " + dy);
		System.out.println(position.getxPosition());
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
	    Coordinate newPos = new Coordinate(newX, newY);
	    System.out.println("Hello");
	    return newPos;
	    
	}
	
	/**
	 * @return the dx: The change in the x position
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy: The change in y position
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the position, the Coordinate of the player
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	/**
	 * @param position, the Coordinate of the player to set.
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	/**
	 * @return the X Coordinate of the player (int)
	 */
	public int returnX() {
		return position.getxPosition();
	}
	
	/**
	 * @return the Y Coordinate of the player (int)
	 */
	public int returnY() {
		return position.getyPosition();
	}
	
	/**
	 * 
	 * @return the string displaying the current Coordinate of the player
	 * The X and the Y Coordinate respectively
	 */
	public String returnPosition() {
		return position.returnPosition();
	}
	
}



