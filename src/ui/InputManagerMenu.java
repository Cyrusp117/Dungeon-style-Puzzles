package ui;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import entities.Coordinate;
import entities.HoverPotion;
import entities.Hunter;
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
	    
		if (key == KeyEvent.VK_1) {
	    	Game game = new Game("Preset #1", 10*32, 10*32);
	        System.out.println("First Preset Dungeon");
	        Coordinate pitPos = new Coordinate(2*32, 2*32);
	        Coordinate hunterPos = new Coordinate(4*32, 1*32);
	        Coordinate hoverPos = new Coordinate(2*32, 1*32);
	        Coordinate swordPos = new Coordinate(5*32, 1*32);
	        game.addEntity(new Pit(pitPos));
	        game.addEntity(new HoverPotion(hoverPos));
	        game.addEntity(new Sword(swordPos));
	        game.addEntity(new Hunter(hunterPos));
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

	
	
	/**
	 * Actions when a key is released (currently nothing)
	 */

	public void keyReleased(KeyEvent e) {
		
	}
	

	/**
	 * Actions when a key is typed (currently nothing)
	 */

	public void keyTyped(KeyEvent e) {
		
	}



}
