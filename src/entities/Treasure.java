package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Treasure extends Entity {
	public Treasure(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.T;
		this.movable = false;
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	@Override
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "Treasure";
	}
	
}
