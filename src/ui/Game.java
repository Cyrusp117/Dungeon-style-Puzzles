package ui;

import java.util.ArrayList;

import javax.swing.JFrame;

import entities.Treasure;
import entities.AI;
import entities.Coordinate;
import entities.Entity;
import entities.Player;
import entities.Wall;

public class Game{ 								
	private static final Entity NULL = null;
// implements Runnable{
	private JFrame frame;
	private int width, height;					//Width and height of the app window
	private String title;						//Title at the top of the window
	private Player playerOne;					//Tracking the player entity
	private InputManagerPlayer playerInput;	//KeyListener, takes in key inputs
	private ArrayList<Wall> walls;				//Array List of Walls, tracks walls in the current game
	private ArrayList<Entity> entities;			//Array List of Entities, tracks all entities in the current game
	// Need to implement generic iterator
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		//this.playerInput;
		this.walls = new ArrayList<>();
		this.entities = new ArrayList<>();
	}
	
	private void update() {						//Updates the state of the game
		movePlayer();
		int allTreasure = 1;
		for (Entity entity : entities) {
			// Moves each entity that is supposed to move
			if (entity instanceof AI) {
				Entity enemy = (AI) entity;
				//System.out.println(enemy.getName() + " would move if he was implemented");
			}
			// Checks if all treasure has been picked up
			if (entity instanceof Treasure){
				if (!playerOne.hasItem(entity)) {
					allTreasure = 0;
				}
			}
		}
		// Checks if player is dead
		if (!playerOne.isAlive()) {
			// Launch new instance of game
			System.out.println("Player is currently dead");
		}
		
		if(allTreasure == 1) {
			//System.out.println("All treasure has been collected");
		}
		System.out.println("");
		printGame();
	}

	public void printGame() {
        int i = 1;
        int j = 1;
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
		playerOne.setDx(playerInput.getDx());
		playerOne.setDy(playerInput.getDy());
		Coordinate newPos = playerOne.move();
		//System.out.println("New position: " + newPos.returnPosition());
		if(!isOutOfBounds(newPos) && !isSolid(newPos)) {
			System.out.println("Moving player to position: X: " + newPos.getxPosition() + " Y: " + newPos.getyPosition());
			playerOne.setPosition(newPos);
			Entity entity = getEntity(newPos);
			if (entity!=NULL) {
				System.out.println("CurPos has a: " + entity.getName());
				if(entity.interactWithPlayer(playerOne)) {
					// The above returns true if the entity is to be deleted afterwards
					this.deleteEntity(entity);
				}
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

	/**
	 * Initialises the game board
	 */
	public void init() {
		Coordinate position = new Coordinate(32,32); // For test, this would be specified by user
		createPlayer(position);						 //Create the player at the given Coordinate
		generatePerimeter();						 //Create a series of walls around the perimeter
		printGame();

	}
	
	public void newTurn() {							 //Called to run the next turn. Currently just update, will later contain render
		update();
	}

	/**
	 * print to the Console the Coordinates of the current player
	 */
	private void printPlayerCoordinates() {
		System.out.println(playerOne.returnPosition()+"\n");
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
					addEntity(new Wall(currentPosition));	
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
		
		if(!walls.isEmpty()) {
			for(Wall wall: walls) {
				if(wall.willCollide(position)) {
					System.out.println("Cannot be placed here");
					return true;
				}
			}
		}
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
		if(newPos.getxPosition() < 0 || newPos.getxPosition() > xBoundary ) {
			System.out.println("Out of bounds");
			return true;
		}else if( newPos.getyPosition() < 0 || newPos.getyPosition() > yBoundary) {
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
	private boolean isSolid(Coordinate position) {
		if(walls.isEmpty()) return false;
		for (Wall wall: walls){
			if(wall.willCollide(position)) {
				System.out.println("Colliding with Wall");
				return true;
			}
		}
		return false;
	}
	
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public ArrayList<Entity> getPlayerInventory() {
		return playerOne.getInventory();
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
}
	
//	public void run() {
//		init();
//		
//		while(active) {
//			update();
//			render();
//		}
//		stop();
//	}
//	
//	public synchronized void start() {
//		if(!active) {
//			active = true;
//			thread = new Thread(this);
//			thread.start();
//		}
//	}
//	
//	public synchronized void stop() {
//		if (active) {
//			try {
//				thread.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		}
//		active = false;
//	}

	

