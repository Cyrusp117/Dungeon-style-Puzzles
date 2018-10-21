package entities;

import javafx.scene.input.KeyCode;

public class Bone extends Entity {

	private int dx = 0;
	private int dy = 0;
	private int timesMoved = 0;
	private int lifeTime = 2; //make this editable later
	
	
	public Bone(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.B;
	}

	

	public Coordinate interactWithPlayer(Player player) {
		player.pickUp(this);
		timesMoved = 0;
		return null;
	}
	
	public String getName() {
		return "Bone";
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setLifeTime(int life) {
		this.lifeTime = life;
	}
	
	public int getLifeTime() {
		return lifeTime;
	}
	
	public int reduceLifeTime() {
		this.lifeTime--;
		return lifeTime;
		
	}

	public Coordinate move() {
		Coordinate newPos;
		if (timesMoved < 2) {
		    int newX = getX() + dx;
		    int newY = getY() + dy;
		    newPos = new Coordinate(newX, newY);
		    timesMoved++;
		} else {
			newPos = position;
		}
		
		return newPos;
	}
	public int timesMoved() {
		return this.timesMoved;
	}

}
