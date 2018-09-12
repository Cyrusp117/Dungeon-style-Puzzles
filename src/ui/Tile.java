package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, tileWidth, tileHeight, null);
	}
}
