package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entities.HoverPotion;
import entities.Coordinate;
import entities.Entity;
import entities.Treasure;

public class TestHoverPotion extends testSetup {

	Coordinate hpPos = new Coordinate(1*32, 4*32);
	@Test
	public void placeTreasure() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		HoverPotion hp = new HoverPotion(hpPos);
		game.addEntity(hp);
		game.printGame();
		assert(game.getEntity(hpPos) instanceof HoverPotion);
	}
	
	@Test
	public void pickUp() {
		HoverPotion hoverPotion = new HoverPotion(hpPos);
		game.addEntity(hoverPotion);
		game.printGame();
		ArrayList<Entity> inventory = game.getPlayerInventory();
		move(DOWN, 3);
		assert(inventory.contains(hoverPotion));
	}

}
