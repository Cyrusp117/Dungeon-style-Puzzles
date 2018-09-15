package entities;

import java.awt.event.KeyEvent;

public class Arrow extends Entity {
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
	
}
