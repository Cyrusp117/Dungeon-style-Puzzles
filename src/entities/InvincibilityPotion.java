package entities;


import javafx.scene.input.KeyCode;

public class InvincibilityPotion extends Entity {
	int durability;
	public InvincibilityPotion(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.I;
		this.durability = 2+1; // actual durability is two
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	
	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		return null;
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
