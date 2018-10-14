
package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Pit extends Entity{
	public Pit(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.P;
		this.movable = false;
	}

	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	public Coordinate interactWithPlayer(Player player) {
		if(!player.hasItem("HoverPotion")) {
			player.setState(0); // player is dead
		}
		return position;
	}
	
	public Coordinate interact(Boulder boulder) {
		//delete boulder
		return null;
	}
	
	public String getName() {
		return "Pit";
	}
	
}

