
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
	public boolean interactWithPlayer(Player player) {
		if(!player.hasItem("HoverPotion")) {
			player.setState(0); // player is dead
		}
		return false;
	}
	
	public boolean interactWithBoulder(Boulder boulder) {
		//delete boulder
		return true;
	}
	
	public String getName() {
		return "Pit";
	}
	
}

