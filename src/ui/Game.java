package ui;
import entities.*;
import java.util.ArrayList;


import entities.Treasure;
import entities.Boulder;
import entities.Coordinate;
import entities.Entity;
import entities.Pit;
import entities.Player;
import entities.Wall;

public class Game{ 								
	private static final Entity NULL = null;
	private int width, height;					//Width and height of the app window
	private Player player;					//Tracking the player entity
	private InputManagerPlayer playerInput;	//KeyListener, takes in key inputs
	private ArrayList<Entity> entities;//Array List of Entities, tracks all entities in the current game
	private boolean win = false;
	private ArrayList<ArrayList<Coordinate>> map;
	// Need to implement generic iterator
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
		this.map = new ArrayList<ArrayList<Coordinate>>();
		generateMap();
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
	

	
	//Done
	/**
	 * 
	 */
	public void deleteShot() {
		ArrayList<Entity> toBeDeleted = new ArrayList<>(); 
		for (Entity entity : entities) {
			if (entity instanceof Arrow) {
				Arrow arrow = (Arrow)entity;
				int newX = arrow.returnX() + arrow.getDx();
				int newY = arrow.returnY() + arrow.getDy();
				Coordinate newPos = new Coordinate(newX, newY);
				moveEntity(newPos, arrow);
				Entity onArrow = getEntityExcept(newPos, arrow);
				if (onArrow instanceof Enemy){
					toBeDeleted.add(onArrow);
				}
			}
		}
		for (Entity entity : toBeDeleted) {
			entities.remove(entity);
		}
	}
	
	public void moveEntity(Coordinate newPos, Entity entity) {
		Coordinate newMapTile = retrieveMapCo(newPos);
		Coordinate oldMapTile = retrieveMapCo(entity);
		oldMapTile.removeEntity(entity);
		entity.setPosition(newMapTile);
		newMapTile.addEntity(entity);
	}
	
	public void update() {						//Updates the state of the game
		// Arrow collisions, kills enemies first
		deleteShot();
		// Moves player and interacts.. (or doesnt if invalid move)
		movePlayer();
		// Checks current state of the game after any interactions
		ArrayList<Entity> toBeRemoved = new ArrayList<>();
		
		int allTreasure = 1;
		int allSwitch = 1;
		int allEnemy = 1;

		Coordinate position;

		win = false;

		for (Entity entity : entities) {
			
			// Checks all killed win condition
			if (entity instanceof Enemy) {
            
				Entity enemy = (Enemy) entity;
				if (player.hasItem("InvincibilityPotion")) {
					position = enemy.invincibilityMove(player.getPosition(), generateGraph() );
				} else {
				    position = enemy.move(player.getPosition(), generateGraph() );
				}
				if ( getEntity(position) == null && !(player.getPosition().equals(position)) ) {
					moveEntity(position, enemy);
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
						Entity affectedEntity = getEntity(affectedArea);
						if (affectedEntity != NULL) {
							if(affectedEntity.interactWithBomb()) {
								toBeRemoved.add(affectedEntity); // to workaround ConcurrentModificationException
							}
						}
					}
					toBeRemoved.add(entity);
				}
			}	
		}
//		if (toBeRemoved.contains(playerOne)) {
//			playerOne.setState(0);   // if bomb explosion is on player
//		}
		entities.removeAll(toBeRemoved);

		toBeRemoved = new ArrayList<>();
		for (Entity entity: player.getInventory()) {
			if (entity instanceof InvincibilityPotion){
				if(player.hasItem(entity)) {
					InvincibilityPotion invincibilityPotion = (InvincibilityPotion) entity;
					invincibilityPotion.reduceDurability();
					if(invincibilityPotion.getDurability() == 0) {
						toBeRemoved.add(invincibilityPotion);
					}
				}
			}
		}
		player.getInventory().removeAll(toBeRemoved);

		
		if(win && player.isAlive()) {
			System.out.println("You have won");
		}

		if (!player.isAlive()) {
			System.out.println("Player is currently dead");
		}
		if(win && player.isAlive()) {
			System.out.println("You have won");
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
		printGame();

	}
	

	public void printGame() {
        int i = 0;
        int j = 0;
		while (i <= this.getHeight()) {
        	while (j <= this.getWidth()) {
        		Coordinate curPos = new Coordinate(j, i);
        		Entity entity = getEntity(curPos);
        		if (curPos.equals(player.getPosition())) {
        			System.out.print("1");
        		} else if(entity != null) {
        			System.out.print((char)entity.getKeyCode());
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
		while (i <= this.getHeight()) {
        	while (j <= this.getWidth()) {
        		Coordinate curPos = new Coordinate(j, i);
        		Entity entity = getEntity(curPos);
        		if (curPos.equals(player.getPosition())) {
        			sb.append("1");
        		} else if(entity != null) {
        			sb.append((char)entity.getKeyCode());
        		} else {
        			sb.append("-");
        		}
        		sb.append(" ");
        		j++;
        	}
        	sb.append("\n");
        	j = 0;
        	i++;
        }
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Moves the player
	 */
	public void movePlayer() {

//		Coordinate curPos = playerOne.getPosition();
//		int xPlayer = curPos.getxPosition();
//		int yPlayer = curPos.getyPosition();
//		int xMovement = playerInput.getDx();
//		int yMovement = playerInput.getDy();
//		Coordinate newPos = new Coordinate(xPlayer+xMovement, yPlayer+yMovement);
		// OR
//		playerInput.getDx();
//		player.setDx(playerInput.getDx());
//		player.setDy(playerInput.getDy());

		//just making null variables for now
		Graph g = null;
		Coordinate newPos = player.getMove();

		if(!isOutOfBounds(newPos)) {

			//System.out.println("Moving player to position: X: " + newPos.getxPosition() + " Y: " + newPos.getyPosition());
			player.setOldPosition(player.getPosition());
			moveEntity(newPos, player);
			Entity entity = getEntityExcept(newPos, new FloorSwitch(new Coordinate(1*32,2*32)));
			//System.out.println(entity);
			Entity boulderEntity = getEntity(player.getMove());


			if (entity!=NULL) {
				System.out.println("CurPos has a: " + entity.getName());
				if(entity.interactWithPlayer(player)) {
					// The above returns true if the entity is to be deleted afterwards
					this.deleteEntity(entity);
				}
				
				// BOULDER HARDCODE STUFF;
				// This checks whether the entity has moved due to the interaction with Player, therefore has to be a boulder
				if(isOutOfBounds(entity.getPosition())) {		
					Boulder boulder = (Boulder) entity;
					boulder.revert(player);
					System.out.println("Boulder cannot be moved here");
				} else if (boulderEntity != NULL && (entity instanceof Boulder)) {
					if (boulderEntity instanceof Pit) {
						entities.remove(entity);
						System.out.println("Boulder is Dead :)");
					} else if (!(boulderEntity instanceof FloorSwitch)) {
						// entity is boulder, boulderEntity is not Pit or FloorSwitch
						// Revert
						Boulder boulder = (Boulder) entity;
						boulder.revert(player);
						System.out.println("Boulder cannot be moved here");
					}
				}
				// BOULDER STUFF END
				
				
			}
		}
		printPlayerCoordinates();
	}
	
	//Done
	public Entity getEntity(Coordinate newPos) {
		ArrayList<Entity> curEntities = retrieveMapCo(newPos).getEntities();
		for (Entity entity : curEntities) {
			return entity; // Just returning top of the entity list
		}
		return null;
	}
	
	//public getSpecificEntity()
	
//	public Entity getEntitynofs(Coordinate newPos) {
//		for (Entity entity : entities) {
//			if(entity instanceof FloorSwitch) continue;
//			if(entity.getPosition().equals(newPos)) {
//				return entity;
//			}
//		}
//		return NULL;
//	}
	//Done
	public Entity getEntityExcept(Coordinate newPos, Entity e) {
		ArrayList<Entity> curEntities = retrieveMapCo(newPos).getEntities();
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
	public void init() {
		Coordinate position = new Coordinate(1,1);   // For test, this would be specified by user
		createPlayer(position);						 //Create the player at the given Coordinate
		generatePerimeter();						 //Create a series of walls around the perimeter
		printGame();
	}
	

	/**
	 * print to the Console the Coordinates of the current player
	 */
	private void printPlayerCoordinates() {
		System.out.println(player.returnPosition() + " (Bounds : " + this.getWidth() + " " + this.getHeight() + " )" + "\n");
	}
	
	//done
	/**
	 * 
	 * @param position, the Coordinate to which the player should be placed
	 */
	public void createPlayer(Coordinate position) {
		if(isOccupied(position)) return;
		player = new Player(position, this);
		retrieveMapCo(position).addEntity(player);
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
					retrieveMapCo(currentPosition).addEntity(new Wall(currentPosition));
				}
			}
		}
	}
	
	/**
	 * @param position
	 */
	public boolean isOccupied(Coordinate position) {
		if(!entities.isEmpty()) {
			for(Entity entity: entities) {
				if(entity.willCollide(position)) {
					//System.out.println("Cannot be placed here");
					return true;
				}
			}
		}
//		
//		if(!walls.isEmpty()) {
//			for(Wall wall: walls) {
//				if(wall.willCollide(position)) {
//					System.out.println("Cannot be placed here");
//					return true;
//				}
//			}
//		}
		return false;
	}
	
	//done
	public boolean addEntity(Entity entity) {
		Coordinate position = entity.getPosition();
		if(isOccupied(position)) return false;
		if(isOutOfBounds(position)) return false;
		System.out.println("adding entity: " + entity.getName() + " at Coordinates: " + position.getxPosition() + ", " + position.getyPosition());
		retrieveMapCo(entity).addEntity(entity);
		entity.setPosition(retrieveMapCo(entity));
		entities.add(entity);
		return true;
	}
	
	//done
	/**
	 * 
	 * @param entity, the Entity to be deleted. If the Entity is the player remove track of the player.
	 */
	private void deleteEntity(Entity entity) {
		retrieveMapCo(entity).removeEntity(entity);
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
		if(newPos.getxPosition() <= 0 || newPos.getxPosition() >= xBoundary ) {
			System.out.println("Out of bounds");
			return true;
		}else if( newPos.getyPosition() <= 0 || newPos.getyPosition() >= yBoundary) {
			System.out.println("Out of bounds");
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param position the Coordinate the entity is trying to occupy
	 * @return true if the Coordinate has a Solid object, false otherwise
	 */
//	private boolean isSolid(Coordinate position) {
//		if(walls.isEmpty()) return false;
//		for (Wall wall: walls){
//			if(wall.willCollide(position)) {
//				System.out.println("Colliding with Wall");
//				return true;
//			}
//		}
//		return false;
//	}
	public Coordinate retrieveMapCo(Coordinate position) {
		return map.get(position.getxPosition()).get(position.getyPosition());
	}
	public Coordinate retrieveMapCo(Entity entity) {
		return map.get(entity.returnX()).get(entity.returnY());
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
	public void setPlayerOne(Player playerOne) {
		this.player = playerOne;
	}
	
	public void changeState(InputManagerPlayer playerInput) {
		
		// There are two game states: Player and Designer
		// Each state only supports a certain subset of Key Inputs 
		// Player and Designer both extend from Playable so they can both move
	
		// Deletes all entities each time state changes (resetting atleast part of the game for now..)
		//this.playerInput = playerInput;
		//this.frame = playerInput.getFrame();
		this.playerInput = playerInput;
		playerInput.getFrame().addKeyListener(playerInput);
	}

	/**
	 * @param playerInput the playerInput to set
	 */
	public void setPlayerInput(InputManagerPlayer playerInput) {
		this.playerInput = playerInput;
	}

	public Graph generateGraph() {
		int i,j;
		Coordinate cur;
		Graph g = new Graph(height,width);
		for(i = 1; i < width; i ++) {
			for(j = 1; j < height; j++) {
				cur = new Coordinate(i,j);
				if(! isOccupied(cur) ) {
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

}