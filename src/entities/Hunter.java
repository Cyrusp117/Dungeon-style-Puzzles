package entities;

public class Hunter extends Enemy {

	public Hunter(Coordinate position,String name) {
		super(position,name);
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
	
	public String getName() {
		return super.getName();
	}

}
