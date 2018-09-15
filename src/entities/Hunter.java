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
		Coordinate playerPos = player.getPosition();
		if(playerPos.equals(this.position)) {
			player.setState(0);
		}
		return false;

	}
	
	public Coordinate move() {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	

}
