package entities;

import java.awt.event.KeyEvent;

public class Arrow extends Entity {
	int dx;
	int dy;
	
	public Arrow(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_A;
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
	
	
}
