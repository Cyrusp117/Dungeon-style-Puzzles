package ui;

import entities.Coordinate;
import entities.Player;

public class Game{ // implements Runnable{
	private Application app;
	private int width, height;
	private String title;
	private Player playerOne;
	private InputManager playerInput;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.playerInput = new InputManager(this);
	}
	
	private void update() {
		playerOne.setDx(playerInput.getDx());
		//CheckValidMove
		playerOne.setDy(playerInput.getDy());
		playerOne.move();
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
