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
		Coordinate arrowPos = new Coordinate(1, 5);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		game.printGame();
		assert(game.getEntity(arrowPos) instanceof Arrow);
	}

	@Test
	public void pickUpArrow() {
		assert(!player.hasItem("Arrow"));
		Coordinate arrowPos = new Coordinate(1, 3);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(DOWN, 3);
		move(RIGHT, 3);
		assert(player.hasItem("Arrow"));
	}
	
	@Test
	public void shootArrowUp() {
		Coordinate arrowPos = new Coordinate(1, 3);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(DOWN, 3);
		move(RIGHT, 3);
		move(KeyEvent.VK_2, 1);
		Coordinate hunterPos = new Coordinate(4, 1);
		game.addEntity(new Hunter(hunterPos));
		assert(game.getEntity(hunterPos) instanceof Hunter);
		move(RIGHT, 3);
		assert(game.getEntity(hunterPos) == null);
	}

	@Test
	public void shootArrowDown() {
		Coordinate arrowPos = new Coordinate(3, 1);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(RIGHT, 3);
		move(DOWN, 3);
		move(KeyEvent.VK_3, 1);
		Coordinate hunterPos = new Coordinate(7, 8);
		game.addEntity(new Hunter(hunterPos));
		assert(game.getEntity(hunterPos) instanceof Hunter);
		move(RIGHT, 3);
		assert(game.getEntity(hunterPos) == null);
	}
	
	@Test
	public void shootArrowLeft() {
		Coordinate arrowPos = new Coordinate(4, 1);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(RIGHT, 3);
		Coordinate hunterPos = new Coordinate(3, 1);
		game.addEntity(new Hunter(hunterPos));
		move(RIGHT, 1);
		move(KeyEvent.VK_4, 1);
		robot.delay(2000);
		move(RIGHT, 1);
		move(RIGHT, 1);
		assert(game.getEntity(hunterPos) == null);
	}
	
	@Test
	public void shootArrowRight() {
		Coordinate arrowPos = new Coordinate(1, 2);
		arrow = new Arrow(arrowPos);
		game.addEntity(arrow);
		move(DOWN, 2);
		move(KeyEvent.VK_5, 1);
		Coordinate hunterPos = new Coordinate(9, 9);
		game.addEntity(new Hunter(hunterPos));
		assert(game.getEntity(hunterPos) instanceof Hunter);
		move(DOWN, 6);
		assert(game.getEntity(hunterPos) == null);
	}
}
