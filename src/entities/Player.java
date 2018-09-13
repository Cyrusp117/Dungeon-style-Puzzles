package entities;

import java.util.ArrayList;
import java.util.Iterator;

import ui.Game;

public class Player extends Entity{
	
	private int dx; // Current x velocity
	private int dy; // Current y velocity
	ArrayList<Entity> inventory;
	
	public Player(Coordinate position){
		super(position);
		dx = 0;
		dy = 0;
		inventory = new ArrayList<Entity>();
	}

	/*
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
	
	public void pickUp(Entity entity) {
		inventory.add(entity);
		System.out.println("Picked up: " + entity.getName());
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
	
	/**
	 * @return the inventory
	 */
	public ArrayList<Entity> getInventory() {
		return inventory;
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
					e.setHitTime();
					if(e.getHitTime() == 0) {
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
	
	
	public String getName() {
		return "Player";
	}
	
}



