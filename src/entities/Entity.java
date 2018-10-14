package entities;

import javafx.scene.input.KeyCode;

public abstract class Entity {
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


	public boolean interactWithPlayer(Player player) {
		return false;
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
	
}