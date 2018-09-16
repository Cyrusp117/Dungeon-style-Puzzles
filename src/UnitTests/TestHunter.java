package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Coordinate;
import entities.Hunter;

public class TestHunter extends testSetup {
	
	Coordinate hunterPos = new Coordinate(5*32, 5*32);
	@Test
	public void placeHunter() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		game.printGame();
		assert(game.getEntity(hunterPos) instanceof Hunter);
	}

	
}
