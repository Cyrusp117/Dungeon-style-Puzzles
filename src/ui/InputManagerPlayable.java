package ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import entities.Entity;

public abstract class InputManagerPlayable extends InputManager {
	protected Game game;
	protected int dx = 0;
	protected int dy = 0;
	
	public InputManagerPlayable(Game game, Application app) {
		super(app);
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// If the left arrow key is pressed, request to move left by a tile
	    if (key == KeyEvent.VK_LEFT) {
	        dx = -32;
	        dy = 0;
	        System.out.println("LEFT");
	        game.newTurn();

	    }
	    // If the right arrow key is pressed, request to move right by a tile
	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 32;
	        dy = 0;
	        System.out.println("RIGHT");
	        game.newTurn();

	    }
	    // If the up arrow key is pressed, request to move up by a tile
	    if (key == KeyEvent.VK_UP) {
	        dy = -32;
	        dx = 0;
	        System.out.println("UP");
	        game.newTurn();

	    }
	    // If the down arrow key is pressed, request to move down by a tile
	    if (key == KeyEvent.VK_DOWN) {
	        dy = 32;
	        dx = 0;
	        System.out.println("DOWN");
	        game.newTurn();

	    }
	    
	    if (key == KeyEvent.VK_ESCAPE) {
	    	dx = 32;
	    	dy = 32;
	    	System.out.println("*****  Returning to Menu.. *****");
	    	app.getFrame().removeKeyListener(this);
	    	app.getFrame().addKeyListener(new InputManagerMenu(app));
	    }
	    extraFunctions(e);
	}
	
	public abstract void extraFunctions(KeyEvent e);

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

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
