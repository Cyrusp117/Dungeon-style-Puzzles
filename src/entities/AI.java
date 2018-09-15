package entities;

public abstract class AI extends Entity {
	
	public AI(Coordinate position) {
		super(position);
	}
	
//	public boolean isAlive() {
//		if (state == 1) return true;
//		return false;
//	}
	
	public abstract Coordinate move ();
}