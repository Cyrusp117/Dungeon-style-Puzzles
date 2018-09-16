package entities;

public class Strategist extends Enemy {

	public Strategist(Coordinate position) {
		super(position);
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g) {
		Coordinate target;
		if (g.isAdjacent(co, this.position)) {
		    target = this.position;
		} else
			target = co;
		return target;
		
	}

	@Override
	public String getName() {
		return "Strategist";
	}

}
