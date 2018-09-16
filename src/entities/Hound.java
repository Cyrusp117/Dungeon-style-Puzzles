package entities;

public class Hound extends Enemy {
    private Hunter hunter;
	
	public Hound(Coordinate position,Hunter hunter) {
		super(position);
		this.hunter = hunter;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g) {
		Coordinate target = null;
		if (hunter != null) {
			
			target = g.between(co,hunter.getPosition());
			
		} else {
			target = co;
		}
		// TODO Auto-generated method stub
		return target;
	}
	
	public void setHunter (Hunter hunter) {
		this.hunter = hunter;
	}
	
	public String getName() {
		return "Hound";
	}
}
