package UnitTests;


import org.junit.Test;

import entities.Coordinate;
import entities.Exit;

public class TestExit extends testSetup {

	Coordinate exitPos = new Coordinate(1, 3);
	@Test
	public void placeExit() {
		//Coordinate wallPos = new Coordinate(1, 5);
		Exit exit = new Exit(exitPos);
		game.addEntity(exit);
		game.printGame();
		assert(game.getFirstEntity(exitPos) instanceof Exit);
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
