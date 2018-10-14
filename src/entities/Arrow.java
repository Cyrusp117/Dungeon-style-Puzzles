package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Arrow extends Entity {
	private int dx = 0;
	private int dy = 0;
	
	public Arrow(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.A;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
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
		int newX = getX() + dx;
		int newY = getY() + dy;
		Coordinate newPos = new Coordinate(newX, newY);
		return newPos;
	}


	
}
