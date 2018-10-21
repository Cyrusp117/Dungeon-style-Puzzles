package UnitTests;


import org.junit.Test;

import entities.Boulder;
import entities.Coordinate;
import entities.FloorSwitch;

public class TestFloorSwitch extends testSetup {

	Coordinate fsPos = new Coordinate(1, 4);
	@Test
	public void placeFs() {
		//Coordinate wallPos = new Coordinate(1, 5);
		FloorSwitch fs = new FloorSwitch(fsPos);
		game.addEntity(fs);
		game.printGame();
		assert(game.getFirstEntity(fsPos) instanceof FloorSwitch);
	}
	
	@Test
	public void testBoulderInteraction() {
		FloorSwitch fs = new FloorSwitch(fsPos);
		Coordinate boulderPos = new Coordinate(1, 3);
		Boulder boulder = new Boulder(boulderPos);
		game.addEntity(boulder);
		game.addEntity(fs);
		game.printGame();
		move(DOWN, 2);
		assert(game.getEntityExcept(fsPos, fs) instanceof Boulder);
		assert(game.getEntityExcept(fsPos, boulder) instanceof FloorSwitch);
		assert(fs.getState());
		
		// Test if fs is deactivated if boulder pushed out
		move(DOWN, 1);
		assert(!fs.getState());
		assert(game.getFirstEntity(fsPos) instanceof FloorSwitch);
		assert(game.getEntityExcept(fsPos, fs) == null);
		boulderPos = new Coordinate(1, 5);
		assert(boulder.getPosition().equals(boulderPos));
	}

}
