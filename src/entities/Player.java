package entities;

import java.awt.event.KeyEvent;


public class Player{
	private Coordinate position;
	private int dx;
	private int dy;
	
	public Player(Coordinate position){
		this.position = position;
	}

	public void move(int xBoundary, int yBoundary) {
		

		
		
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
	    Coordinate newPos = new Coordinate(newX, newY);
	    if(!isOutOfBounds( xBoundary, yBoundary, newPos )) {
		    position.setxPosition(newX);
		    position.setyPosition(newY);
	    }

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
	
	public boolean isOutOfBounds(int xBoundary, int yBoundary, Coordinate newPos) {
		System.out.println("Xboundary: " + xBoundary +" Yboundary: " + yBoundary);
		if(newPos.getxPosition() < 0 || newPos.getxPosition() > xBoundary ) {
			return true;
		}else if( newPos.getyPosition() < 0 || newPos.getyPosition() > yBoundary) {
			return true;
		}
		
		return false;
	}
}



