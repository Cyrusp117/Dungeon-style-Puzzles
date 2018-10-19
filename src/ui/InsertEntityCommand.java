package ui;

import entities.Coordinate;
import entities.Entity;

public class InsertEntityCommand implements EditorCommand {

	private EntityProducer producer;
	private Game game;
	private Coordinate co;
	private String entity;
	private Entity concreteEntity = null;
	
	public InsertEntityCommand(EntityProducer producer, Game game, Coordinate co, String entity) {
		this.game = game;
		this.co = co;
		this.entity = entity;
		this.producer = producer;
	}
	
	@Override
	public void execute() {
		concreteEntity = producer.addEntityToGame(game, co, entity);
		// TODO Auto-generated method stub

	}


	@Override
	public void undo() {
		if(concreteEntity != null) {
			game.deleteEntity(concreteEntity);
		}
		// TODO Auto-generated method stub

	}

}
