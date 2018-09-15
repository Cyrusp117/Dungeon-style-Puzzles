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
	
	public abstract void keyPressed(KeyEvent e);
	public JFrame getFrame() { 
		return app.getFrame();
	}




}
