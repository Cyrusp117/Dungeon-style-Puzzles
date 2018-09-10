package entities;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



public class Player{
	private Coordinate position;
	private int dx;
	private int dy;
	
	public Player(int xPos, int yPos){
		this.position = new Coordinate(xPos, yPos);
	}

	public void keyPressed(KeyEvent e) {
		
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        dx = -1;
	        dy = 0;
	        System.out.println("LEFT");
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 1;
	        dy = 0;
	        System.out.println("RIGHT");
	    }

	    if (key == KeyEvent.VK_UP) {
	        dy = 1;
	        dx = 0;
	        System.out.println("UP");
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        dy = -1;
	        dx = 0;
	        System.out.println("DOWN");
	    }
	}

	public void move() {

		//if(isValidMove()){;
		
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
	    
	    position.setxPosition(newX);
	    position.setyPosition(newY);
//	    if (x < 1) {
//	        x = 1;
//	    }
//
//	    if (y < 1) {
//	        y = 1;
//	    }
	}
	
	public Coordinate getPosition(){
		return position;
	}
	
	public String returnPosition() {
		return "X Coordinate:" + position.getxPosition() + "Y Coordinate:" + position.getyPosition(); 
	}
}



