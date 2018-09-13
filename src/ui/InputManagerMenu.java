package ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;

public class InputManagerMenu extends InputManager {
	
	public InputManagerMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Actions when a key is pressed
	 */
	public void keyPressed(KeyEvent e) {
		Frame curFrame = game.getFrame();
		int key = e.getKeyCode();
	    if (key == KeyEvent.VK_1) {
	    	game.changeState(new InputManagerDesigner(game));
	    	System.out.println("Designer Mode Controls: Escape to return to menu, F1 - Treasure Placement, H - Hunter Placement, B - Backpack");
	    }
	    if (key == KeyEvent.VK_2) {
	    	game.changeState(new InputManagerPlayer(game));
	    }
	}
	


	
	/**
	 * Actions when a key is released (currently nothing)
	 */

	public void keyReleased(KeyEvent e) {
		
	}
	

	/**
	 * Actions when a key is typed (currently nothing)
	 */

	public void keyTyped(KeyEvent e) {
		
	}



}
