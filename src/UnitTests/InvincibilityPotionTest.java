package UnitTests;

import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.InvincibilityPotion;

public class InvincibilityPotionTest extends testSetup {
	Coordinate invincibilityPotionPos = new Coordinate(1*32, 5*32);
	
	@Test
	public void placeInvincibilityPotionTest() {
		InvincibilityPotion invincibilityPotion = new InvincibilityPotion(invincibilityPotionPos);
		game.addEntity(invincibilityPotion);
		game.printGame();
		assert(game.getEntity(invincibilityPotionPos) instanceof InvincibilityPotion);
	}
	
	@Test
	public void pickUp() {
		InvincibilityPotion invincibilityPotion = new InvincibilityPotion(invincibilityPotionPos);
		game.addEntity(invincibilityPotion);
		game.printGame();
		assert(!game.getPlayerInventory().contains(invincibilityPotion));
		move(DOWN, 5);
		assert(game.getPlayerInventory().contains(invincibilityPotion));
	}
	
	@Test
	public void checkNumOfTurns() {
		InvincibilityPotion invincibilityPotion = new InvincibilityPotion(new Coordinate(1*32, 3*32));
		game.addEntity(invincibilityPotion);
		game.printGame();
		assert(!game.getPlayerInventory().contains(invincibilityPotion));
		move(DOWN, 3);
		assert(game.getPlayerInventory().contains(invincibilityPotion));
		game.newTurn();
		game.newTurn();
		game.newTurn();
		assert(!game.getPlayerInventory().contains(invincibilityPotion));
	}
}
