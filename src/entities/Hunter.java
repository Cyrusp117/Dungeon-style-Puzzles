
package entities;
import java.awt.event.KeyEvent;

public class Hunter extends Enemy {

	public Hunter(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_H;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co,Graph g) {
		Coordinate target;
		if (g.isAdjacent(co, this.position)) {
		    target = this.position;
		} else
			target = co;
		return target;
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
	
	
	
	

}

