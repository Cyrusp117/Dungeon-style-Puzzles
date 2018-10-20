package ui;

import entities.Arrow;
import entities.Bomb;
import entities.Boulder;
import entities.Coordinate;
import entities.Coward;
import entities.Door;
import entities.Entity;
import entities.Exit;
import entities.FloorSwitch;
import entities.HoverPotion;
import entities.Hunter;
import entities.InvincibilityPotion;
import entities.Key;
import entities.Pit;
import entities.Strategist;
import entities.Sword;
import entities.Treasure;
import entities.Wall;

public class EntityFactory {
	private Entity concreteEntity = null;
	
	public Entity createEntity(String entityName, Coordinate co) {
		if(entityName.equals("Arrow")) {
			concreteEntity = new Arrow(co);
		}else if(entityName.equals("Bomb")) {
			concreteEntity = new Bomb(co);
		}else if(entityName.equals("Boulder")) {
			concreteEntity = new Boulder(co);
		}else if(entityName.equals("Coward")) {
			concreteEntity = new Coward(co);
		}else if(entityName.equals("Door")) {
			concreteEntity = new Door(co);
		}else if(entityName.equals("Exit")) {
			concreteEntity = new Exit(co);
		}else if(entityName.equals("FloorSwitch")) {
			concreteEntity = new FloorSwitch(co);
		}else if(entityName.equals("HoverPotion")) {
			concreteEntity = new HoverPotion(co);
		}else if(entityName.equals("Hunter")) {
			concreteEntity = new Hunter(co);
		}else if(entityName.equals("InvincibilityPotion")) {
			concreteEntity = new InvincibilityPotion(co);
		}else if(entityName.equals("Key")) {
			concreteEntity = new Key(co);
		}else if(entityName.equals("Pit")) {
			concreteEntity = new Pit(co);
		}else if(entityName.equals("Strategist")) {
			concreteEntity = new Strategist(co);
		}else if(entityName.equals("Sword")) {
			concreteEntity = new Sword(co);
		}else if(entityName.equals("Treasure")) {
			concreteEntity = new Treasure(co);
		}else if(entityName.equals("Wall")) {
			concreteEntity = new Wall(co);
		}
		
		return concreteEntity;
	}

}
