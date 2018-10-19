package ui;

import entities.Coordinate;
import entities.Entity;

public class EntityProducer {
	private EntityFactory factory;
	
	public EntityProducer(EntityFactory factory) {
		this.factory = factory;
	}
	
	public Entity addEntityToGame(Game game, Coordinate co, String entityName) {
		Entity toAdd = factory.createEntity(entityName, co);
		game.addEntity(toAdd);
		return toAdd;
	}
}
