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
	

	@Override
	public String getName() {
		return "Wall";
	}
	
	
}
