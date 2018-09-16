package UnitTests;


import org.junit.Test;

import entities.Coordinate;
import entities.Exit;

public class TestExit extends testSetup {

	Coordinate exitPos = new Coordinate(1*32, 3*32);
	@Test
	public void placeExit() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		Exit exit = new Exit(exitPos);
		game.addEntity(exit);
		game.printGame();
		assert(game.getEntity(exitPos) instanceof Exit);
	}
	
	@Test
	public void testPlayerInteraction() {
		Exit exit = new Exit(exitPos);
		game.addEntity(exit);
		game.printGame();
		assert(!game.victory());
		move(DOWN, 2);
		assert(game.victory());
		move(DOWN, 1);
		assert(!game.victory());
	}

}
