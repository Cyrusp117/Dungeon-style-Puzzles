package ui;

import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;


import entities.Arrow;
import entities.Boulder;
import entities.Coordinate;
import entities.Entity;
import entities.HoverPotion;
import entities.Hunter;
import entities.InvincibilityPotion;
import entities.Pit;
import entities.Sword;
import entities.Treasure;
import entities.Wall;

// to be refactored into the Game
public class InputManagerDesigner extends InputManagerPlayer {
	ArrayList<Entity> allDesignerObjects;
	
	public InputManagerDesigner(Game game, Application app) {
		super(game, app);
		//System.out.println("Designer Mode Controls: Escape to return to menu, F1 - Treasure Placement, H - Hunter Placement, B - Backpack");
		allDesignerObjects = new ArrayList<Entity>();
		Coordinate defaultPos = new Coordinate(1, 1);
		allDesignerObjects.add(new Hunter(defaultPos));
		allDesignerObjects.add(new Arrow(defaultPos));
		allDesignerObjects.add(new HoverPotion(defaultPos));
		allDesignerObjects.add(new Treasure(defaultPos));
		allDesignerObjects.add(new Sword(defaultPos));
		allDesignerObjects.add(new Boulder(defaultPos));
		allDesignerObjects.add(new Pit(defaultPos));
		allDesignerObjects.add(new InvincibilityPotion(defaultPos));
		allDesignerObjects.add(new Wall(defaultPos));
		for (Entity entity : allDesignerObjects) {
			entity.getDesignerDescription();
		}
	}
	
	/**
	 * Additional actions for Designer version of InputManager
	 */
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyPressed(e);
	}
	
	@Override
	public void extraFunctions(KeyEvent e) {
		int key = e.getKeyCode();
		
		// ideally for (entity entity : game.getEntities) {
		// 	 if (entity instanceof DesignerObject ) {
		//		if (entity.getKeyCode == key) {
		//			system.out.println("Please enter coordinates of entity.getName(): ") // also scan two integers
		//			game.addEntity(entity)
		// ^ refactor into this later
		
	 
		Entity requiredEntity = null;
		for (Entity entity : allDesignerObjects) {
			if (entity.getKeyCode() == key) {
				System.out.println("Please enter co-ordinates of " + entity.getName() + ": ");
				requiredEntity = entity;
			}
		}
		
		if (requiredEntity != null) { 
			Scanner sc = new Scanner(System.in);
			dx = sc.nextInt()*32;
			dy = sc.nextInt()*32;
			Coordinate entityPos = new Coordinate(dx, dy);
			
			Class<? extends Entity> c = requiredEntity.getClass();
			Constructor<? extends Entity> constructor = null;
			Entity instance = null;
			try {
				constructor = c.getConstructor(Coordinate.class);
				instance = constructor.newInstance(entityPos);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			game.addEntity(instance);
		}
		
//	    if (key == KeyEvent.VK_F1) {
//	    	System.out.println("Please enter co-ordinates of Treasure: ");
//			Scanner sc = new Scanner(System.in);
//			dx = sc.nextInt()*32;
//			dy = sc.nextInt()*32;
//			
//			Coordinate treasurePos = new Coordinate(dx, dy);
//			if (game.addEntity(new Treasure(treasurePos))) {
//				System.out.println("adding treasure at " + dx + dy);
//			} else {
//				System.out.println("Couldn't add treasure");
//			}
//	    }
//	    
//	    if (key == KeyEvent.VK_F2) {
//	    	System.out.println("Please enter co-ordinates of Pit: ");
//			Scanner sc = new Scanner(System.in);
//			dx = sc.nextInt()*32;
//			dy = sc.nextInt()*32;
//			
//			Coordinate pitPos = new Coordinate(dx, dy);
//			if (game.addEntity(new Pit(pitPos))) {
//				System.out.println("adding pit at " + dx + dy);
//			} else {
//				System.out.println("Couldn't add pit");
//			}
//	    }
//	    
//	    if (key == KeyEvent.VK_B) {
//	    	System.out.println("Inventory contents: ");
//	    	for (Entity curItem : game.getPlayerInventory()) {
//	    		System.out.print(curItem.getName() + " ");
//	    	}
//	    	System.out.println("\n");
//	    }
//	    
//	    if (key == KeyEvent.VK_H) {
//	    	System.out.println("Enter Hunter's Coordinates: ");
//			Scanner sc = new Scanner(System.in);
//			dx = sc.nextInt()*32;
//			dy = sc.nextInt()*32;
//			Coordinate hunterPos = new Coordinate(dx, dy);
//			if (game.addEntity(new Hunter(hunterPos))) {
//				System.out.println("adding hunter at " + dx + dy);
//			} else {
//				System.out.println("Couldn't add hunter");
//			}
//	    }
		
	
	}


	/**
	 * Actions when a key is released (currently nothing)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	/**
	 * Actions when a key is typed (currently nothing)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * @return the dx the change in x position requested
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @return the dy the change in y position requested
	 */
	public int getDy() {
		return dy;
	}
	
}

