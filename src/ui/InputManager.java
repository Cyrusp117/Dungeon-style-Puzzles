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
public abstract class InputManager implements KeyListener {
	protected int dx = 0;
	protected int dy = 0;
	protected Game game;

	public InputManager(Game game) {
		this.game = game;
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
