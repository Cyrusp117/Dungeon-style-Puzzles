package UnitTests;


import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.Treasure;

public class TestTreasure extends testSetup {

	Coordinate treasurePos = new Coordinate(1*32, 4*32);
	@Test
	public void placeTreasure() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
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
		move(DOWN, 3);
		ArrayList<Entity> inventory = game.getPlayerInventory();
		assert(inventory.contains(treasure));
	}

}
