
package entities;
import java.util.ArrayList;

public abstract class Enemy extends Entity{

    //protected Coordinate position;
    
	public Enemy (Coordinate position){
		super(position);
	    this.movable = true;
	}
	
	public abstract Coordinate getTargetSpace(Coordinate co,Graph g,Coordinate closestPickup, ArrayList<Entity> entities);
	
	
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
	

	//@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	//@Override
	public int getX() {
		// TODO Auto-generated method stub
		return position.getX();
	}

	//@Override
	public int returnY() {
		// TODO Auto-generated method stub
		return position.getY();
	}

	//@Override
	public boolean willCollide(Coordinate otherPos) {
		// TODO Auto-generated method stub
		return position.equals(otherPos);
	}
	
	@Override
	public boolean interactWithBomb() {
		return true;
	}
	
	
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

	