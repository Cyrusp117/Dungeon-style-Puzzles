package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// to be refactored into the Game
public class InputManager implements KeyListener {
	private int dx = 0;
	private int dy = 0;
	private Game game;
	public InputManager() {
		
	}

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

	public InputManager(Game game) {
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        dx = -32;
	        dy = 0;
	        game.newTurn();
	        System.out.println("LEFT");
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 32;
	        dy = 0;
	        game.newTurn();
	        System.out.println("RIGHT");
	    }

	    if (key == KeyEvent.VK_UP) {
	        dy = -32;
	        dx = 0;
	        game.newTurn();
	        System.out.println("UP");
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        dy = 32;
	        dx = 0;
	        game.newTurn();
	        System.out.println("DOWN");
	    }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	

}
