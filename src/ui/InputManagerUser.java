package ui;

import java.awt.event.KeyEvent;

public class InputManagerUser extends InputManager {

	Game playerGame;
	public InputManagerUser(Game playerGame, Application app) {
		super(app);
		this.playerGame = playerGame;
		System.out.println("Press 1 for designer, 2 for player");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	    
		if (key == KeyEvent.VK_1) {
			app.getFrame().removeKeyListener(this);
		    playerGame.changeState(new InputManagerDesigner(playerGame, app));
			playerGame.init();
			
		}
	    if (key == KeyEvent.VK_2) {
	    	app.getFrame().removeKeyListener(this);
	    	playerGame.changeState(new InputManagerPlayer(playerGame, app));
			playerGame.init();
			
	    }

	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

 
}
