package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Coordinate;
import entities.Entity;

/** 
 * 
 * CURRENTLY UNUSED JAVA FILE, BASIS FOR UI LATER DOWN THE ROAD.
 *
 */
public class Tile {
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	protected BufferedImage texture;
	protected final int id;
	private Coordinate coordinate;
	private ArrayList<Entity> entities;
	
	public Tile(int id, Coordinate position) {
		this.id = id;
		entities = new ArrayList<>();
		coordinate = position;
	}
	
	public int getId() {
		return id;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, tileWidth, tileHeight, null);
	}
	
	public void addEntity(Entity entity) {
		if(entity != null) {
			entities.add(entity);
		}
	}
	
	public void removeEntity(Entity entity) {
		if(entity != null) {
			entities.remove(entity);
		}
	}

	/**
	 * @return the entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
}
