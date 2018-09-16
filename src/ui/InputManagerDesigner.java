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
		allDesignerObjects.add(new FloorSwitch(defaultPos));
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
	}

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

