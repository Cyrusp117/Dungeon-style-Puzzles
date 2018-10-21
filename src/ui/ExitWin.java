package ui;

import entities.Coordinate;
import entities.Enemy;
import entities.Entity;
import entities.Exit;
import entities.FloorSwitch;
import entities.Player;
import entities.Treasure;
import entities.TreasureGoblin;

public class ExitWin extends WinDecorator {
	
	public ExitWin(CheckWinCon checkWin) {
		super(checkWin);
	}

	
	@Override
	public boolean checkWinCondition(Game game) {
		Player player = game.getPlayer();
		for (Entity entity : game.getEntities()) {
			if (entity instanceof Exit){
				if(entity.getPosition().equals(player.getPosition())) {
					if (player.isAlive()) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
