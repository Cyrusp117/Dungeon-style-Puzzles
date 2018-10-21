package ui;

import entities.Coordinate;
import entities.Enemy;
import entities.Entity;
import entities.FloorSwitch;
import entities.Treasure;
import entities.TreasureGoblin;

public class FloorWin extends WinDecorator {
	
	public FloorWin(CheckWinCon checkWin) {
		super(checkWin);
	}

	
	@Override
	public boolean checkWinCondition(Game game) {
		if(checkWin.checkWinCondition(game) == false){
			return false;
		}else {
			System.out.println("Checking");
			for (Entity entity : game.getEntities()) {
				if (entity instanceof FloorSwitch) {		// could be a lot neater if we had an array of FloorSwitch ?
					FloorSwitch fs = (FloorSwitch)entity; 
					if(!fs.getState()) {
						return false;
					}
				}
			}
			return true;
		}
	}

}
