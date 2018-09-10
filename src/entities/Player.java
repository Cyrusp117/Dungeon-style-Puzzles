package entities;

import java.awt.event.KeyEvent;


public class Player{
	private Coordinate position;
	private int dx;
	private int dy;
	
	public Player(int xPos, int yPos){
		this.position = new Coordinate(xPos, yPos);
	}

	public void move() {

		//if(isValidMove()){;
		
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
	    
	    position.setxPosition(newX);
	    position.setyPosition(newY);
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



