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
	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		return null;
	}
	
	public String getName() {
		return "Treasure";
	}
	
}
