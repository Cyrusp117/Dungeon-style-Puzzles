package UnitTests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

import entities.Arrow;
import entities.Coordinate;
import entities.Entity;
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
	public void pickUpAndShootArrow() {
		assert(!player.hasItem("Arrow"));
		move(DOWN, 3);
		move(RIGHT, 3);
		assert(player.hasItem("Arrow"));
		move(KeyEvent.VK_2, 1);
		Coordinate hunterPos = new Coordinate(4*32, 1*32);
		assert(game.getEntity(hunterPos) instanceof Hunter);
		move(RIGHT, 3);
		assert(game.getEntity(hunterPos) == null);
	}
//	public void setUp() throws Exception {
//		// TODO Auto-generated method stub
//		super.setUp();
//	}
	
//	@Test
//	public void RobotTestMove() {
//		robot.waitForIdle();
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.delay(500);
//		System.out.println("Button pressed");
//		System.out.println(player.returnX()  + " " + player.returnY());
//		assertEquals(player.returnX(), 32);
//		assertEquals(player.returnY(), 64);
//		System.out.println(player.returnX()  + " " + player.returnY());
//	}

}
