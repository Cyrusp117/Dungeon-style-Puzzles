package ui;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


// to be refactored into the Game
public abstract class InputManager implements KeyListener {
	protected Application app;
	
	public InputManager(Application app) {
		this.app = app;
	}
	
	public abstract void keyTyped(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void keyPressed(KeyEvent e);
	public JFrame getFrame() { 
		return app.getFrame();
	}

//	/**
//	 * Action when keys are pressed
//	 */
//	public abstract void keyPressed(KeyEvent e);
//
//	
//	/**
//	 * Actions when a key is released (currently nothing)
//	 */
//	@Override
//	public abstract void keyReleased(KeyEvent e);
//	
//
//	/**
//	 * Actions when a key is typed (currently nothing)
//	 */
//	@Override
//	public abstract void keyTyped(KeyEvent e);


}
