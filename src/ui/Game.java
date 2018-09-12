package ui;

import java.util.ArrayList;

import entities.Coordinate;
import entities.Player;
import entities.Wall;

public class Game{ // implements Runnable{
	private Application app;
	private int width, height;
	private String title;
	private Player playerOne;
	private InputManager playerInput;
	private ArrayList<Wall> walls;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.playerInput = new InputManager(this);
		this.walls = new ArrayList<>();
	}
	
	private void update() {
		playerOne.setDx(playerInput.getDx());
		playerOne.setDy(playerInput.getDy());
		Coordinate newPos = playerOne.move();
		if(!isOutOfBounds(width, height, newPos) && !isSolid(newPos)) {
			playerOne.setPosition(newPos);
		}
		printPlayerCoordinates();
	}
	
	public void init() {
		Coordinate position = new Coordinate(0,0); // For test, this would be specified by user
		createPlayer(position);
		app = new Application(title, width, height);
		app.getFrame().addKeyListener(playerInput); //refactor this
	}
	
	public void newTurn() {
		update();
	}

	private void printPlayerCoordinates() {
		System.out.println(playerOne.returnPosition());
	}
	
	public void createPlayer(Coordinate position) {
		playerOne = new Player(position);
	}

	public void generatePerimeter() {
		int i,j;
		for(i = 0; i <= width; i += 32) {
			for(j = 0; j <= height; j+= 32) {
				if(j == 0 || j == height || i == 0 || i == width) {
					Coordinate currentPosition = new Coordinate(i,j);
					Wall newWall = new Wall(currentPosition, true);
					addWall(newWall);	
				}
			}
		}
	}
	
	private void addWall(Wall wall) {
		walls.add(wall);
	}
	
	// Safety net to make sure the character can't go outside of the game
	public boolean isOutOfBounds(int xBoundary, int yBoundary, Coordinate newPos) {
		System.out.println("Xboundary: " + xBoundary +" Yboundary: " + yBoundary);
		if(newPos.getxPosition() < 0 || newPos.getxPosition() > xBoundary ) {
			return true;
		}else if( newPos.getyPosition() < 0 || newPos.getyPosition() > yBoundary) {
			return true;
		}
		return false;
	}

	private boolean isSolid(Coordinate position) {
		if(walls.isEmpty()) return false;
		for (Wall wall: walls){
			if(wall.willCollide(position)) {
				return true;
			}
		}
		return false;
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

	
}
