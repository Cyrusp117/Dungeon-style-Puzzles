
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
	
	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		return null;
	}
	
	public String getName() {
		return "HoverPotion";
	}
}
