package ui;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class InputManagerMenu extends InputManager {
	
	public InputManagerMenu(Application application) {
		super(application);
		System.out.println("Press 1 for Designer mode and 2 for Player mode");
	}

	/**
	 * Actions when a key is pressed
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	    
		if (key == KeyEvent.VK_1) {
			Game playerGame = makeGame("Designer");
			app.getFrame().removeKeyListener(this);
		    playerGame.changeState(new InputManagerDesigner(playerGame, app));
			playerGame.init();
			
		}
	    if (key == KeyEvent.VK_2) {
	    	Game playerGame = makeGame("Player");
	    	app.getFrame().removeKeyListener(this);
	    	playerGame.changeState(new InputManagerPlayer(playerGame, app));
			playerGame.init();
			
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
