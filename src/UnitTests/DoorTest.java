package UnitTests;

import org.junit.Test;

import entities.Coordinate;
import entities.Door;
import entities.Key;

public class DoorTest extends testSetup {

	@Test
	public void placeDoor() {
		Coordinate keyPos = new Coordinate(1*32, 7*32);
		Key key = new Key(keyPos);
		game.addEntity(key);
		game.printGame();
		assert(game.getEntity(keyPos) instanceof Key);
	}
	
	@Test
	public void openDoor() {
		Coordinate key1Pos = new Coordinate(7*32, 1*32);
        Coordinate key2Pos = new Coordinate(8*32, 1*32);
        Coordinate Door1Pos = new Coordinate(2*32, 7*32);
        Coordinate Door2Pos = new Coordinate(3*32, 7*32);
        Key key1 = new Key(key1Pos);
        game.addEntity(key1);
        Key key2 = new Key(key2Pos);
        game.addEntity(key2);
        Door door1 = new Door(Door1Pos);
        game.addEntity(door1);
        Door door2 = new Door(Door2Pos);
        game.addEntity(door2);
		game.printGame();
		assert(door1.getState() == 0);
		assert(door2.getState() == 0);
		move(RIGHT, 6);
		assert(player.hasKey(0));
		move(DOWN, 5);
		move(LEFT, 4);
		move(DOWN, 1);
		assert(door1.getState() == 0);
		assert(door2.getState() == 0);
		move(LEFT, 1);
		move(DOWN, 1);
		assert(door1.getState() == 1);
		assert(door2.getState() == 0);
	}
}
