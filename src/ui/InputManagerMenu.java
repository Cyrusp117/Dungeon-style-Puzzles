package ui;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import entities.Arrow;
import entities.Bomb;
import entities.Boulder;
import entities.Coordinate;
import entities.Door;
import entities.FloorSwitch;
import entities.HoverPotion;
import entities.Hunter;
import entities.InvincibilityPotion;
import entities.Key;
import entities.Pit;
import entities.Sword;

public class InputManagerMenu extends InputManager {
	
	public InputManagerMenu(Application application) {
		super(application);
		System.out.println("Press 1 for Preset dungeon #1 and 0 for Custom dungeon");
	}

	/**
	 * Actions when a key is pressed
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	    
		if (key == KeyEvent.VK_2) {
			Game game = new Game("Blank 10x10", 10*32, 10*32);
	        System.out.println("Blank 10x10 Dungeon");
	    	app.getFrame().removeKeyListener(this);
	    	app.getFrame().addKeyListener(new InputManagerUser(game, app));
		}
		
		if (key == KeyEvent.VK_1) {
	    	Game game = new Game("Preset #1", 10*32, 10*32);
	        System.out.println("First Preset Dungeon");
	        Coordinate pitPos = new Coordinate(2*32, 2*32);
	        Coordinate hunterPos = new Coordinate(4*32, 1*32);
	        Coordinate hoverPos = new Coordinate(2*32, 1*32);
	        Coordinate swordPos = new Coordinate(5*32, 1*32);
	        Coordinate fsPos = new Coordinate(1*32, 8*32);
	        Coordinate boulderPos = new Coordinate(5*32, 5*32);
	        Coordinate arrowPos = new Coordinate(4*32, 4*32);
	        Coordinate key1Pos = new Coordinate(7*32, 1*32);
	        Coordinate key2Pos = new Coordinate(8*32, 1*32);
	        Coordinate Door1Pos = new Coordinate(2*32, 7*32);
	        Coordinate Door2Pos = new Coordinate(3*32, 7*32);
	        Coordinate Bomb1Pos = new Coordinate(7*32, 7*32);
	        Coordinate Bomb2Pos = new Coordinate(8*32, 7*32);
	        Coordinate IPpotPos = new Coordinate(5*32, 4*32);
	        game.addEntity(new Pit(pitPos));
	        game.addEntity(new HoverPotion(hoverPos));
	        game.addEntity(new Sword(swordPos));
	        game.addEntity(new Hunter(hunterPos));
	        game.addEntity(new FloorSwitch(fsPos));
	        game.addEntity(new Boulder(boulderPos));
	        game.addEntity(new Arrow(arrowPos));
	        game.addEntity(new Key(key1Pos));
	        game.addEntity(new Key(key2Pos));
	        game.addEntity(new Door(Door1Pos));
	        game.addEntity(new Door(Door2Pos));
	        game.addEntity(new Bomb(Bomb1Pos));
	        game.addEntity(new Bomb(Bomb2Pos));
	        game.addEntity(new InvincibilityPotion(IPpotPos));
	    	app.getFrame().removeKeyListener(this);
	    	app.getFrame().addKeyListener(new InputManagerUser(game, app));
		}
		
	    if (key == KeyEvent.VK_0) {
			Game playerGame = makeGame("Custom");
			app.getFrame().removeKeyListener(this);
		    app.getFrame().addKeyListener(new InputManagerUser(playerGame, app));
			
	    }
	    

	}
	


	public Game makeGame(String title) {
    	// CREATING GAME
    	System.out.println("Specify board dimensions e.g. '#horizontalTiles <= 40 #verticalTiles <= 20'");
		int valid = 0;
		
		int xSize = 1, ySize = 1;
		Scanner sc = null;
		sc = new Scanner(System.in);
		
		while(valid == 0) {
			valid = 1;
			xSize = sc.nextInt();
			ySize = sc.nextInt();
			if(xSize > 40 || xSize <= 2 || ySize > 20 || ySize <= 2) {
				System.out.println("Please enter valid dimensions: 2 < #horizontalTiles <= 40 2 < #verticalTiles <= 20 ");
				valid = 0;
			}
			
		}
		//sc.close();
		
		xSize = xSize * 32;
		ySize = ySize * 32;
		Game game = new Game(title, xSize, ySize);
		System.out.println("Game created with xBoundary: " + xSize + " and yBoundary: " + ySize);
		return game;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	


}
