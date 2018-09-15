
package entities;

import java.awt.event.KeyEvent;

public class Boulder extends Entity {
	public Boulder(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_B;
		this.movable = false;
	}
	
	public Coordinate move(Coordinate co, Graph g) {
		return position;
	}
	
	public boolean interactWithPlayer(int x,int y) {
		int newX = position.getxPosition() + x;
	    int newY = position.getyPosition() + y;
	    Coordinate newPos = new Coordinate(newX, newY);
	    this.setPosition(newPos);
	    return false;
	}
	
	public boolean interactWithBomb() {
		//delete this boulder
		return true;
	}
	
	public String getName() {
		return "Boulder";
	}
	
}
=======

package entities;

import java.awt.event.KeyEvent;

public class Boulder extends Entity {
	public Boulder(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_B;
	}
	
	public boolean interactWithPlayer(int x,int y) {
		int newX = position.getxPosition() + x;
	    int newY = position.getyPosition() + y;
	    Coordinate newPos = new Coordinate(newX, newY);
	    this.setPosition(newPos);
	    return false;
	}
	
	public boolean interactWithBomb() {
		//delete this boulder
		return true;
	}
	
	public String getName() {
		return "Boulder";
	}
	
}
