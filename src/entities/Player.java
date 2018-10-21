package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;


public class Player extends Entity {
	
	private static final int ALIVE = 1;
	private static final int DEAD = 0;
	private int dx; // Current x velocity
	private int dy; // Current y velocity
	private int state;
	ArrayList<Entity> inventory;

	
	public Player(Coordinate position){
		super(position);
		dx = 0;
		dy = 0;
		inventory = new ArrayList<Entity>();
		state = ALIVE;
		keyCode = KeyCode.DIGIT1;
	}


	
	/**
	 * 
	 * @return the newPosition requested to be moved to by the player
	 */

	public Coordinate getMove() {
	    int newX = position.getX() + dx;
	    int newY = position.getY() + dy;
	    Coordinate newPos = new Coordinate(newX, newY);
	    return newPos;
	 
	}
	/**
	 * 
	 * @param An entity
	 * @return True if entity exists in inventory
	 */
	public boolean hasItem(Entity entity) {
		return inventory.contains(entity);
	}
	
	/**
	 * Picks up entity (No validity check here, this is checked elsewhere)
	 * @param Entity to be picked up
	 */
	public void pickUp(Entity entity) {
		inventory.add(entity);
	}
	
	/**
	 * 
	 * @return True if the player is alive
	 */
	public boolean isAlive () {
		if(this.state == 1) return true;
		return false;
	}
	
	/**
	 * 
	 * @param state - if state = 1, the player is alive
	 * state = 0, the player is dead
	 */
	public void setState(int state) {
		this.state=state;
	}
	



	/**
	 * 
	 * @return Returns an arrayList representing the entities the player currently has
	 */
	public ArrayList<Entity> getInventory() {
		return inventory;
	}

	/**
	 * Returns a string which is the name of the entity
	 */
	public String getName() {
		return "Player";
	}
	
	/**
	 * @return the dx: The change in the x position
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy: The change in y position
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the position, the Coordinate of the player
	 */
	public Coordinate getPosition(){
		return position;
	}
	
	/**
	 * @param position, the Coordinate of the player to set.
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	/**
	 * @return the X Coordinate of the player (int)
	 */
	public int getX() {
		return position.getX();
	}
	
	/**
	 * @return the Y Coordinate of the player (int)
	 */
	public int returnY() {
		return position.getY();
	}
	
	/**
	 * 
	 * @return the string displaying the current Coordinate of the player
	 * The X and the Y Coordinate respectively
	 */
	public String returnPosition() {
		return position.returnPosition();
	}
	
	/**
	 * 
	 * @return A Sword entity if player has one, else null
	 */
	public Sword getSword() {
		for(Entity entity : inventory) {
			if (entity instanceof Sword) return (Sword)entity;
		}
		return null;
	}
	
	/**
	 * Reduces the durability of the player's sword
	 */
	public void hitUsingSword() {
		Sword curSword = getSword();
		curSword.reduceDurability();
		if(curSword.getDurability() == 0) {
			inventory.remove(curSword);
		}
	}
	
	/**
	 * 
	 * @param item - String representing item's name
	 * @return True if the player has such an item
	 */
	public boolean hasItem(String item) {
		int has = 0;
		for(Entity e: this.getInventory()) {
			if(e.getName().equals(item)) {
				has = 1;
			}
		}
		if(has == 1) {
			return true;
		}else {
			return false;
		}

	}
	
	/**
	 * 
	 * @param o - The entity to be removed from player's inventory
	 */
	public void removeItem(Entity o) {
		this.getInventory().remove(o);
	}
	
	/**
	 * 
	 * @param KeyId - the key with KeyId will attempt to be removed
	 * @return True if remove was successful, else false
	 */
	public boolean removeKey(int KeyId) {
		int r = 0;
		Key remove = null;
		for(Entity e: this.getInventory()) {
			if(e.getName().equals("Key")) {
				Key key = (Key) e;
				if(key.getKeyId() == KeyId) {
					r = 1;
					remove = key;
				}
			}
		}
		//remove key with specific id from inventory,and return true
		if(r == 1) {
			this.removeItem(remove);
			return true;
		}else {
			return false;
		}

	}
	
	/**
	 * 
	 * @param DoorId
	 * @return True if player has a key that can open door with
	 * an id of DoorId 
	 */
	public boolean hasKey(int DoorId) {
		int has = 0;
		for(Entity e: this.getInventory()) {
			if(e.getName().equals("Key")) {
				Key key = (Key) e;
				if(key.getKeyId() == DoorId) {
					has = 1;
				}
				System.out.println("KeyID: " + key.getKeyId());
			}
		}
		if(has == 1) {
			return true;
		}else {
			return false;
		}

	}
	
	
	/**
	 * Activates the bomb and removes it from players inventory
	 * @return The bomb that was activated
	 */
	public Bomb setBomb() {
		for(Entity e: this.getInventory()) {
			if(e.getClass() == Bomb.class) {
				Bomb bomb = (Bomb)e;
				bomb.light();
				this.removeItem(e);
				return bomb;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param entityClass
	 * @return True if player's inventory has an entity of 
	 * class entityClass
	 */
	public boolean hasEntity(Class entityClass) {
		for(Entity e: this.getInventory()) {
			if (e.getClass() == entityClass) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * @return True if entity can be placed on top of player
	 */
	@Override
	public boolean canBePlacedOnTop(Entity entity) {
		if (entity instanceof Arrow) { return true; }
		if (entity instanceof Bomb) { return true; }
		if (entity instanceof Bone) { return true; }
		if (entity instanceof Exit) { return true; }
		if (entity instanceof Pit) { return true; }
		if (entity instanceof FloorSwitch) { return true; }
		return false;
	}
	
}



