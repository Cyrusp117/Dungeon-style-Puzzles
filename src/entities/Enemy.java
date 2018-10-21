
package entities;
import java.util.ArrayList;

public abstract class Enemy extends Entity{

    
	public Enemy (Coordinate position){
		super(position);
	    this.movable = true;	
	}
	/**
	 * @param the position of player, a graph of the board, the closest item to the player that they can interact with, and the list of entities in the game
	 * @return the desired move coordinate
	 */
	public abstract Coordinate getTargetSpace(Coordinate co,Graph g,Coordinate closestPickup, ArrayList<Entity> entities);
	
	/**
	 * @param the position of player, a graph of the board, the closest item to the player that they can interact with, and the list of entities in the game
	 * @return the final move coordinate for the enemy
	 */
	public Coordinate move(Coordinate co,Graph g,Coordinate closestPickup, ArrayList<Entity> entities) {
		g.addCoordinate(position); //doing this before hand solves not being able to use in targetSpace stuff
		g.generateEdges();
		Coordinate move = getTargetSpace(co,g,closestPickup, entities);
		
		if (move == co && g.isAdjacent(position, co)) {
			move = position;
		} else if (move != position){
			
		    move = g.BFS(this.position, move,co); 
		  
		} 
		
		
		return move;
	}
	

	
	public Coordinate getPosition() {

		return position;
	}
	
	public int getX() {
	
		return position.getX();
	}


	public int returnY() {
		
		return position.getY();
	}

	public boolean willCollide(Coordinate otherPos) {
	
		return position.equals(otherPos);
	}
	
	@Override
	public boolean interactWithBomb() {
		return true;
	}
	
	/**
	 * 
	 * @param player - the player to interact with
	 * @return	new position of this entity (null if destroyed)
	 */
	public Coordinate interactWithPlayer(Player player) {
		if(player.hasItem("Sword")) {
			player.hitUsingSword();
			return null;
		} else if (player.hasItem("InvincibilityPotion")) {
			return null;
		} else {
			player.setState(0);
		}
		return position;

	}
	

}

	