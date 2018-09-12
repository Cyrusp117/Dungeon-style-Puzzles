package entities;

import java.awt.event.KeyEvent;


public class Player extends Entity{
	
	private int dx;
	private int dy;
	
	public Player(Coordinate position){
		super(position);
		dx = 0;
		dy = 0;
	}

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
	 * @return the dx
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
	 * @return the dy
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

	public Coordinate getPosition(){
		return position;
	}
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	public int returnX() {
		return position.getxPosition();
	}
	
	public int returnY() {
		return position.getyPosition();
	}
	
	public String returnPosition() {
		return "X Coordinate:" + position.getxPosition() + " Y Coordinate:" + position.getyPosition(); 
	}
	
}



