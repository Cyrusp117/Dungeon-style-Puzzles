package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Sword extends Entity {
	int durability;
	public Sword(Coordinate position) {
		super(position);
		this.durability = 5;
		this.keyCode = KeyCode.S;
		this.movable = false;
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	public Coordinate interactWithPlayer(Player player) {
		if(!player.hasItem("Sword")) {
			player.pickUp(this);
			return null;
		}
		System.out.println("You already have sword, you can only carry one sword!!");
		return position;
	}
	
	public int getDurability() {
		return durability;
	}
	
	public void reduceDurability() {
		durability--;
	}

	public String getName() {
		return "Sword";
	}
	
}
