package entities;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;



public class Bomb extends Entity {
//	private static int numOfBombs;
//	private int BombId;
	
	private boolean active;
	private int turnsLeft;
	public static final ArrayList<Coordinate> directions = new ArrayList<>();


	
	public Bomb(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.U;
		this.turnsLeft = 4;
//		BombId = numOfBombs;
//		numOfBombs++;
	}
	
	public Coordinate interactWithPlayer(Player player) {
		if(!active) {
			player.pickUp(this);
			return null;
		}
		//System.out.println("Num Of Bombs: "+ player.getNumOfBombs());
		return position;
	}
	
	/**
	 * 
	 * @return ArrayList of Coordinates that bomb's explosion affects
	 */
	public ArrayList<Coordinate> affectedAreas() {
		ArrayList<Coordinate> affectedAreas = new ArrayList<>();
		affectedAreas.add(position.moveDown());
		affectedAreas.add(position.moveUp());
		affectedAreas.add(position.moveRight());
		affectedAreas.add(position.moveLeft());
		return affectedAreas;
	}
	
	public String getName() {
		return "Bomb_" + Integer.toString(getTurnsLeft());
	}

	/**
	 * 
	 * @return True if bomb is active
	 */
	public boolean isLit() {
		return active;
	}
	
	/**
	 * Sets bomb to active state
	 */
	public void light() {
		active = true;
	}
	
	/**
	 * 
	 * @return Turns to explosion
	 */
	public int getTurnsLeft() {
		return turnsLeft;
	}
	
	/**
	 * Reduce turns left to explosion
	 */
	public void tickTock() {
		turnsLeft--;
	}
	
}