package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;

public class TestStrategist extends testSetup {
	

	@Test
	public void placeStrat() {
		Coordinate stratPos = new Coordinate(3, 1);
		Strategist strat = new Strategist(stratPos);
		game.addEntity(strat);
		game.printGame();
		assert(game.getEntity(stratPos) instanceof Strategist);
	}

	@Test
	public void TestOneAway() {
		Coordinate stratPos = new Coordinate(3, 1);
		Strategist strat = new Strategist(stratPos);
		game.addEntity(strat);
		game.printGame();
		move(RIGHT, 1);
		assert(player.isAlive());
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	@Test
	public void TestVerticalChase() {
		Coordinate stratPos = new Coordinate(2, 1);
		Strategist strat = new Strategist(stratPos);
		game.addEntity(strat);
		game.printGame();
		move(DOWN, 1);
		Coordinate expectedPos = new Coordinate(1, 1);
		assert(strat.getPosition().equals(expectedPos));
		move(DOWN, 6);
		expectedPos = new Coordinate(1, 7);
		assert(strat.getPosition().equals(expectedPos));
	}
	
	@Test
	public void TestObstacleRecognition() {
		Door.resetNumOfDoors();
		Key.resetNumOfKeys();
		Coordinate stratPos = new Coordinate(8, 4);
		Strategist strat = new Strategist(stratPos);
		game.addEntity(strat);

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
		assert(strat.getPosition().equals(stratPos));
		move(RIGHT, 4);
		assert(!strat.getPosition().equals(stratPos));
	}
	
	@Test
	public void TestInvincibility() {
		Coordinate stratPos = new Coordinate(5, 1);
		Strategist strat = new Strategist(stratPos);
		game.addEntity(strat);
		Coordinate ipPos = new Coordinate(3, 1);
		InvincibilityPotion ip = new InvincibilityPotion(ipPos);
		game.addEntity(ip);
		game.printGame();
		//robot.delay(1000);
		move(RIGHT, 1);
		// Hunter wouldve gone left towards player
		assert(strat.getPosition().equals(stratPos.moveLeft())); 
		move(RIGHT, 1);
		// Hunter wouldve gone back to original pos bcause player has potion now
		assert(strat.getPosition().equals(stratPos)); 

		robot.delay(1000);
		move(RIGHT, 2);
		Coordinate expectedPos = stratPos.moveRight().moveRight();
		assert(strat.getPosition().equals(expectedPos));
	}
}


/*
in the testing each should probs test for straight movement, moving around an object(s),  
moving when path is blocked, 
and what they do when they are next to the player */
