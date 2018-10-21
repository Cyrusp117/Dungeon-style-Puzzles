package UnitTests;

import org.junit.Test;

import entities.Boulder;
import entities.Coordinate;
import entities.Door;
import entities.Exit;
import entities.FloorSwitch;
import entities.HoverPotion;
import entities.TreasureGoblin;
import entities.InvincibilityPotion;
import entities.Key;
import entities.Pit;
import entities.Sword;
import ui.Game;
import entities.Treasure;
import entities.Wall;

public class GoblinTest extends testSetup {

	@Test
	public void placeGoblin() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate hunterPos = new Coordinate(3, 1);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		game.addEntity(goblin);
		game.printGame();
		assert(game.getFirstEntity(hunterPos) instanceof TreasureGoblin);
	}

	@Test
	public void TestOneAway() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate playerCoord = new Coordinate(1,1);
		game.createPlayer(playerCoord);
		
		Coordinate hunterPos = new Coordinate(9, 1);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		Coordinate tresPos = new Coordinate(8,8);
		Treasure tres = new Treasure(tresPos);
		game.addEntity(goblin);
		game.addEntity(tres);
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	@Test
	public void TestGoblinGetsTreasure() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate playerCoord = new Coordinate(1,1);
		game.createPlayer(playerCoord);
		
		Coordinate hunterPos = new Coordinate(9, 8);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		Coordinate tresPos = new Coordinate(8,8);
		Treasure tres = new Treasure(tresPos);
		game.addEntity(goblin);
		game.addEntity(tres);
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		
		System.out.println(goblin.getX() + " " + goblin.getY());
		//assert(goblin.getPosition().equals(tresPos));
		assert(goblin.getTreasure() != null);
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	@Test
	public void TestPlayerKillsGoblinWithTreasure() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate playerCoord = new Coordinate(1,9);
		game.createPlayer(playerCoord);
		
		Coordinate hunterPos = new Coordinate(9, 9);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		Coordinate tresPos = new Coordinate(8,9);
		Treasure tres = new Treasure(tresPos);
		
		Coordinate swordPos = new Coordinate(7,9);
		Coordinate wall1Pos = new Coordinate(9,8);
		Coordinate wall2Pos = new Coordinate(8,8);
		Coordinate wall3Pos = new Coordinate(7,8);
		
		game.addEntity(new Sword(swordPos));
		game.addEntity(new Wall(wall1Pos));
		game.addEntity(new Wall(wall2Pos));
		game.addEntity(new Wall(wall3Pos));
		
		game.addEntity(goblin);
		game.addEntity(tres);
		game.printGame();
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		assert(game.getPlayer().hasItem(tres));
		//assert(goblin.getTreasure() != null);
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	@Test
	public void TestTwoGoblinWithNoTreasure() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate playerCoord = new Coordinate(1,1);
		game.createPlayer(playerCoord);
		
		Coordinate hunterPos = new Coordinate(9, 1);
		Coordinate secondGoblinPos = new Coordinate(1,9);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		TreasureGoblin goblin2 = new TreasureGoblin(secondGoblinPos);
		Coordinate tresPos = new Coordinate(8,9);
		Treasure tres = new Treasure(tresPos);
		
		
		
		game.addEntity(goblin);
		game.addEntity(goblin2);
		game.addEntity(tres);
		game.printGame();
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		
		//assert(goblin.getTreasure() != null);
		// Because This enemy constantly moves toward the player, 
		// stopping if it cannot move any closer
	}
	
	
	//@Test
	public void TestObstacleRecognition() {
		Door.resetNumOfDoors();
		Key.resetNumOfKeys();
		Coordinate hunterPos = new Coordinate(8, 4);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		game.addEntity(goblin);

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
		move(s, 8);
		assert(goblin.getPosition().equals(hunterPos));
		move(d, 4);
		assert(!goblin.getPosition().equals(hunterPos));
	}
	
	//@Test
	public void TestInvincibility() {
		Coordinate hunterPos = new Coordinate(4, 1);
		TreasureGoblin goblin = new TreasureGoblin(hunterPos);
		game.addEntity(goblin);
		Coordinate ipPos = new Coordinate(3, 1);
		InvincibilityPotion ip = new InvincibilityPotion(ipPos);
		game.addEntity(ip);
		game.printGame();
		//robot.delay(1000);
		move(d, 1);
		// Hunter wouldve gone left towards player
		assert(goblin.getPosition().equals(hunterPos.moveLeft())); 
		move(d, 1);
		// Hunter wouldve gone back to original pos bcause player has potion now
		assert(goblin.getPosition().equals(hunterPos)); 

		robot.delay(1000);
		move(d, 2);
		Coordinate expectedPos = hunterPos.moveRight().moveRight();
		assert(goblin.getPosition().equals(expectedPos));
	}

}
