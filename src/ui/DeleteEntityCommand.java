package ui;

import entities.Coordinate;
import entities.Entity;
import entities.Player;

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
	}

	/**
	 * Undo the previous action
	 */
	@Override
	public void undo() {
		if(entity.getName().equals("Player")) {
			game.setPlayer((Player)entity);
		}
		game.addEntity(entity);
	}

}
