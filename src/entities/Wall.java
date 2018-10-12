package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Wall extends Entity {
	//private BufferedImage sprite;
	
	public Wall (Coordinate position)  {
		super(position);
		this.keyCode = KeyCode.W;
		this.movable = false;
	}
	public Coordinate move(Coordinate co, Graph g) {
		return position;
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
