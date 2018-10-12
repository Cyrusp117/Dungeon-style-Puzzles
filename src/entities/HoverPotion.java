
package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class HoverPotion extends Entity {
	public HoverPotion(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.F;
		this.movable = false;
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "HoverPotion";
	}
}
