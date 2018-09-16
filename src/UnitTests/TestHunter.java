package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Boulder;
import entities.Coordinate;
import entities.Door;
import entities.Exit;
import entities.FloorSwitch;
import entities.HoverPotion;
import entities.Hunter;
import entities.InvincibilityPotion;
import entities.Key;
import entities.Pit;
import entities.Sword;
import entities.Wall;

public class TestHunter extends testSetup {
	

	@Test
	public void placeHunter() {
		Coordinate hunterPos = new Coordinate(3*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		game.printGame();
		assert(game.getEntity(hunterPos) instanceof Hunter);
	}

	@Test
	public void TestOneAway() {
		Coordinate hunterPos = new Coordinate(3*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		game.printGame();
		move(RIGHT, 1);
		assert(player.isAlive());
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	@Test
	public void TestVerticalChase() {
		Coordinate hunterPos = new Coordinate(2*32, 1*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);
		game.printGame();
		move(DOWN, 1);
		Coordinate expectedPos = new Coordinate(1*32, 1*32);
		assert(hunter.getPosition().equals(expectedPos));
		move(DOWN, 6);
		expectedPos = new Coordinate(1*32, 7*32);
		assert(hunter.getPosition().equals(expectedPos));
	}
	
	@Test
	public void TestObstacleRecognition() {
		Door.resetNumOfDoors();
		Key.resetNumOfKeys();
		Coordinate hunterPos = new Coordinate(8*32, 4*32);
		Hunter hunter = new Hunter(hunterPos);
		game.addEntity(hunter);

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
		move(RIGHT, 4);
		assert(!hunter.getPosition().equals(hunterPos));

	}
}


/*
in the testing each should probs test for straight movement, moving around an object(s),  
moving when path is blocked, 
and what they do when they are next to the player
*/