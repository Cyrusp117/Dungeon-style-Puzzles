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
		if(toAdd != null) {
			game.addEntity(toAdd);
		}
		if(entityName.equals("Player")) {
			System.out.println("added player");
			game.createPlayer(co);
			toAdd = game.getPlayer();
		}
		
		return toAdd;
	}
}
