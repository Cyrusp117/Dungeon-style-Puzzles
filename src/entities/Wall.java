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
	
	
	public boolean interactWithPlayer(Player player) {
		player.setPosition(player.getOldPosition());
		System.out.println("There is a wall here... Moving back");
		return false;

	}

	@Override
	public String getName() {
		return "Wall";
	}
	
	
}
