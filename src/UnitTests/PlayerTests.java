package UnitTests;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Coordinate;
import entities.HoverPotion;
import entities.Player;
import ui.Application;
import ui.Game;
import ui.InputManagerMenu;
import ui.InputManagerPlayer;

public class PlayerTests {

	private Player player;
	private int xBoundary;
	private int yBoundary;
	private Robot robot;
	private Game game;
	private Application app;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Coordinate start = new Coordinate(32,32);
		player = new Player(start);
		xBoundary = 320;
		yBoundary = 320;
		Application app = new Application("Title", 32, 32);
		game = new Game("Title", xBoundary, yBoundary);
		KeyListener playerInput = new InputManagerPlayer(game, app);
		app.getFrame().addKeyListener(playerInput);
		game.init();
		game.setPlayerInput((InputManagerPlayer)playerInput);
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
		robot.delay(500);
		System.out.println("Button pressed");
		System.out.println(player.returnX()  + " " + player.returnY());
		assertEquals(player.returnX(), 32);
		assertEquals(player.returnY(), 64);
		System.out.println(player.returnX()  + " " + player.returnY());
	}

	@Test
	public void testPickUp() {
		Coordinate itemPlace = new Coordinate(64,32);
		HoverPotion hPot = new HoverPotion(itemPlace);
		System.out.println(hPot == null);
		game.addEntity(hPot);
		player.pickUp(hPot);
		assertTrue(player.hasItem(hPot));
	}

//	@Test
//	public void testHitUsingSword() {
//		fail("Not yet implemented");
//	}

}
