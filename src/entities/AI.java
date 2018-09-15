package entities;

public abstract class AI extends Entity {
	int state = 1; // 1 = Alive, 0 = Dead
	
	public AI(Coordinate position) {
		super(position);
	}
	
	public boolean isAlive() {
		if (state == 1) return true;
		return false;
	}
	
	public abstract Coordinate move ();
}