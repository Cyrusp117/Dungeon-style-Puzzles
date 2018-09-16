package entities;

import java.awt.event.KeyEvent;

public class Exit extends Entity {

	public Exit(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_E;
	}

	@Override
	public String getName() {
		return "Exit";
	}
	
}
