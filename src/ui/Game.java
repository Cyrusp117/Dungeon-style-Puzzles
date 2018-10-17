package ui;
import entities.*;
import java.util.ArrayList;

public class Game{ 								
	private static final Entity NULL = null;
	private int width, height;					//Width and height of the app window
	private Player player;					//Tracking the player entity
//	private InputManagerPlayer playerInput;	//KeyListener, takes in key inputs
	private ArrayList<Entity> entities;//Array List of Entities, tracks all entities in the current game
	private boolean win = false;
	private ArrayList<ArrayList<Coordinate>> map;
	private Graph g; //new in graph implementation
	// Need to implement generic iterator
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.map = new ArrayList<ArrayList<Coordinate>>();
		generateMap();
		this.g = null;
	}
	


	
	public void update() {						//Updates the state of the game
		// Arrow collisions, bomb explosions, player inventory update
		itemInteractions();
		// Moves player and interacts.. (or doesnt if invalid move)
		movePlayer();
		// Checks current state of the game after any interactions
	    if (g == null) {
	    	g = generateGraph();
	    	g.generateEdges();
	    }
	    //if (g.hasPoint(player.getPosition())) {
	    //	g.removePoint(player.getPosition());
	    //}
		
		int allTreasure = 1;
		int allSwitch = 1;
		int allEnemy = 1;

		Coordinate position;

		win = false;

		for (Entity entity : entities) {
			
			// Checks all killed win condition
			if (entity instanceof Enemy) {
              
				Enemy enemy = (Enemy) entity;
				if (player.hasItem("InvincibilityPotion")) {
					position = enemy.invincibilityMove(player.getPosition(), g);//generateGraph() );
				} else {
					System.out.println("hi\n\n\n");
				    position = enemy.move(player.getPosition(), g);//generateGraph() ); //where I generate graph before the move
				}
				if ( getFirstEntity(position) == null && !(player.getPosition().equals(position)) ) {
					moveEntity(enemy, position);
				} 

				allEnemy = 0;

				
			}
			
			// Checks if all treasure has been picked up
			if (entity instanceof Treasure){
				allTreasure = 0;
			}
			
			// Checks if all floor switches are active (have a boulder on them)
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

		if(win && player.isAlive()) {
			System.out.println("You have won");
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
			g.newPoint(entity.getPosition()); //testing this
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
		System.out.println(player.returnPosition() + " (Bounds : " + this.getWidth() + " " + this.getHeight() + " )" + "\n");
	}
	
	//done
//	/**
//	 * 
//	 * @param position, the Coordinate to which the player should be placed
//	 */
//	public void createPlayer(Coordinate position) {
//		if(isOccupied(position)) return;
//		player = new Player(position);
//		getMapCo(position).addEntity(player);
//		entities.add(player);
//	}

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
	private void deleteEntity(Entity entity) {
		getMapCo(entity).removeEntity(entity);
		entities.remove(entity);
		if(entity.equals(player)) {
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
//	
//	public void changeState(InputManagerPlayer playerInput) {
//		
//		// There are two game states: Player and Designer
//		// Each state only supports a certain subset of Key Inputs 
//		// Player and Designer both extend from Playable so they can both move
//	
//		// Deletes all entities each time state changes (resetting atleast part of the game for now..)
//		//this.playerInput = playerInput;
//		//this.frame = playerInput.getFrame();
//		this.playerInput = playerInput;
//		playerInput.getFrame().addKeyListener(playerInput);
//	}
//
//	/**
//	 * @param playerInput the playerInput to set
//	 */
//	public void setPlayerInput(InputManagerPlayer playerInput) {
//		this.playerInput = playerInput;
//	}

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

	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//        int i = 0;
//        int j = 0;
//		while (i <= this.getHeight()) {
//        	while (j <= this.getWidth()) {
//        		Coordinate curPos = new Coordinate(j, i);
//        		Entity entity = getEntity(curPos);
//        		if(entity != null) {
//        			sb.append(entity.getKeyCode().getName());
//        		} else {
//        			sb.append("-");
//        		}
//        		sb.append(" ");
//        		j++;
//        	}
//        	sb.append("\n");
//        	j = 0;
//        	i++;
//        }
//		return sb.toString();
//	}
	
//	public void printGame() {
//  int i = 0;
//  int j = 0;
//	while (i <= this.getHeight()) {
//  	while (j <= this.getWidth()) {
//  		Coordinate curPos = new Coordinate(j, i);
//  		Entity entity = getEntity(curPos);
//  		if (curPos.equals(player.getPosition())) {
//  			System.out.print("1");
//  		} else if(entity != null) {
//  			System.out.print(entity.getKeyCode().getName());
//  		} else {
//  			System.out.print("-");
//  		}
//  		System.out.print(" ");
//  		j++;
//  	}
//  	System.out.println("");
//  	j = 0;
//  	i++;
//  }
//}
}