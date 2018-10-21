package entities;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class TreasureGoblin extends Enemy {
	
	private Treasure goblinTreasure = null;

	public TreasureGoblin(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.G;
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g, Coordinate closestPickup, ArrayList<Entity> entities) {
		Coordinate target;
		int closestTreasurePath = -1;
		Treasure closest = null;
		int curLength;
		
		if (goblinTreasure != null) {
			//distracted by treasure
			if (g.sizeBFS(position, co) < 2) {
				target = g.moveAway(co, position);
			} else {
				target = g.randomCoordinate(); //does not care where it goes
			}
			
		} else { //does not have treasure, very cautious of player
		    	for (Entity object: entities) {
					if (object instanceof Treasure) {
						Treasure tres = (Treasure) object;
						g.addPointAndEdges(tres.getPosition());
						//g.printGraph();
						//System.out.println("looking for treasure"); //test
					    curLength = g.sizeBFS(position, tres.getPosition());
			
					    g.removePointAndEdges(tres.getPosition());
					    if (closestTreasurePath == -1 || curLength < closestTreasurePath ) {
						    closestTreasurePath = curLength;
						    closest = tres;
					    }
					}
				}
		    	if (closestTreasurePath != -1) {
		    		int size = g.sizeBFS(position, co);
					
				    if (size < 4 && size != -1) {
				    	//System.out.println("scared of player"); //test
				    	target = g.moveAway(co, position);
				    	
				    } else if (g.isAdjacent(position, closest.getPosition())) {
						System.out.print("We also end up inside the required function");
						target = closest.getPosition();
						g.addPointAndEdges(closest.getPosition());
						this.keyCode = KeyCode.L;
						goblinTreasure = closest; //not sure what do do about goblin taking the treasure
						 
					} else {
					    g.addPointAndEdges(closest.getPosition());
					    target = closest.getPosition(); //target is closest treasure
					}
				} else {
					//search for other entities with treasure and take it, if none exist, just hide
					//will implement if have time
					target = co;
				}
		    	
		    
		}
		
		System.out.println("Target coordinate is : (" +  target.getX() +"," + target.getY() + ")");
		return target;
	}

	@Override
	public String getName() {
		
		return "Treasure Goblin";
	}
	
	public Treasure getTreasure() {
		return this.goblinTreasure;
	}
	
	@Override
	public Coordinate interactWithPlayer(Player player) {
		if(player.hasItem("Sword")) {
			player.hitUsingSword();
			if (getTreasure() != null) {
				player.pickUp(goblinTreasure);
			}
			return null;
		} else if (player.hasItem("InvincibilityPotion")) {
			if (getTreasure() != null) {
				player.pickUp(goblinTreasure);
			}
			return null;
		} else {
			player.setState(0);
		}
		return position;

	}

}
