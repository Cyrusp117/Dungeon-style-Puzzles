package entities;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public abstract class Entity {
	protected Coordinate oldPos; 
	protected Coordinate position;
	protected KeyCode keyCode;
	protected boolean movable;
	protected boolean valid;
	public Entity(Coordinate position) {
		this.position = position;
	}
	
	public Entity() {
		
	}

	/**
	 * 
	 * @param player - the player that this entity interacts with
	 * @return	new position of this entity (null if destroyed)
	 */
	public Coordinate interactWithPlayer(Player player) {
		return position;
	}
	
	/**
	 * 
	 * @return true if entity dies to a bomb explosion
	 */
	public boolean interactWithBomb() {
		return false;
	}

	
	public Coordinate invincibilityMove(Coordinate co, Graph g) {
		
		g.addCoordinate(this.position); //need this?
		g.generateEdges();
		Coordinate move = g.moveAway(co,this.position);
		
		move = g.BFS(this.position, move,co);
		
		
		return move;
	}

	/**
	 * 
	 * @return string containing the name (and state in the case of bomb)
	 *  of the entity
	 */
	public abstract String getName();

	/**
	 * 
	 * @return position, the Coordinate of the current entity
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	/**
	 * 
	 * @return the x coordinate of the entity (int)
	 */
	public int getX() {
		return position.getX();
	}
	
	/**
	 * 
	 * @return the y coordinate of the entity (int)
	 */
	public int getY() {
		return position.getY();
	}

	/**
	 * 
	 * @param otherPos
	 * @return true if this entities position equals otherPos
	 */
	public boolean willCollide(Coordinate otherPos) {
		return position.equals(otherPos);
		
	}
	

//	public KeyCode getKeyCode() {
//		return this.keyCode;
//	}
	
//	public String getDesignerDescription() {
//
//		return(this.getName() + " - " + getKeyCode().getName());
//
//	}

	/**
	 * 
	 * @param entity
	 * @return true if entity can be placed on top of this entity
	 */
	public boolean canBePlacedOnTop(Entity entity) {
		return false;
	}
	
	/**
	 * 
	 * @return previous position of Entity
	 */
	public Coordinate getOldPosition () {
		return oldPos;
	}


	/**
	 * 
	 * @param entity - the entity to interact with
	 * @return new position of this entity (null if destroyed)
	 */
	public Coordinate interact(Entity entity) {
		return position;
	}
	
	/**
	 * 
	 * @param sets oldPos to position
	 */
	public void setOldPosition(Coordinate position) {
		this.oldPos = position;
	}
	/**
	 * @returns True if the last interaction was valid
	 * 
	 */
	public boolean isValid() {
		return true;
	}
}