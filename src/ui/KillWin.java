package ui;

import entities.Coordinate;
import entities.Enemy;
import entities.Entity;
import entities.Treasure;
import entities.TreasureGoblin;

public class KillWin extends WinDecorator {
	
	public KillWin(CheckWinCon checkWin) {
		super(checkWin);
	}

	
	@Override
	public boolean checkWinCondition(Game game) {
		int allTreasure = 1;
		if(checkWin.checkWinCondition(game) == false){
			return false;
		}else {
			for (Entity entity : game.getEntities()) {
				if (entity instanceof Enemy) {
					return false;
				}
			}
			return true;
		}
	}

}
