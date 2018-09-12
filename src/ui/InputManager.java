package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import entities.Coordinate;
import entities.Entity;
import entities.Hunter;
import entities.Player;
import entities.Treasure;

// to be refactored into the Game
public class InputManager implements KeyListener {
	private int dx = 0;
	private int dy = 0;
	private Game game;

	public InputManager(Game game) {
		this.game = game;
	}
	/**
	 * Action when keys are pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	    int key = e.getKeyCode();

	    // If the left arrow key is pressed, request to move left by a tile
	    if (key == KeyEvent.VK_LEFT) {
	        dx = -32;
	        dy = 0;
	        game.newTurn();
	        System.out.println("LEFT");
	        System.out.println(" ");
	    }
	    // If the right arrow key is pressed, request to move right by a tile
	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 32;
	        dy = 0;
	        game.newTurn();
	        System.out.println("RIGHT");
	    }
	    // If the up arrow key is pressed, request to move up by a tile
	    if (key == KeyEvent.VK_UP) {
	        dy = -32;
	        dx = 0;
	        game.newTurn();
	        System.out.println("UP");
	    }
	    // If the down arrow key is pressed, request to move down by a tile
	    if (key == KeyEvent.VK_DOWN) {
	        dy = 32;
	        dx = 0;
	        game.newTurn();
	        System.out.println("DOWN");
	    }
	    
	    // If F1 is pressed, the next two Integers in STDIN will be the  
	    // coordinate of the treasure
	    if (key == KeyEvent.VK_F1) {
	    	System.out.println("Please enter co-ordinates of Treasure: ");
			Scanner sc = new Scanner(System.in);
			dx = sc.nextInt()*32;
			dy = sc.nextInt()*32;
			
			Coordinate treasurePos = new Coordinate(dx, dy);
			if (game.addEntity(new Treasure(treasurePos))) {
				System.out.println("adding treasure at " + dx + dy);
			} else {
				System.out.println("Couldn't add treasure");
			}
	    }
	    
	    if (key == KeyEvent.VK_B) {
	    	System.out.println("Inventory contents: ");
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		System.out.print(curItem.getName() + " ");
	    	}
	    	System.out.println("\n");
	    }
	    
	    if (key == KeyEvent.VK_H) {
	    	System.out.println("Enter Hunter's Coordinates: ");
			Scanner sc = new Scanner(System.in);
			dx = sc.nextInt()*32;
			dy = sc.nextInt()*32;
			Coordinate hunterPos = new Coordinate(dx, dy);
			if (game.addEntity(new Hunter(hunterPos))) {
				System.out.println("adding hunter at " + dx + dy);
			} else {
				System.out.println("Couldn't add hunter");
			}
	    }
	}


	/**
	 * Actions when a key is released (currently nothing)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Actions when a key is typed (currently nothing)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * @return the dx the change in x position requested
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @return the dy the change in y position requested
	 */
	public int getDy() {
		return dy;
	}

}
