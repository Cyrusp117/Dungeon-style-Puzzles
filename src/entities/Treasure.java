package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Treasure extends Entity {
	
	private boolean gotByGoblin = false;
	
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
	
	public boolean getStatus() {
		return gotByGoblin;
	}
	
	public void setStatus() {
		this.gotByGoblin = true;
	}
	
}
