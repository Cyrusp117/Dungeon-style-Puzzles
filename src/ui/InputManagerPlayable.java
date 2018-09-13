package ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;

public abstract class InputManagerPlayable extends InputManager {
	
	public InputManagerPlayable(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

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
	    
	    if (key == KeyEvent.VK_ESCAPE) {
	    	System.out.println("*****  Returning to Menu.. *****");
	    	game.changeState(new InputManagerMenu(game));
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
}
