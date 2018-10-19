
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
		//g.addCoordinate(position); //need this?
		//g.generateEdges();
		//boolean check = false;
		if (move == co && g.isAdjacent(position, co)) {
			move = position;
		} else if (move != position){
			//test 
			System.out.println("Move coordinate is : (" +  move.getX() +"," + move.getY() + ")");
			System.out.println("position coordinate is : (" +  position.getX() +"," + position.getY() + ")");
		    move = g.BFS(this.position, move,co); 
		    //check = true;
		} 
		//if attempt is made to move to spot near player but cannot, will attempt any move it can do player
		//if (check && move == position) {
		//	move = g.BFS(this.position, co, co);
		//}
		
		
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

	