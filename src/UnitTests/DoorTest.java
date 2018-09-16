package UnitTests;

import org.junit.Test;

import entities.Coordinate;
import entities.Door;
import entities.Key;

public class DoorTest extends testSetup {

	@Test
	public void placeDoor() {
		Door.resetNumOfDoors();
		Coordinate doorPos = new Coordinate(1*32, 7*32);
		Door door = new Door(doorPos);
		game.addEntity(door);
		game.printGame();
		assert(door.getDoorId() == 0);
		assert(door.getNumOfDoors() == 1);
		assert(game.getEntity(doorPos) instanceof Door);
	}

	@Test
	public void openDoor1() {
		Key.resetNumOfKeys();
		Door.resetNumOfDoors();
		Coordinate key1Pos = new Coordinate(7*32, 1*32);
        Coordinate Door1Pos = new Coordinate(2*32, 7*32);
        Key key1 = new Key(key1Pos);
        game.addEntity(key1);
        Door door1 = new Door(Door1Pos);
        game.addEntity(door1);
        move(RIGHT, 6);
        move(DOWN, 6);
        move(LEFT, 5);
		game.printGame();
		assert(door1.getState() == 1);
	}
	
	@Test
	public void openDoor2() {
		Key.resetNumOfKeys();
		Door.resetNumOfDoors();
		Coordinate key1Pos = new Coordinate(6*32, 7*32);
		Coordinate key2Pos = new Coordinate(7*32, 1*32);
        Coordinate Door1Pos = new Coordinate(7*32, 4*32);
        Coordinate Door2Pos = new Coordinate(7*32, 3*32);
        Key key1 = new Key(key1Pos);
        game.addEntity(key1);
        Key key2 = new Key(key2Pos);
        game.addEntity(key2);
        Door door1 = new Door(Door1Pos);
        game.addEntity(door1);
        Door door2 = new Door(Door2Pos);
        game.addEntity(door2);
        move(RIGHT, 6);
        move(DOWN, 3);
		game.printGame();
		assert(door1.getState() == 0);
		assert(door2.getState() == 1);
	}
}
