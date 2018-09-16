package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.Hunter;
import entities.Sword;


public class TestSword extends testSetup {

	Coordinate swordPos = new Coordinate(1*32, 2*32);
	@Test
	public void placeSword() {
		Sword sword = new Sword(swordPos);
		game.addEntity(sword);
		game.printGame();
		assert(game.getEntity(swordPos) instanceof Sword);
	}
	
	@Test
	public void pickUpSword() {
		Sword sword = new Sword(swordPos);
		game.addEntity(sword);
		game.printGame();
		ArrayList<Entity> inventory = game.getPlayerInventory();
		assert(!inventory.contains(sword));
		move(DOWN, 2);
		assert(inventory.contains(sword));
	}

	@Test
	public void interactWithEnemy() {
		ArrayList<Entity> entities = game.getEntities();
		Sword sword = new Sword(swordPos);
		game.addEntity(sword);
		Coordinate enemyPos = new Coordinate(1*32, 3*32);
		Hunter hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		// Testing if killing one hunter is successful nad reduces durability
		assert(entities.contains(hunter));
		assert(sword.getDurability() == 5);
		move(DOWN, 2);
		assert(!entities.contains(hunter));
		assert(sword.getDurability() == 4);
		// Successful
		
		// Testing if sowrd is broken when durability is 0
		enemyPos = new Coordinate(1*32, 4*32);
		hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		enemyPos = new Coordinate(1*32, 5*32);
		hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		enemyPos = new Coordinate(1*32, 6*32);
		hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		enemyPos = new Coordinate(1*32, 7*32);
		hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		enemyPos = new Coordinate(1*32, 8*32);
		hunter = new Hunter(enemyPos);
		game.addEntity(hunter);
		move(DOWN, 4);
		assert(sword.getDurability() == 0);
		assert(!player.hasItem(sword));
		assert(player.isAlive());
		// Successful
		
		// Testing if Player dies with no sword
		move(DOWN, 1);
		assert(!player.isAlive());
		

	}
}
