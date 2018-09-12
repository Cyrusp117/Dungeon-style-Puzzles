package entities;

public class Wall {
	//private BufferedImage sprite;
	private Coordinate position;
	private boolean isBlockable;
	
	public Wall (Coordinate position, boolean isBlockable) {
		this.position = position;
		this.isBlockable = true;
	}
	
	/**
	 * 
	 * @return position, the Coordinate of the Wall
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	/**
	 * 
	 * @return the x Coordinate of the Wall. (int)
	 */
	public int returnX() {
		return position.getxPosition();
	}
	
	/**
	 * 
	 * @return the y Coordinate of the Wall. (int)
	 */
	public int returnY() {
		return position.getyPosition();
	}
	
	/**
	 * 
	 * @param position the Coordinate to inspect collision with
	 * @return true if Collision will occur, false otherwise.
	 */
	public boolean willCollide(Coordinate position) {
		if(position.equals(this.position)) return true;
		return false;
	}
}
