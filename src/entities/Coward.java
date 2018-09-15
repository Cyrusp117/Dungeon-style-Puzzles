package entities;

public class Coward extends Enemy {

	public Coward(Coordinate position,String name) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g) {
		int ax = co.getxPosition();
		int ay = co.getyPosition();
		int bx = position.getxPosition();
		int by = position.getyPosition();
		Coordinate move;
		if (Math.hypot(ax-bx,ay-by) > Math.sqrt(2)) {
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
