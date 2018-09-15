package entities;

import java.awt.event.KeyEvent;

public class Hunter extends AI {

	public Hunter(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_H;
	}

	@Override
	public String getName() {
		return "Hunter";
	}
	
	@Override
	public boolean interactWithPlayer(Player player) {
		if(player.hasItem("Sword")) {
			return true;
		} else {
			player.setState(0);
		}
		return false;

	}
	
	public Coordinate move() {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	

}
