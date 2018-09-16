package entities;

import java.awt.event.KeyEvent;

public class Boulder extends Entity {
	private Coordinate oldPos; 
	public Boulder(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_B;
	}
	
	public boolean interactWithPlayer(Player player) {
		System.out.println("Boulder hit");
		int dx = player.getDx();
		int dy = player.getDy();
		int curX = position.getxPosition();
		int curY = position.getyPosition();
	    Coordinate newPos = new Coordinate(curX + dx, curY + dy);
	    this.setOldPosition(this.position);
	    this.setPosition(newPos);
	    return false;
	}
	
	public boolean interactWithBomb() {
		//delete this boulder
		return true;
	}
	
	public String getName() {
		return "Boulder";
	}

	/**
	 * Reverts position of player and boulder to a turn ago
	 * @param Player
	 */
	public void revert(Player player) {
		this.setPosition(this.oldPos);
		player.setPosition(player.getOldPosition());
	}

	
	/**
	 * 
	 * @param sets oldPos to position
	 */
	public void setOldPosition(Coordinate position) {
		this.oldPos = position;
	}
	/**
	 * 
	 * @return previous position of Entity
	 */
	public Coordinate getOldPosition () {
		return oldPos;
	}
	
}
