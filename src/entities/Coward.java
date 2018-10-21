package entities;
import java.util.ArrayList;

public class Coward extends Enemy {

	public Coward(Coordinate position) {
		super(position);
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g,Coordinate closetPickup,ArrayList<Entity> entities) {
		
		Coordinate move;
		
		if (g.getDistance(co, position) > Math.sqrt(2)) { 
			move = co;
		} else {
			move = g.moveAway(co,this.position);
		
			
		}
		
		return move;
	}
	
	public String getName() {
		return "Coward";
	}

}
