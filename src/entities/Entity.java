package entities;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public abstract class Entity {
	protected Coordinate oldPos; 
	protected Coordinate position;
	protected KeyCode keyCode;
	protected boolean movable;
	public Entity(Coordinate position) {
		this.position = position;
	}
	
	public Entity() {
		
	}

//	public Coordinate move(Coordinate co, Graph g) {
//		return null;
//	}


	public Coordinate interactWithPlayer(Player player) {
		return position;
	}
	
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

	public boolean willCollide(Coordinate otherPos) {
		return position.equals(otherPos);
		
	}
	
	public KeyCode getKeyCode() {
		return this.keyCode;
	}
	
	public String getDesignerDescription() {

		return(this.getName() + " - " + getKeyCode().getName());

	}

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

	public boolean isValidInteraction(Entity atNewEntityPos) {
		//if (atNewEntityPos.size() == 0) { return true; }
		return false;
	}

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
}