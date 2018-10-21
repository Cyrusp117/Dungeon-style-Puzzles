package ui;

import entities.Entity;
import entities.Treasure;
import entities.TreasureGoblin;

public class TreasureWin extends WinDecorator {
	
	public TreasureWin(CheckWinCon checkWin) {
		super(checkWin);
	}

	
	@Override
	public boolean checkWinCondition(Game game) {
		int allTreasure = 1;
		if(checkWin.checkWinCondition(game) == false){
			return false;
		}else {
			for (Entity entity : game.getEntities()) {
				// Checks if all treasure has been picked up
				if (entity instanceof Treasure){
					allTreasure = 0;
					//also check treasure goblin for this condition
				} else if (entity instanceof TreasureGoblin) {
					TreasureGoblin goblin = (TreasureGoblin)entity;
					if(goblin.getTreasure() != null) {
						allTreasure = 0;
					}
				}
			}
			return (allTreasure == 1);
		}
	}

}
