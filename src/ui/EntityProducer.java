package ui;

import entities.Coordinate;
import entities.Entity;
import entities.Player;

public class EntityProducer {
	private EntityFactory factory;
	
	public EntityProducer(EntityFactory factory) {
		this.factory = factory;
	}
	
	public Entity addEntityToGame(Game game, Coordinate co, String entityName) {
		boolean added = false;
		Entity toAdd = factory.createEntity(entityName, co);
		if(toAdd != null) {
			added = game.addEntity(toAdd);
		}
		if(toAdd instanceof Player && added == true) {
			System.out.println("added player");
			game.setPlayer((Player)toAdd);
		}
		
		return toAdd;
	}
}
