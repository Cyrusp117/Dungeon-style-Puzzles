package entities;

import java.awt.event.KeyEvent;

public class InvincibilityPotion extends Entity {
	int durability;
	public InvincibilityPotion(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_I;
		this.durability = 2+1; // actual durability is two
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "InvincibilityPotion";
	}

	public int getDurability() {
		return durability;
	}
	
	public void reduceDurability() {
		this.durability--;
	}
}
