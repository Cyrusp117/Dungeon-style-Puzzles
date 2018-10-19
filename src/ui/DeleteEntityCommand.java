package ui;

import entities.Coordinate;
import entities.Entity;

public class DeleteEntityCommand implements EditorCommand {
	private Entity entity;
	private Game game;
	
	public DeleteEntityCommand(Game game, Entity entity) {
		this.entity = entity;
		this.game = game;
	}
	@Override
	public void execute() {
		game.deleteEntity(entity);
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		game.addEntity(entity);
		// TODO Auto-generated method stub

	}

}
