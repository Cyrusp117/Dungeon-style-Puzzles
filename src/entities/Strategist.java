package entities;
import java.util.ArrayList;

public class Strategist extends Enemy {

	public Strategist(Coordinate position) {
		super(position);
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g,Coordinate closestPickup,ArrayList<Entity> entities) {
		if (!g.availablePoint(closestPickup.getX(), closestPickup.getY())) {
			closestPickup = co;
		}
		//System.out.println(closestPickup.getX() + " " + closestPickup.getY());
		return closestPickup;
	}

	@Override
	public String getName() {
		return "Strategist";
	}
	

}
