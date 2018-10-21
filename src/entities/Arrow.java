package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Arrow extends Entity {
	private ArrowState arrowState;
	private int dx = 0;
	private int dy = 0;
	
	public Arrow(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.A;
		this.arrowState = new ArrowStationary();
	}
	/**
	 * @param player Player picks up arrow upon interaction
	 */
	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		return null;
	}
	
	/**
	 * 
	 * @return new Coordinate of arrow after moving in its set direction
	 * default is stationary, when it is placed on the game map
	 */
	public Coordinate move() {
		return Coordinate.addCoordinates(arrowState.getDirection(), this.position);
	}
	
	/**
	 * 
	 * @param key Digits 3-6 set the direction of the arrow so that
	 * it moves at the start of every new game turn
	 */
	public void setDirection(KeyCode key) {
		if (key.equals(KeyCode.DIGIT3)) {
			arrowState = new ArrowUpwards();
		} else if (key.equals(KeyCode.DIGIT4)) {
			arrowState = new ArrowDownwards();
		} else if (key.equals(KeyCode.DIGIT5)) {
			arrowState = new ArrowLeft();
		} else if (key.equals(KeyCode.DIGIT6)) {
			arrowState = new ArrowRight();
		}
	}

	/**
	 * 
	 * @param key checks if the key code is used for arrow directions
	 * @return true if the above is true
	 */
	public static boolean checkKeyCode(KeyCode key) {
		if ( key.equals(KeyCode.DIGIT3) || key.equals(KeyCode.DIGIT4) || key.equals(KeyCode.DIGIT5) || key.equals(KeyCode.DIGIT6)) {
			return true;
		}
		return false;
	}

	
	
	public String getName() {
		return "Arrow";
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}



	
}
