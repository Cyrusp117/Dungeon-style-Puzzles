package UnitTests;


import java.awt.event.KeyEvent;

import org.junit.Test;

import entities.Arrow;
import entities.Coordinate;
import entities.Hunter;

public class ArrowTest extends testSetup {

	Arrow arrow;
	@Test
	public void placeArrow() {
		Coordinate arrowPos = new Coordinate(1*32, 5*32);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		game.printGame();
		assert(game.getEntity(arrowPos) instanceof Arrow);
	}

	@Test
	public void pickUpArrow() {
		assert(!player.hasItem("Arrow"));
		Coordinate arrowPos = new Coordinate(1*32, 3*32);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(DOWN, 3);
		move(RIGHT, 3);
		assert(player.hasItem("Arrow"));
	}
	
	@Test
	public void shootArrowUp() {
		Coordinate arrowPos = new Coordinate(1*32, 3*32);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(DOWN, 3);
		move(RIGHT, 3);
		move(KeyEvent.VK_2, 1);
		Coordinate hunterPos = new Coordinate(4*32, 1*32);
		game.addEntity(new Hunter(hunterPos));
		assert(game.getEntity(hunterPos) instanceof Hunter);
		move(RIGHT, 3);
		assert(game.getEntity(hunterPos) == null);
	}

}
