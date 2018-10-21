package ui;
import entities.*;
import java.util.ArrayList;

public class Game{ 								
	private static final Entity NULL = null;
	private int width, height;					//Width and height of the app window
	private Player player = null;					//Tracking the player entity
//	private InputManagerPlayer playerInput;	//KeyListener, takes in key inputs
	private ArrayList<Entity> entities;//Array List of Entities, tracks all entities in the current game
	private boolean win = false;
	private ArrayList<ArrayList<Coordinate>> map;
	//private Graph g; //new in graph implementation
	// Need to implement generic iterator
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.map = new ArrayList<ArrayList<Coordinate>>();
		generateMap();
	}
	
	//Copy constructor
	public Game(Game otherGame) {
		this.width = otherGame.width;
		this.height = otherGame.height;
		this.entities = otherGame.entities;
		this.map = otherGame.map;
	}
	
	public void update() {						//Updates the state of the game
		// Arrow collisions, bomb explosions, player inventory update
		itemInteractions();
		// Moves player and interacts.. (or doesnt if invalid move)
		movePlayer();
		// Checks current state of the game after any interactions
		
		int allTreasure = 1;
		int allSwitch = 1;
		int allEnemy = 1;

		Coordinate position;
        Coordinate closestPickup;
        
		//need list of bone locations
        //then hound will use this later and determine its target
		win = false;

		for (Entity entity : entities) {
			
			// Checks all killed win condition
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				if (player.hasItem("InvincibilityPotion")) {
					position = enemy.invincibilityMove(player.getPosition(), generateGraph());
				} else {
					
					closestPickup = closestPickup(enemy);
					//System.out.println("hi\n\n\n");
				    position = enemy.move(player.getPosition(), generateGraph(),closestPickup,entities); //where I generate graph before the move
				    
				    if (getFirstEntity(position) instanceof Treasure && entity instanceof TreasureGoblin) { //test if there will be overlap of treasure and goblin
				    	//entities.remove(getFirstEntity(position)); //remove the treasure from list of entities as goblin now has it
				    	deleteEntity(getFirstEntity(position));
				    	System.out.println("So we made it here");
				    	TreasureGoblin goblin = (TreasureGoblin)entity;
				    	System.out.println("Goblin has " +goblin.getTreasure().getName());
				    	assert(getFirstEntity(position) == null);
				    }
				}
				if ( getFirstEntity(position) == null && !(player.getPosition().equals(position)) ) {
					moveEntity(enemy, position);
				} 

				allEnemy = 0;

				
			}
			
			// Checks if all treasure has been picked up
			if (entity instanceof Treasure){
				allTreasure = 0;
				//also check treasure goblin for this condition
			} else if (entity instanceof TreasureGoblin) {
				TreasureGoblin goblin = (TreasureGoblin)entity;
				if(goblin.getTreasure() != null) {
					allTreasure = 0;
				}
			}
			
			// Checks if all floor switches are active (have a boulder on them)
//			if (entity instanceof FloorSwitch) {		// could be a lot neater if we had an array of FloorSwitch ?
//				FloorSwitch fs = (FloorSwitch)entity; 
//				fs.deactivate();
//				if(getEntity(fs.getPosition(), Boulder.class) != NULL) {
//					fs.activate();
//				}
//				if(!fs.getState()) {
//					allSwitch = 0;
//				}
//			}
			if (entity instanceof FloorSwitch) {		// could be a lot neater if we had an array of FloorSwitch ?
				FloorSwitch fs = (FloorSwitch)entity; 
				fs.deactivate();
				if(getEntityExcept(fs.getPosition(), fs) != NULL) {
					fs.activate();
				}
				if(!fs.getState()) {
					allSwitch = 0;
				}
			}
			
			// Checks general win condition
			if (entity instanceof Exit){
				if(entity.getPosition().equals(player.getPosition())) {
					if (player.isAlive()) {
						win = true;
					}
				}
			}
		}
		
		if (allTreasure == 1 && allSwitch == 1 && allEnemy == 1) {
			win = true;
		}
		if (!player.isAlive()) {
			System.out.println("Player is currently dead");
		}
		if(allTreasure == 1) {
			System.out.println("All treasure has been collected");
		}
		if(allSwitch == 1) {
			System.out.println("All Switches active");
		}
		if(allEnemy == 1) {
			System.out.println("All enemies dead");
		}
		System.out.println("");

	}
	
	public void moveEntity(Entity entity, Coordinate newPos) {
		entity.setOldPosition(entity.getPosition());
		Coordinate newMapTile = getMapCo(newPos);
		Coordinate oldMapTile = getMapCo(entity);
		oldMapTile.removeEntity(entity);
		entity.setPosition(newMapTile);
		newMapTile.addEntity(entity);
	}
	


	

	
	/**
	 * Moves the player
	 */
	public void movePlayer() {
		Coordinate newPlayerPos = player.getMove();
		if(isOutOfBounds(newPlayerPos)) {
			return;
		}
		ArrayList<Entity> entities = getEntities(newPlayerPos);
		Entity interactable = null;
		for (Entity curEntity : entities) {
			// maybe add an canInteractWithPlayer?
			if (curEntity instanceof FloorSwitch) {
				continue;
			} else {
				interactable = curEntity;
			}
		}
		moveEntity(player, newPlayerPos);
		
		if (interactable!=NULL) {
			System.out.println("CurPos has a: " + interactable.getName());
			Coordinate newInteractablePos = interactable.interactWithPlayer(player);
			if(newInteractablePos == null) {
				// The above returns null if the entity is to be deleted afterwards
				deleteEntity(interactable);
			} else {
				if (isOutOfBounds(newInteractablePos)) {		// assuming that if thre are more than one entity at that location, no interaction can be had
					moveEntity(player, player.getOldPosition());
				} else {
					Entity atNewEntityPos = getFirstEntity(newInteractablePos);
					if (atNewEntityPos == interactable) {
						// entity hasnt moved after interaction
						if (interactable instanceof Enemy){
							// if we've interacted with the enemy 
							// but enemy is still in same position (not dead)
							// therefore, we are dead
						} else if (interactable instanceof Pit) {
						} else {
							moveEntity(player, player.getOldPosition());
						}
					// BOULDER STUFF
					} else if (atNewEntityPos == NULL) {
						// boulder has moved to empty spot
						moveEntity(interactable, newInteractablePos);
					} else if (atNewEntityPos instanceof Pit) {
						// boulder has moved onto pit
						deleteEntity(interactable);
					} else if (atNewEntityPos instanceof FloorSwitch) {
						// boulder has moved onto floorswitch
						moveEntity(interactable, newInteractablePos);
					} else {
						//System.out.println("Entity is" + interactable.getDesignerDescription());
						moveEntity(interactable, interactable.getOldPosition());
						moveEntity(player, player.getOldPosition());
					}
					// END BOULDER STUFF
				}
			}
			
		}
		printPlayerCoordinates();
	}
	

	/**
	 *  Updates interactions with items (player inv + in dungeon)
	 */
	public void itemInteractions() {
		ArrayList<Entity> toBeDeleted = new ArrayList<>(); 
		for (Entity entity : entities) {
			if (entity instanceof Arrow) {
				Arrow arrow = (Arrow)entity;
				Coordinate newPos = arrow.move();
				if (isOutOfBounds(newPos)) {
					toBeDeleted.add(arrow);
					break;
				}
				if (getFirstEntity(newPos) instanceof Enemy){
					toBeDeleted.add(getFirstEntity(newPos));
				}
				moveEntity(arrow, newPos);
			}
			//bone stuff
			if (entity instanceof Bone) {
				Bone bone = (Bone)entity;
				Coordinate newPos = bone.move();
				if(isOutOfBounds(newPos)) {
					break; //so if new spot out of bounds, dont move it
				}
				if (getFirstEntity(newPos) instanceof Pit || bone.getLifeTime() <= 0) {
					toBeDeleted.add(bone);
					break;
				}
				if (!isOccupied(newPos)) {
					moveEntity(bone,newPos);
				}
				
				
			}
			//bone stuff
			if (entity instanceof Bomb) {
				Bomb bomb = (Bomb)entity;
				if(bomb.isLit()) {
					bomb.tickTock();
				}
				if (bomb.getTurnsLeft() == 0) { 
					for (Coordinate affectedArea : bomb.affectedAreas()) {
						if(bomb.affectedAreas().contains(player.getPosition())) {		// can be refactored later by having player be part of entities
							player.setState(0);
						}
						Entity affectedEntity = getFirstEntity(affectedArea);
						if (affectedEntity != NULL) {
							if(affectedEntity.interactWithBomb()) {
								toBeDeleted.add(affectedEntity); // to workaround ConcurrentModificationException
							}
						}
					}
					toBeDeleted.add(entity);
				}
			}	
		}
		
		for (Entity entity : toBeDeleted) {
			deleteEntity(entity);
		}
		
		
		toBeDeleted = new ArrayList<>();
		for (Entity entity: player.getInventory()) {
			if (entity instanceof InvincibilityPotion){
				if(player.hasItem(entity)) {
					InvincibilityPotion invincibilityPotion = (InvincibilityPotion) entity;
					invincibilityPotion.reduceDurability();
					if(invincibilityPotion.getDurability() == 0) {
						toBeDeleted.add(invincibilityPotion);
					}
				}
			}
		}
		// Refactor into deleteFromInventory(toBeRemoved)
		player.getInventory().removeAll(toBeDeleted);

	}
	
	//Done
	public void generateMap() {
		for( int i = 0; i <= width; i++ ) {
			ArrayList<Coordinate> coords = new ArrayList<>();
			for ( int j = 0; j <= height; j++ ) {
				Coordinate newPos = new Coordinate(i,j);
				coords.add(newPos);
			}
			map.add(coords);
		}
	}
	
	public boolean addEntity(Entity entity) {
		if(entity instanceof Player && player != null) {
			return false;
		}
		Coordinate position = entity.getPosition();
		ArrayList<Entity> curEntities = getEntities(position);
		if(curEntities.size() >= 2) {
			return false;
		} 
		
		if (curEntities.size() == 1) {
			Entity curEntity = curEntities.get(0);
			// can add boulder/floor switch on top of one another
			// Arrow can be added on top of Player
			if (!curEntity.canBePlacedOnTop(entity)) {
				return false;
			}
		}

		if(isOutOfBounds(position)) return false;
		System.out.println("adding entity: " + entity.getName() + " at Coordinates: " + position.getX() + ", " + position.getY());
		getMapCo(entity).addEntity(entity);
		entity.setPosition(getMapCo(entity));
		entities.add(entity);
		if (entity instanceof Player) {
			player = (Player)entity;
		}
		return true;
	}

	public Entity getFirstEntity(Coordinate newPos) {
		if (getEntities(newPos).size() > 0) {
			return getEntities(newPos).get(0);
		} else {
			return null;
		}

	}
	
	public ArrayList<Entity> getEntities(Coordinate newPos) {
		return getMapCo(newPos).getEntities();
	}


	public Entity getEntityExcept(Coordinate newPos, Entity e) {
		ArrayList<Entity> curEntities = getMapCo(newPos).getEntities();
		for (Entity entity : curEntities) {
			if(!entity.equals(e)) {
				return entity; // Just returning top of the entity list	
			}
			
		}
		return null;
	}

	/**
	 * Initialises the game board
	 */
//	public void init() {
//		Coordinate position = new Coordinate(1,1);   // For test, this would be specified by user
//		createPlayer(position);						 //Create the player at the given Coordinate
//		generatePerimeter();						 //Create a series of walls around the perimeter
////		printGame();
//	}
	

	/**
	 * print to the Console the Coordinates of the current player
	 */
	private void printPlayerCoordinates() {
		if(player == null) {
			return;
		}
		System.out.println(player.returnPosition() + " (Bounds : " + this.getWidth() + " " + this.getHeight() + " )" + "\n");
	}
	
	//done
//	/**
//	 * 
//	 * @param position, the Coordinate to which the player should be placed
//	 */
	public void createPlayer(Coordinate position) {
		if(isOccupied(position)) return;
		player = new Player(position);
		getMapCo(position).addEntity(player);
		entities.add(player);
		if(player == null) {
			player = new Player(position);
			getMapCo(position).addEntity(player);
		}
	}

	//done
	/**
	 * Create a perimeter of wall around the board
	 */
	public void generatePerimeter() {
		int i,j;
		//System.out.print(width + " " + height);
		for(i = 0; i <= width; i ++) {
			for(j = 0; j <= height; j++) {
				if(j == 0 || j == height || i == 0 || i == width) {
					Coordinate currentPosition = new Coordinate(i,j);
					getMapCo(currentPosition).addEntity(new Wall(currentPosition));
				}
			}
		}
	}
	
	/**
	 * @param position
	 */
	public boolean isOccupied(Coordinate position) {
		for(Entity entity: entities) {
			if(entity.willCollide(position)) {
				//System.out.println("Cannot be placed here");
				return true;
			}
		}

		return false;
	}
	
	//done

	
//	public void forceAddEntity(Entity entity) {
//		Coordinate position = entity.getPosition();
//		if (!isOccupied(position))
//	}
	//done
	/**
	 * 
	 * @param entity, the Entity to be deleted. If the Entity is the player remove track of the player.
	 */
	public void deleteEntity(Entity entity) {
		getMapCo(entity).removeEntity(entity);
		entities.remove(entity);
		if(entity.getName().equals("Player")) {
			System.out.println("done");
			player = null;
		}
	}
	
	
	/**
	 * 
	 * @param xBoundary the largest size of the wall on the x axis
	 * @param yBoundary the largest size of the wall on the y axis
	 * @param newPos the Coordinate the entity is attempting to occupy
	 * @return true if the entity would leave the bounds, false otherwise
	 */
	// Safety net to make sure the character can't go outside of the game
	public boolean isOutOfBounds(Coordinate newPos) {
		int xBoundary = this.width;
		int yBoundary = this.height;
		//System.out.println("Xboundary: " + xBoundary +" Yboundary: " + yBoundary);
		if(newPos.getX() <= 0 || newPos.getX() >= xBoundary ) {
			System.out.println("Out of bounds");
			return true;
		}else if( newPos.getY() <= 0 || newPos.getY() >= yBoundary) {
			System.out.println("Out of bounds");
			return true;
		}
		return false;
	}



	public Coordinate getMapCo(Coordinate position) {
		return map.get(position.getX()).get(position.getY());
	}
	public Coordinate getMapCo(Entity entity) {
		return map.get(entity.getX()).get(entity.getY());
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public ArrayList<Entity> getPlayerInventory() {
		return player.getInventory();
	}
	
	/**
	 * @return the playerOne
	 */
	public Player getPlayer() {
		return player;
	}


	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}


	public Graph generateGraph() {
		int i,j;
		Coordinate cur;
		Graph g = new Graph(height,width);
		for(i = 1; i < width; i ++) {
			for(j = 1; j < height; j++) {
				cur = new Coordinate(i,j);
				if(! isOccupied(cur) || getFirstEntity(cur) instanceof Player) {
					g.addCoordinate(cur);	
				}
			}
		}
		
		return g;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	
	public boolean victory() {
		return win;
	}
	
	public ArrayList<Entity> getAllEntities() {
		ArrayList<Entity> allDesignerObjects = new ArrayList<Entity>();
		Coordinate defaultPos = new Coordinate(1, 1);
		allDesignerObjects.add(new Key());
		allDesignerObjects.add(new Hunter(defaultPos));
		allDesignerObjects.add(new Arrow(defaultPos));
		allDesignerObjects.add(new HoverPotion(defaultPos));
		allDesignerObjects.add(new Treasure(defaultPos));
		allDesignerObjects.add(new Sword(defaultPos));
		allDesignerObjects.add(new Boulder(defaultPos));
		allDesignerObjects.add(new Pit(defaultPos));
		allDesignerObjects.add(new InvincibilityPotion(defaultPos));
		allDesignerObjects.add(new Wall(defaultPos));
		allDesignerObjects.add(new FloorSwitch(defaultPos));
		allDesignerObjects.add(new Exit(defaultPos));
		allDesignerObjects.add(new Door());
		allDesignerObjects.add(new Hound(defaultPos));
		allDesignerObjects.add(new Player(defaultPos));
		return allDesignerObjects;
	}
	
	//now that im giving list of entities to Enemies
	private Coordinate closestPickup(Enemy enemy) {
		Coordinate closest = null;
		Entity ent = null;
		int size = -1;
		int tempSize;
		Graph g;
		Key key;
		Door door;
		boolean hasKey = false;
		for (Entity object: entities) {
			if (object instanceof Enemy || object instanceof Wall || object instanceof FloorSwitch || object instanceof Pit || object instanceof Player) {
				continue;
			}
			if (object instanceof Key) {
				if (player.hasItem("Key")) {
					continue;
				}
				
			}
			if (object instanceof Door) {
				for(Entity item: player.getInventory()) {
					if(item instanceof Key) {
						key = (Key)item;
						door = (Door)object;
						if ( door.getNumOfDoors() == key.getNumOfKeys()) {
							hasKey = true;
							break;
						}
					}
				}
				if (! hasKey) {
					continue;
				}
				hasKey = false;
			}
			
			g= generateGraph();
			g.addCoordinate(object.getPosition());
			g.addCoordinate(enemy.getPosition());
			if (!g.hasCoordinate(player.getPosition())) {
				g.addCoordinate(player.getPosition());
			}
			g.generateEdges();
			tempSize = g.sizeBFS(player.getPosition(), object.getPosition());
			if (tempSize < size || size == -1) {
				ent = object;
				size = tempSize;
			}
		}
		if (ent != null) {
		    g= generateGraph();
		    g.addCoordinate(ent.getPosition());
		    g.addCoordinate(enemy.getPosition());
		    if (!g.hasCoordinate(player.getPosition())) {
				g.addCoordinate(player.getPosition());
			}
		    g.generateEdges();
		    closest = g.BFS(player.getPosition(), ent.getPosition(), player.getPosition());
		//test
		    //System.out.println(ent.getName());
		}
		return closest;
	}

	public void printGame() {
	    int i = 0;
	    int j = 0;
		while (i <= this.getHeight()) {
	  	while (j <= this.getWidth()) {
	  		Coordinate curPos = new Coordinate(j, i);
	 		Entity entity = getFirstEntity(curPos);
	 		
	  		if (curPos.equals(player.getPosition())) {
	  			System.out.print("1");
	  		} else if(entity != null) {
	  			System.out.print(entity.getKeyCode().getName());
	  		} else {
	  			System.out.print("-");
	  		}
	  		System.out.print(" ");
	  		j++;
	 	}
	 	System.out.println("");
	  	j = 0;
	  	i++;
	  }
	}
	
	public Arrow getPlayerArrow() {
    	Arrow arrow = null;
    	for (Entity curItem : getPlayerInventory()) {
    		if (curItem instanceof Arrow) {
    			arrow = (Arrow)curItem;
    			return arrow;
    		}
    	}
		return null;
	}
	
	public Entity getEntity(Coordinate pos, Class reqClass) {
		for (Entity curEntity : getEntities(pos)) {
			if (curEntity.getClass() == reqClass) {
				return curEntity;
			}
		}
		return null;
	}
	
	public boolean isPlayerAlive() {
		return player.isAlive();
	}
	
	public boolean hasPlayerWon() {
		return win;
	}
}