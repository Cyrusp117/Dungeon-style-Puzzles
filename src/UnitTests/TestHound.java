package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;


public class TestHound extends testSetup {

	@Test
	public void placeHound() {
		Coordinate hunterPos = new Coordinate(3*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		Coordinate houndPos = new Coordinate(32,3*32);
		Hound hound = new Hound(houndPos,hunter);
		game.addEntity(hound);
		game.printGame();
		assert(game.getEntity(houndPos) instanceof Hound);
	}

	@Test
	public void TestOneAway() {
		Coordinate hunterPos = new Coordinate(3*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		Coordinate houndPos = new Coordinate(32,64);
		Hound hound = new Hound(houndPos,hunter);
		game.addEntity(hound);
		game.printGame();
		move(RIGHT, 1);
		assert(player.isAlive());
		move(DOWN,1);
		Coordinate expectedPos = new Coordinate(32,2*32);
		assert(hound.getPosition().equals(expectedPos));
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	@Test
	public void TestVerticalChase() {
		Coordinate hunterPos = new Coordinate(2*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		
		Coordinate houndPos = new Coordinate(7*32,7*32);
		Hound hound = new Hound(houndPos,hunter);
		game.addEntity(hound);
		
		game.printGame();
		move(DOWN, 1);
		Coordinate expectedPos = new Coordinate(1*32, 1*32);
		assert(hunter.getPosition().equals(expectedPos));
		move(DOWN, 6);
		move(RIGHT,1);
		expectedPos = new Coordinate(1*32, 7*32);
		assert(hunter.getPosition().equals(expectedPos));
	}
	
	@Test
	public void TestObstacleRecognition() {
		Coordinate hunterPos = new Coordinate(8*32, 4*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		Coordinate houndPos = new Coordinate(5*32,4*32);
		Hound hound = new Hound(houndPos,hunter);
		game.addEntity(hound);
		
		Coordinate position = new Coordinate(4*32, 1*32);
		game.addEntity(new Boulder(position));
		game.addEntity(new Pit(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new Exit(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new FloorSwitch(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new Sword(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new HoverPotion(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new InvincibilityPotion(position.moveDown()));
		position = position.moveDown();
		game.addEntity(new Key(position.moveDown()));
		position = position.moveDown();
		Door door = new Door(position.moveDown());
		game.addEntity(door);
		position = position.moveLeft().moveDown();
		game.addEntity(new Key(position));

		
		game.printGame();
		move(DOWN, 8);
		assert(hunter.getPosition().equals(hunterPos));
		move(RIGHT, 6);
		move(UP,3);
		assert(!hunter.getPosition().equals(hunterPos));
	}
}
