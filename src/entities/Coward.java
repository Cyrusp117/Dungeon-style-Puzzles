package entities;

public class Coward extends Enemy {

	public Coward(Coordinate position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g) {
		int ax = co.getX()/32;
		int ay = co.getY()/32;
		int bx = this.position.getX()/32;
		int by = this.position.getY()/32;
		Coordinate move;
		if (Math.hypot(ax-bx,ay-by) > Math.sqrt(2)) { //Math.sqrt(2)
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
