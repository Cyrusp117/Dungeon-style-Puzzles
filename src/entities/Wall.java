package entities;

import java.awt.event.KeyEvent;

public class Wall extends Entity {
	//private BufferedImage sprite;
	
	public Wall (Coordinate position)  {
		super(position);
		this.keyCode = KeyEvent.VK_W;
	}
	
	/**
	 * 
	 * @return position, the Coordinate of the Wall
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	
//	public boolean interactWithPlayer(Player player) {
//		int newX = position.getxPosition() + x;
//	    int newY = position.getyPosition() + y;
//	    Coordinate newPos = new Coordinate(newX, newY);
//	    this.setPosition(newPos);
//	    return false;
//	}

	@Override
	public String getName() {
		return "Wall";
	}
	
	
}
