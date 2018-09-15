package entities;

import java.awt.event.KeyEvent;

public class InvincibilityPotion extends Entity {
	public InvincibilityPotion(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_I;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "InvincibilityPotion";
	}
	
}
