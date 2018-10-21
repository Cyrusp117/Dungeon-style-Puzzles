package ui;
import entities.*;
import java.util.ArrayList;

public class Game{ 								
	private static final Entity NULL = null;
	private int width, height;					//Width and height of the app window
	private Player player = null;					//Tracking the player entity
	private ArrayList<Entity> entities;//Array List of Entities, tracks all entities in the current game
	private boolean win = false;
	private ArrayList<ArrayList<Coordinate>> map;
	private CheckWinCon winChecker;
	private String theme;
	//private Graph g; //new in graph implementation
	// Need to implement generic iterator
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.map = new ArrayList<ArrayList<Coordinate>>();
		this.theme = "theme1";
		generateMap();
		this.winChecker = new WinChecker();
	}
	
	//Copy constructor
	public Game(Game otherGame) {
		this(otherGame.width, otherGame.height);
		EntityFactory factory = new EntityFactory();
		EntityProducer producer = new EntityProducer(factory);
		for(Entity entity: otherGame.getEntities()) {
			producer.addEntityToGame(this, entity.getPosition(), entity.getName());
		}
		this.theme = otherGame.theme;
		this.winChecker = otherGame.winChecker;
	}
	
	
	/**
	 * This signals a new turn in the game and updates the model
	 * by first updating interactions with items,
	 * then moves the player
	 * then checks win conditions
	 */
	public void update() {						//Updates the state of the game
		// Arrow collisions, bomb explosions, player inventory update
		itemInteractions();
		// Moves player and interacts.. (or doesnt if invalid move)
		movePlayer();

		
		// Extension stuff
		for (Entity entity : entities) {
			enemyCheck(entity);
			floorCheck(entity);
		}
		
		ArrayList<Treasure> tresList = new ArrayList<Treasure>();
		for (Entity entity : entities) {
			if(entity instanceof Treasure) {
				Treasure tres = (Treasure) entity;
				if(tres.getStatus()) {
					tresList.add(tres);
				}
			}
		}
		for (Treasure object: tresList) {
			deleteEntity(object);
		}
		// extension stuff end 
		
		// Checks current state of the game after any interactions
		win = false;
		if (winChecker.checkWinCondition(this)) {
			win = true;
		}
	}

	/**
	 * 
	 * @param entity to Check if Floor switch
	 * 
	 */
	public void floorCheck(Entity entity) {
		if (entity instanceof FloorSwitch) {		// could be a lot neater if we had an array of FloorSwitch ?
			FloorSwitch fs = (FloorSwitch)entity; 
			fs.deactivate();
			if(getEntity(fs.getPosition(), Boulder.class) != NULL) {
				fs.activate();
			}
		}
	}

	/**
	 * 
	 * @param entity the Entity to check if Enemy
	 * 
	 */
	public void enemyCheck(Entity entity) {
		Coordinate position;
		Coordinate closestPickup;
		if (entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			if (player.hasItem("InvincibilityPotion")) {
				position = enemy.invincibilityMove(player.getPosition(), generateGraph());
			} else {
				
				closestPickup = closestPickup(enemy);
			    position = enemy.move(player.getPosition(), generateGraph(),closestPickup,entities); //where I generate graph before the move
			    if (getFirstEntity(position) instanceof Treasure && entity instanceof TreasureGoblin) { //test if there will be overlap of treasure and goblin
			    	Treasure tres = (Treasure) getFirstEntity(position);
			    	tres.setStatus();

			    }
			}
			if ( getFirstEntity(position) == null && !(player.getPosition().equals(position)) ) {
				moveEntity(enemy, position);
			} 			
		}
	}

	/**
	 * @param allTreasure
	 * @param entity
	 * @return
	 */
//	public int treasureCheck(Entity entity) {
//		// Checks if all treasure has been picked up
//		if (entity instanceof Treasure){
//			allTreasure = 0;
//			//also check treasure goblin for this condition
//		} else if (entity instanceof TreasureGoblin) {
//			TreasureGoblin goblin = (TreasureGoblin)entity;
//			if(goblin.getTreasure() != null) {
//				allTreasure = 0;
//			}
//		}
//		return allTreasure;
//	}
	/**
	 * 
	 * @param entity - the entity to be moved
	 * @param newPos - the position to be moved to
	 */
	public void moveEntity(Entity entity, Coordinate newPos) {
		entity.setOldPosition(entity.getPosition());
		Coordinate newMapTile = getMapCo(newPos);
		Coordinate oldMapTile = getMapCo(entity);
		oldMapTile.removeEntity(entity);
		entity.setPosition(newMapTile);
		newMapTile.addEntity(entity);
	}
	
	/**
	 * Moves the player and does interactions with objects in 
	 * player's new location
	 */
	public void movePlayer() {
		Coordinate newPlayerPos = player.getMove();
		if(isOutOfBounds(newPlayerPos)) {
			return;
		}
		ArrayList<Entity> entities = getEntities(newPlayerPos);
		ArrayList<Entity> entitiesClone = cloneEntities(entities); 
		moveEntity(player, newPlayerPos);
		for (Entity interactable : entitiesClone) {
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
						// Entity that player is interacting with
						// hasnt moved or been deleted or was out of bounds
						if(!player.canBePlacedOnTop(atNewEntityPos)) {
							moveEntity(player, player.getOldPosition());
						}

					// An entity that player has interacted with has moved
					} else {
						// Interactable entity has moved
						if (atNewEntityPos == null) {
							moveEntity(interactable, newInteractablePos);
						} else {
							newInteractablePos = interactable.interact(atNewEntityPos);
							if (newInteractablePos == null) {
								deleteEntity(interactable); 
							} else if (interactable.isValid()) {	
								moveEntity(interactable, newInteractablePos);
							} else {
								moveEntity(interactable, interactable.getOldPosition());
								moveEntity(player, player.getOldPosition());
							}
						}
					}
				}
			}
		}
		printPlayerCoordinates();
	}
	

	private ArrayList<Entity> cloneEntities(ArrayList<Entity> entities) {
		ArrayList<Entity> clone = new ArrayList<>();
		for(Entity e:entities) {
			clone.add(e);
		}
		return clone;
	}

	/**
	 *  Updates interactions with items (player inv + in dungeon)
	 *  e.g. bombs getting closer to explosion, arrows, bones
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
				if (bomb.getTurnsLeft() == -1) { 
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
	
	/**
	 * Creates coordinates for the Game model
	 */
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
	
	/**
	 * 
	 * @param entity The entity to be added
	 * @return True if successfully added entity
	 */
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

	/**
	 * 
	 * @param newPos - The position to get the entity from
	 * @return - Returns the first entity from the array of entities
	 * at that location
	 */
	public Entity getFirstEntity(Coordinate newPos) {
		if (getEntities(newPos).size() > 0) {
			return getEntities(newPos).get(0);
		} else {
			return null;
		}

	}
	
	/**
	 * 
	 * @param newPos
	 * @return The array of entities at newPos Coordinate
	 */
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
	 * print to the Console the Coordinates of the current player
	 */
	private void printPlayerCoordinates() {
		if(player == null) {
			return;
		}
		System.out.println(player.returnPosition() + " (Bounds : " + this.getWidth() + " " + this.getHeight() + " )" + "\n");
	}
	
	//done
	/**
	 * Create a perimeter of walls around the board
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
	 * @return True if the position is occupied
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


	/**
	 * 
	 * @param position 
	 * @return The map Coordinate equivalent of position
	 */
	public Coordinate getMapCo(Coordinate position) {
		return map.get(position.getX()).get(position.getY());
	}
	/**
	 * 
	 * @param entity
	 * @return The map coordinate equivalent of Entity's position
	 */
	public Coordinate getMapCo(Entity entity) {
		return map.get(entity.getX()).get(entity.getY());
	}
	
	/**
	 * 
	 * @return Height of the game
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @return Width of the game
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @return ArrayList of entities representing player inventory
	 */
	public ArrayList<Entity> getPlayerInventory() {
		return player.getInventory();
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}


	/**
	 * @param playerOne - Sets the player of the game
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
	
	/**
	 * 
	 * @return List of all entities in the game's map
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	

	
	
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
		
		}
		return closest;
	}


	
	/**
	 * 
	 * @return An Arrow entity if the player has arrow, else null
	 */
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
	
	/**
	 * 
	 * @param pos
	 * @param reqClass
	 * @return The entity with position pos and Class reqClass, else null
	 */
	public Entity getEntity(Coordinate pos, Class reqClass) {
		for (Entity curEntity : getEntities(pos)) {
			if (curEntity.getClass() == reqClass) {
				return curEntity;
			}
		}
		return null;
	}
	
	
	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * 
	 * @return True if player is alive
	 */
	public boolean isPlayerAlive() {
		return player.isAlive();
	}
	
	/**
	 * 
	 * @return True if player has won
	 */
	public boolean hasPlayerWon() {
		return win;
	}

	/**
	 * @return the winChecker
	 */
	public CheckWinCon getWinChecker() {
		return winChecker;
	}

	/**
	 * @param winChecker the winChecker to set
	 */
	public void setWinChecker(CheckWinCon winChecker) {
		this.winChecker = winChecker;
	}


	
}