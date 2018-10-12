package UnitTests;


import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.Treasure;

public class TestTreasure extends testSetup {

	Coordinate treasurePos = new Coordinate(1, 4);
	@Test
	public void placeTreasure() {
		//Coordinate wallPos = new Coordinate(1, 5);
		Treasure treasure = new Treasure(treasurePos);
		game.addEntity(treasure);
		game.printGame();
		assert(game.getEntity(treasurePos) instanceof Treasure);
	}
	
	@Test
	public void pickUp() {
		Treasure treasure = new Treasure(treasurePos);
		game.addEntity(treasure);
		game.printGame();
		ArrayList<Entity> inventory = game.getPlayerInventory();
		assert(!inventory.contains(treasure));
		move(DOWN, 3);
		assert(inventory.contains(treasure));
	}

}
