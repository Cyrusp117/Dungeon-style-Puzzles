package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Coordinate;
import entities.Player;
import ui.Game;

class PlayerTest {

	private Player player;
	private int xBoundary;
	private int yBoundary;
	private Robot robot;
	private Game game;
	@BeforeEach
	public void setUp() throws Exception {
		Coordinate position = new Coordinate(32,32);
		player = new Player(position);
		xBoundary = 320;
		yBoundary = 320;
		Game game = new Game("Title", xBoundary, yBoundary);
		game.init();
		game.setPlayerOne(player);
		this.robot = new Robot();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void RobottestMove() {
		robot.waitForIdle();
		robot.keyPress(KeyEvent.VK_DOWN);
		System.out.println("Button pressed");
		
		assertEquals(player.returnX(), 32);
		assertEquals(player.returnY(), 64);
		System.out.println(player.returnX()  + " " + player.returnY());
	}

	@Test
	void testGetPosition() {
		
	}

	@Test
	void testIsOutOfBounds() {
		//fail("Not yet implemented");
	}

}
