

package entities;
import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class Hunter extends Enemy {

	public Hunter(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.H;
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co,Graph g,Coordinate closestPickup, ArrayList<Entity> entities) {
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
	
	

}
