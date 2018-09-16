package ui;
import entities.*;
import java.util.ArrayList;

import com.sun.java.swing.plaf.windows.resources.windows_zh_CN;

import entities.Treasure;
import entities.Boulder;
import entities.Coordinate;
import entities.Entity;
import entities.Pit;
import entities.Player;
import entities.Wall;

public class Game{ 								
	private static final Entity NULL = null;
// implements Runnable{
	private int width, height;					//Width and height of the app window
	private Player playerOne;					//Tracking the player entity
	private InputManagerPlayer playerInput;	//KeyListener, takes in key inputs
	private ArrayList<Entity> entities;//Array List of Entities, tracks all entities in the current game
	private boolean win = false;
	// Need to implement generic iterator
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<>();
	}
	
	public void newTurn() {							 //Called to run the next turn. Currently just update, will later contain render
		// Checks for arrow collisions
		ArrayList<Entity> toBeDeleted = new ArrayList<>(); 
		for (Entity entity : entities) {
			if (entity instanceof Arrow) {
				Arrow arrow = (Arrow)entity;
				int newX = arrow.returnX() + arrow.getDx();
				int newY = arrow.returnY() + arrow.getDy();
				Coordinate newPos = new Coordinate(newX, newY);
				arrow.setPosition(newPos);
				Entity onArrow = getEntityExcept(newPos, arrow);
				if (onArrow instanceof Enemy){
					toBeDeleted.add(onArrow);
				}
			}
		}
		for (Entity entity : toBeDeleted) {
			entities.remove(entity);
		}
		update();
	}
	
	private void update() {						//Updates the state of the game

		movePlayer();
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
				position = enemy.move(playerOne.getPosition(), generateGraph() );
				if ( getEntity(position) == null ) {
					enemy.setPosition(position);
				} //else if (getEntity(position).equals(entity)) {
					
				//}
				//if (position.getxPosition() != enemy.returnX() || position.getyPosition() != enemy.returnY()) {
				//    enemy.setPosition(position);
				//}

				allEnemy = 0;

				//System.out.println(enemy.getName() + " would move if he was implemented");
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
//				if (e instanceof Boulder) {
//				Boulder b = (Boulder)e;
//				if(b.getPosition().equals(fs.getPosition())) {
//					fs.activate();
//				}
			}
			
			// Checks general win condition
			if (entity instanceof Exit){
				if(entity.getPosition().equals(playerOne.getPosition())) {
					if (playerOne.isAlive()) {
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
		entities.removeAll(toBeRemoved);
		// Checks if player is dead
		if (!playerOne.isAlive()) {
			System.out.println("Player is currently dead");
		}
		if(win && playerOne.isAlive()) {
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
		while (i <= this.getHeight()/32) {
        	while (j <= this.getWidth()/32) {
        		Coordinate curPos = new Coordinate(j*32, i*32);
        		Entity entity = getEntity(curPos);
        		if (curPos.equals(playerOne.getPosition())) {
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
		playerInput.getDx();
		playerOne.setDx(playerInput.getDx());
		playerOne.setDy(playerInput.getDy());

		//just making null variables for now
		Graph g = null;
		Coordinate newPos = playerOne.move();

		if(!isOutOfBounds(newPos)) {

			//System.out.println("Moving player to position: X: " + newPos.getxPosition() + " Y: " + newPos.getyPosition());
			playerOne.setOldPosition(playerOne.getPosition());
			playerOne.setPosition(newPos);
			Entity entity = getEntityExcept(newPos, new FloorSwitch(new Coordinate(1*32,2*32)));
			//System.out.println(entity);
			Entity boulderEntity = getEntity(playerOne.move());


			if (entity!=NULL) {
				System.out.println("CurPos has a: " + entity.getName());
				if(entity.interactWithPlayer(playerOne)) {
					// The above returns true if the entity is to be deleted afterwards
					this.deleteEntity(entity);
				}
				
				// BOULDER HARDCODE STUFF;
				// This checks whether the entity has moved due to the interaction with Player, therefore has to be a boulder
				if(isOutOfBounds(entity.getPosition())) {		
					Boulder boulder = (Boulder) entity;
					boulder.revert(playerOne);
					System.out.println("Boulder cannot be moved here");
				} else if (boulderEntity != NULL) {
					if (boulderEntity instanceof Pit) {
						entities.remove(entity);
						System.out.println("Boulder is Dead :)");
					}
				}
				// BOULDER STUFF END
				
				
			}
		}
		printPlayerCoordinates();
	}
	
	public Entity getEntity(Coordinate newPos) {
		for (Entity entity : entities) {
			if(entity.getPosition().equals(newPos)) {
				return entity;
			}
		}
		return null;
	}
	
//	public Entity getEntitynofs(Coordinate newPos) {
//		for (Entity entity : entities) {
//			if(entity instanceof FloorSwitch) continue;
//			if(entity.getPosition().equals(newPos)) {
//				return entity;
//			}
//		}
//		return NULL;
//	}
	
	public Entity getEntityExcept(Coordinate newPos, Entity e) {
		for (Entity entity : entities) {
			if(e.getName().equals(entity.getName())) continue;
			if(entity.getPosition().equals(newPos)) {
				return entity;
			}
		}
		return NULL;
	}

	/**
	 * Initialises the game board
	 */
	public void init() {
		Coordinate position = new Coordinate(32,32); // For test, this would be specified by user
		createPlayer(position);						 //Create the player at the given Coordinate
		generatePerimeter();						 //Create a series of walls around the perimeter
		printGame();

	}
	


	/**
	 * print to the Console the Coordinates of the current player
	 */
	private void printPlayerCoordinates() {
		System.out.println(playerOne.returnPosition() + " (Bounds : " + this.getWidth() + " " + this.getHeight() + " )" + "\n");
	}
	
	/**
	 * 
	 * @param position, the Coordinate to which the player should be placed
	 */
	public void createPlayer(Coordinate position) {
		if(isOccupied(position)) return;
		playerOne = new Player(position);
	}

	/**
	 * Create a perimeter of wall around the board
	 */
	public void generatePerimeter() {
		int i,j;
		//System.out.print(width + " " + height);
		for(i = 0; i <= width; i += 32) {
			for(j = 0; j <= height; j+= 32) {
				if(j == 0 || j == height || i == 0 || i == width) {
					Coordinate currentPosition = new Coordinate(i,j);
					entities.add(new Wall(currentPosition));
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
					System.out.println("Cannot be placed here");
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
	
	public boolean addEntity(Entity entity) {
		Coordinate position = entity.getPosition();
		if(isOccupied(position)) return false;
		if(isOutOfBounds(position)) return false;
		System.out.println("adding entity: " + entity.getName() + " at Coordinates: " + position.getxPosition() + ", " + position.getyPosition());
		entities.add(entity);
		return true;
	}
	
	
	/**
	 * 
	 * @param entity, the Entity to be deleted. If the Entity is the player remove track of the player.
	 */
	private void deleteEntity(Entity entity) {
		entities.remove(entity);
		if(entity.equals(playerOne)) {
			playerOne = null;
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
	
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public ArrayList<Entity> getPlayerInventory() {
		return playerOne.getInventory();
	}
	
	/**
	 * @return the playerOne
	 */
	public Player getPlayerOne() {
		return playerOne;
	}


	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
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
		for(i = 0; i <= width; i += 32) {
			for(j = 0; j <= height; j+= 32) {
				cur = new Coordinate(i,j);
				if(! isOccupied(cur)) {
					g.addCoordinate(cur);	
				}
			}
		}
		
		return g;
	}
	
	public ArrayList<Entity> testEntities() {
		return this.entities;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public boolean victory() {
		return win;
	}
}
	

	

