

package entities;

import java.awt.event.KeyEvent;

public class Pit extends Entity{
	public Pit(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_P;
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
=======

package entities;

import java.awt.event.KeyEvent;

public class Pit extends Entity{
	public Pit(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_P;
	}

	public boolean interactWithPlayer(Player player,int x, int y) {
		if(player.hasItem("HoverPotion")) {
			int newX = position.getxPosition() + x;
		    int newY = position.getyPosition() + y;
		    Coordinate newPos = new Coordinate(newX, newY);
		    player.setPosition(newPos);
		}else{
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

