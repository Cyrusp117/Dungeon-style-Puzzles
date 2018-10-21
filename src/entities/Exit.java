package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Exit extends Entity {

	public Exit(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.E;
	}

	@Override
	public String getName() {
		return "Exit";
	}
	
	@Override
	public boolean canBePlacedOnTop(Entity entity) {
		if (entity instanceof Player) {return true;}
		return false;
	}
	
}
