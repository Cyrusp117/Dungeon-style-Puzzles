//package ui;
//
//import java.awt.event.KeyEvent;
//
//import entities.Arrow;
//import entities.Bomb;
//import entities.Entity;
//import entities.Player;
//
//
//
//public class InputManagerPlayer extends InputManager {
//	protected Game game;
//	protected int dx = 0;
//	protected int dy = 0;
//	
//	public InputManagerPlayer(Game game, Application app) {
//		super(app);
//		this.game = game;
//		System.out.println("Arrow Keys to move, 1 for Inventory, 2 to shoot arrow, Escape to exit");
//		// TODO Auto-generated constructor stub
//	}
//
//	public void keyPressed(KeyEvent e) {
//		int key = e.getKeyCode();
//		dx = 0;
//		dy = 0;
//		// If the left arrow key is pressed, request to move left by a tile
//	    if (key == KeyEvent.VK_LEFT) {
//	        dx = -1;
//	        dy = 0;
//	        System.out.println("LEFT");
//	        game.update();
//
//	    }
//	    // If the right arrow key is pressed, request to move right by a tile
//	    if (key == KeyEvent.VK_RIGHT) {
//	        dx = 1;
//	        dy = 0;
//	        System.out.println("RIGHT");
//	        game.update();
//
//	    }
//	    // If the up arrow key is pressed, request to move up by a tile
//	    if (key == KeyEvent.VK_UP) {
//	        dy = -1;
//	        dx = 0;
//	        System.out.println("UP");
//	        game.update();
//
//	    }
//	    // If the down arrow key is pressed, request to move down by a tile
//	    if (key == KeyEvent.VK_DOWN) {
//	        dy = 1;
//	        dx = 0;
//	        System.out.println("DOWN");
//	        game.update();
//
//	    }
//	    
//	    if (key == KeyEvent.VK_ESCAPE) {
//	    	dx = 0;
//	    	dy = 0;
//	    	System.out.println("*****  Returning to Menu.. *****");
//	    	app.getFrame().removeKeyListener(this);
//	    	app.getFrame().addKeyListener(new InputManagerMenu(app));
//	    }
//	    
//	    if (key == KeyEvent.VK_1) {
//	    	System.out.println("Inventory contents: ");
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		System.out.print(curItem.getName() + " ");
//	    	}
//	    	System.out.println("\n");
//	    }
//	    
//	    if (key == KeyEvent.VK_2) {
//	    	Arrow arrow = null;
//	    	Player player = game.getPlayer();
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		if (curItem instanceof Arrow) {
//	    			arrow = (Arrow)curItem;
//	    			arrow.setPosition(game.getPlayer().getPosition());
//	    			arrow.setDy(-1); 
//	    			arrow.setDx(0);
//	    			game.addEntity(arrow);
//	    			break;
//	    		}
//	    	}
//	    	if(arrow!=null) {
//	    		System.out.println("Shooting upwards");
//	    		player.removeItem(arrow);
//	    		game.update();
//	    	} else {
//	    		System.out.println("No arrows :(");
//	    	}
//	    }
//	    
//	    if (key == KeyEvent.VK_3) {
//	    	Arrow arrow = null;
//	    	Player player = game.getPlayer();
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		if (curItem instanceof Arrow) {
//	    			arrow = (Arrow)curItem;
//	    			arrow.setPosition(game.getPlayer().getPosition());
//	    			arrow.setDy(1); 
//	    			arrow.setDx(0);
//	    			game.addEntity(arrow);
//	    			break;
//	    		}
//	    	}
//	    	if(arrow!=null) {
//	    		System.out.println("Shooting downwards");
//	    		player.removeItem(arrow);
//	    		game.update();
//	    	} else {
//	    		System.out.println("No arrows :(");
//	    	}
//	    }
//	    
//	    if (key == KeyEvent.VK_4) {
//	    	Arrow arrow = null;
//	    	Player player = game.getPlayer();
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		if (curItem instanceof Arrow) {
//	    			arrow = (Arrow)curItem;
//	      			
//	    			arrow.setPosition(game.getPlayer().getPosition());
//	    			arrow.setDy(0); 
//	    			arrow.setDx(-1);
//	    			game.addEntity(arrow);
//	    			break;
//	    		}
//	    	}
//	    	if(arrow!=null) {
//	    		System.out.println("Shooting left");
//	    		player.removeItem(arrow);
//	    		game.update();
//	    	} else {
//	    		System.out.println("No arrows :(");
//	    	}
//	    }
//	    
//	    if (key == KeyEvent.VK_5) {
//	    	Arrow arrow = null;
//	    	Player player = game.getPlayer();
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		if (curItem instanceof Arrow) {
//	    			arrow = (Arrow)curItem;
//	    			arrow.setPosition(game.getPlayer().getPosition());
//	    			arrow.setDy(0); 
//	    			arrow.setDx(1);
//	    			game.addEntity(arrow);
//	    			break;
//	    		}
//	    	}
//	    	if(arrow!=null) {
//	    		System.out.println("Shooting downwards");
//	    		player.removeItem(arrow);
//	    		game.update();
//	    	} else {
//	    		System.out.println("No arrows :(");
//	    	}
//	    }
//	    
//	    if (key == KeyEvent.VK_V) {
//	    	System.out.println("Checking for bomb");
//	    	Player player = game.getPlayer();
//	    	if(player.hasItem("Bomb")) {
//	    		System.out.println("Light and drop the bomb");
//	    		Bomb placedBomb = player.setBomb();
//	    		placedBomb.setPosition(player.getPosition());
//	    		game.addEntity(placedBomb);
//	    	}
//	    }
//	    extraFunctions(e);
//	}
//	
//	public void extraFunctions(KeyEvent e) {
//		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	public Game getGame() {
//		return this.game;
//	}
//
//	/**
//	 * @return the dx the change in x position requested
//	 */
//	public int getDx() {
//		return dx;
//	}
//
//	/**
//	 * @return the dy the change in y position requested
//	 */
//	public int getDy() {
//		return dy;
//	}
//}
