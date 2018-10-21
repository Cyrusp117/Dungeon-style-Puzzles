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
			//System.out.println("get distance gives " + g.getDistance(co,position));
			move = co;
		} else {
			System.out.println("Coward is running away");
			move = g.moveAway(co,this.position);
		
			
		}
		
		return move;
	}
	
	public String getName() {
		return "Coward";
	}

}
