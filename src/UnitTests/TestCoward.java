package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Boulder;
import entities.Coordinate;
import entities.Coward;
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

public class TestCoward extends testSetup {
	

	@Test
	public void placeCoward() {
		Coordinate cowardPos = new Coordinate(3, 1);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		game.printGame();
		assert(game.getFirstEntity(cowardPos) instanceof Coward);
	}

	@Test
	public void TestOneAway() {
		Coordinate cowardPos = new Coordinate(3, 1);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		game.printGame();
		move(RIGHT, 1);
		Coordinate correctPos = new Coordinate(4, 1);
		assert(player.isAlive());
		assertTrue(coward.getPosition().equals(correctPos));
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	@Test
	public void TestVerticalChase() {
		Coordinate cowardPos = new Coordinate(3, 1);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		game.printGame();
		move(DOWN, 1);
		Coordinate expectedPos = new Coordinate(2, 1);
		assert(coward.getPosition().equals(expectedPos));
		move(DOWN, 6);
		expectedPos = new Coordinate(1, 6);
		assert(coward.getPosition().equals(expectedPos));
	}
	
	@Test
	public void TestObstacleRecognition() {
		Door.resetNumOfDoors();
		Key.resetNumOfKeys();
		Coordinate cowardPos = new Coordinate(8, 4);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		game.printGame();

		Coordinate position = new Coordinate(4, 1);
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
		Door fakeDoorForFirstKey = new Door(position.moveRight());
		game.addEntity(fakeDoorForFirstKey);
		Door door = new Door(position.moveDown());
		game.addEntity(door);
		position = position.moveLeft().moveDown();
		game.addEntity(new Key(position));

		
		game.printGame();
		move(DOWN, 8);
		assert(coward.getPosition().equals(cowardPos));
		move(RIGHT, 4);
		assert(!coward.getPosition().equals(cowardPos));
	}
	
	@Test
	public void TestInvincibility() {
		Coordinate cowardPos = new Coordinate(5, 1);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		Coordinate ipPos = new Coordinate(3, 1);
		InvincibilityPotion ip = new InvincibilityPotion(ipPos);
		game.addEntity(ip);
		game.printGame();
		//robot.delay(1000);
		move(RIGHT, 1);
		// Hunter wouldve gone left towards player
		assert(coward.getPosition().equals(cowardPos.moveLeft())); 
		move(RIGHT, 1);
		// Hunter wouldve gone back to original pos bcause player has potion now
		assert(coward.getPosition().equals(cowardPos)); 

		robot.delay(1000);
		move(RIGHT, 2);
		Coordinate expectedPos = cowardPos.moveRight().moveRight();
		assert(coward.getPosition().equals(expectedPos));
	}
	@Test
	public void testDiag() {
		Coordinate cowardPos = new Coordinate(2, 2);
		Coward coward = new Coward(cowardPos);
		game.addEntity(coward);
		game.printGame();
		move(DOWN,1);
		Coordinate expectedPos = new Coordinate(3,2);
		assertTrue(coward.getPosition().equals((expectedPos)));
		
	}
}
