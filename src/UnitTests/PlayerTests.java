package UnitTests;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Coordinate;
import entities.HoverPotion;
import entities.Player;
import ui.Game;

public class PlayerTests {

	private Player player;
	private int xBoundary;
	private int yBoundary;
	private Robot robot;
	private Game game;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void RobotTestMove() {
		robot.waitForIdle();
		robot.keyPress(KeyEvent.VK_DOWN);
		System.out.println("Button pressed");
		assertEquals(player.returnX(), 32);
		assertEquals(player.returnY(), 64);
		System.out.println(player.returnX()  + " " + player.returnY());
	}

	@Test
	public void testHasItemEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickUp() {
		Coordinate itemPlace = new Coordinate(32,32);
		HoverPotion hPot = new HoverPotion(itemPlace);
		game.addEntity(hPot);
		//robot.keyPress(KeyEvent.pickupbutton);
		assertTrue(player.hasItem(hPot));
		fail("Not yet implemented");
	}

	@Test
	public void testHitUsingSword() {
		fail("Not yet implemented");
	}

}
