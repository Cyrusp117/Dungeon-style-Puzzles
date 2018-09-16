package UnitTests;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Bomb;
import entities.Coordinate;
import entities.HoverPotion;
import entities.Key;
import entities.Player;
import entities.Sword;
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
		game.addEntity(hPot);
		player.pickUp(hPot);
		assertTrue(player.hasItem(hPot));
	}
	
	@Test
	public void testHasItem() {
		Coordinate itemPlace = new Coordinate(32,32);
		HoverPotion hPot = new HoverPotion(itemPlace);
		assertFalse(player.hasItem(hPot));
		player.getInventory().add(hPot);
		assertTrue(player.hasItem(hPot));
	}
	
	@Test
	public void testIsAlive() {
		player.setState(1);
		assertTrue(player.isAlive());
		player.setState(0);
		assertFalse(player.isAlive());
	}
	
	@Test
	public void testHitUsingSword() {
		Coordinate itemPlace = new Coordinate(32,32);
		Sword bigHugeBlade = new Sword(itemPlace);
		player.getInventory().add(bigHugeBlade);
		assertEquals(bigHugeBlade.getDurability(),5);
		player.hitUsingSword();
		assertEquals(bigHugeBlade.getDurability(),4);
		player.hitUsingSword();
		player.hitUsingSword();
		player.hitUsingSword();
		player.hitUsingSword();
		assertFalse(player.hasItem(bigHugeBlade));
	}
	
	@Test
	public void testRemoveItem() {
		Coordinate itemPlace = new Coordinate(32,32);
		Sword bigHugeBlade = new Sword(itemPlace);
		player.getInventory().add(bigHugeBlade);
		assertTrue(player.hasItem(bigHugeBlade));
		player.removeItem(bigHugeBlade);
		assertFalse(player.hasItem(bigHugeBlade));
	}
	
	@Test
	public void testRemoveKey() {
		Coordinate itemPlace = new Coordinate(32,32);
		Key bigKey = new Key(itemPlace);
		player.getInventory().add(bigKey);
		assertTrue(player.hasItem(bigKey));
		player.removeKey(0);
		assertFalse(player.hasItem(bigKey));
		
	}
	
	@Test
	public void testLightAndDropBomb() {
		Coordinate itemPlace = new Coordinate(32,32);
		Bomb bigBomb = new Bomb(itemPlace);
		player.getInventory().add(bigBomb);
		assertTrue(player.hasItem(bigBomb));
		player.lightAndDropBomb();
		assertFalse(player.hasItem(bigBomb));
	}
//	@Test
//	public void testHitUsingSword() {
//		fail("Not yet implemented");
//	}

}
