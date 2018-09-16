package entities;

import java.awt.event.KeyEvent;

public class Bomb extends Entity {
//	private static int numOfBombs;
//	private int BombId;
	public Bomb(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_U;
//		BombId = numOfBombs;
//		numOfBombs++;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		//System.out.println("Num Of Bombs: "+ player.getNumOfBombs());
		return true;
	}
	
	
	public String getName() {
		return "Bomb";
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