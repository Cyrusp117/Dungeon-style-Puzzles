package entities;


import java.awt.event.KeyEvent;
import java.util.ArrayList;



public class Bomb extends Entity {
//	private static int numOfBombs;
//	private int BombId;
	
	private boolean active;
	private int turnsLeft;
	public static final ArrayList<Coordinate> directions = new ArrayList<>();


	
	public Bomb(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_U;
		this.turnsLeft = 3;
//		BombId = numOfBombs;
//		numOfBombs++;
	}
	
	public boolean interactWithPlayer(Player player) {
		if(!active) {
			player.pickUp(this);
			return true;
		}
		//System.out.println("Num Of Bombs: "+ player.getNumOfBombs());
		return false;
	}
	
	public ArrayList<Coordinate> affectedAreas() {
		ArrayList<Coordinate> affectedAreas = new ArrayList<>();
		affectedAreas.add(position.moveDown());
		affectedAreas.add(position.moveUp());
		affectedAreas.add(position.moveRight());
		affectedAreas.add(position.moveLeft());
		return affectedAreas;
	}
	
	public String getName() {
		return "Bomb";
	}

	public boolean isLit() {
		return active;
	}
	
	public void light() {
		active = true;
	}
	
	public int getTurnsLeft() {
		return turnsLeft;
	}
	
	public void tickTock() {
		turnsLeft--;
	}
//	/**
//	 * @return the numOfBombs
//	 */
//	public static int getNumOfBombs() {
//		return numOfBombs;
//	}
//
//	/**
//	 * @return the bombId
//	 */
//	public int getBombId() {
//		return BombId;
//	}

	
}