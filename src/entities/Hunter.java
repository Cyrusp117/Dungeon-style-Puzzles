

package entities;
import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Hunter extends Enemy {

	public Hunter(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.H;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co,Graph g,Coordinate closestPickup) {
		Coordinate target;
		if (g.isAdjacent(co, this.position)) {
		    target = this.position;
		} else
			target = co;
		return target;
		//System.out.println("Closest point " +closestPickup.getX() + " " + closestPickup.getY());
		//return closestPickup;
	}
	




	@Override
	public String getName() {
		return "Hunter";
	}
	
//	@Override
//	public boolean interactWithPlayer(Player player) {
//		if(player.hasItem("Sword")) {
//			player.hitUsingSword();
//			return true;
//		} else if (player.hasItem("InvincibilityPotion")) {
//			return true;
//		} else {
//			player.setState(0);
//		}
//		return false;
//
//	}
	
	
	
	

}
