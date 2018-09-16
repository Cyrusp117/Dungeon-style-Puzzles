package UnitTests;

import org.junit.Test;

import entities.Coordinate;
import entities.HoverPotion;
import entities.Pit;

public class TestPit extends testSetup {

	Coordinate pitPos = new Coordinate(1*32, 4*32);
	@Test
	public void placePit() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		Pit pit = new Pit(pitPos);
		game.addEntity(pit);
		game.printGame();
		assert(game.getEntity(pitPos) instanceof Pit);
	}
	
	@Test
	public void testPlayerNoPotion() {
		Pit pit = new Pit(pitPos);
		game.addEntity(pit);
		game.printGame();
		assert(player.isAlive());
		move(DOWN, 3);
		assert(!player.isAlive());
	}
	
	@Test
	public void testPlayerWithPotion() {
		Pit pit = new Pit(pitPos);
		HoverPotion hp = new HoverPotion(new Coordinate(1*32, 3*32));
		game.addEntity(hp);
		game.addEntity(pit);
		game.printGame();
		assert(player.isAlive());
		move(DOWN, 3);
		assert(player.isAlive());
	}
}
