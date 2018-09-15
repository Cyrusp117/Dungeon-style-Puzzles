package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity{
	
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
		keyCode = KeyEvent.VK_JAPANESE_HIRAGANA;
	}

	/**
	 * 
	 * @return the newPosition requested to be moved to by the player
	 */
	public Coordinate move() {
		System.out.println("DX = " + dx + " DY = " + dy);
		//System.out.println(position.getxPosition());
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
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
		System.out.println("Picked up: " + entity.getName());
	}
	

	public boolean isAlive () {
		if(this.state == 1) return true;
		return false;
	}
	
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
	public int returnX() {
		return position.getxPosition();
	}
	
	/**
	 * @return the Y Coordinate of the player (int)
	 */
	public int returnY() {
		return position.getyPosition();
	}
	
	/**
	 * 
	 * @return the string displaying the current Coordinate of the player
	 * The X and the Y Coordinate respectively
	 */
	public String returnPosition() {
		return position.returnPosition();
	}
	


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
	
	public boolean hitUsingSword() {
		int r = 0;
		Entity remove = null;
		if(this.hasItem("Sword")) {
			for(Entity e: this.getInventory()) {
				if(e.getName().equals("Sword")) {
					Sword sword = (Sword) e; 
					sword.setHitTime();
					if(sword.getHitTime() == 0) {
						remove = e;
						r = 1;
					}
				}
			}
		}
		//hit using sword
		if(r == 0 && this.hasItem("Sword")) {
			return true;
		//presss v but don't have sword
		}else if (r== 0 && !this.hasItem("Sword")){
			System.out.println("You don't have sword!");
			return false;
	    //hit using sword and remove the sword from inventory
		}else if(r == 1){
			System.out.println("Remove sword from inventory!");
			this.getInventory().remove(remove);
			return false;
		}
		return false;
	}
	
	
	

	
}



