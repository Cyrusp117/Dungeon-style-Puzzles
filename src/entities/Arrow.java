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
	
	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		return null;
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

	public Coordinate move() {
		return Coordinate.addCoordinates(arrowState.getDirection(), this.position);
	}
	
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

	public static boolean checkKeyCode(KeyCode key) {
		if ( key.equals(KeyCode.DIGIT3) || key.equals(KeyCode.DIGIT4) || key.equals(KeyCode.DIGIT5) || key.equals(KeyCode.DIGIT6)) {
			return true;
		}
		return false;
	}


	
}
