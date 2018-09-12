package entities;

public class Wall {
	//private BufferedImage sprite;
	private Coordinate position;
	private boolean isBlockable;
	
	public Wall (Coordinate position, boolean isBlockable) {
		this.position = position;
		this.isBlockable = true;
	}
	
	public Coordinate getPosition(){
		return position;
	}
	
	public int returnX() {
		return position.getxPosition();
	}
	
	public int returnY() {
		return position.getyPosition();
	}
	
	public boolean willCollide(Coordinate position) {
		if(position.equals(this.position)) return true;
		return false;
	}
}
