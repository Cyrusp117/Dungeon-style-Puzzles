package entities;


import javafx.scene.input.KeyCode;
import ui.Game;

public class Boulder extends Entity {
	private Coordinate oldPos;
	public Boulder(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.B;
	}
	
	public Coordinate interactWithPlayer(Player player) {

//		int dx = player.getDx();
//		int dy = player.getDy();
//		int curX = position.getX();
//		int curY = position.getY();
//	    Coordinate newPos = new Coordinate(curX + dx, curY + dy);
	    this.setOldPosition(this.position);
//	    this.setPosition(newPos);
	    return player.getMove();
	}
	
	public boolean interactWithBomb() {
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

	public boolean canBePlacedOnTop(Entity entity) {
		if (entity instanceof FloorSwitch) {	return true; }
		return false;
	}
	
//	public boolean isValidInteraction(Entity entity) {
//		System.out.println(entity);
//		if (entity instanceof Pit) {
//			return true;
//		} else if (entity instanceof Player) {
//			return true;
//		} else if (entity == null) {
//			return true;
//		}
//		//return super.isValidInteraction(entity);
//	}
	
	public Coordinate interact(Entity entity) {
		valid = true;
		if(entity instanceof Pit){
			return null;
		} else if (entity instanceof FloorSwitch) {
			return entity.getPosition();
		} else {
			valid = false;
		}
		return position;
	}
	
	public boolean isValid() {
		return valid;
	}
	
}
