package UnitTests;

import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.InvincibilityPotion;

public class InvincibilityPotionTest extends testSetup {
	Coordinate ipPotPos = new Coordinate(1, 5);
	
	@Test
	public void placeInvincibilityPotionTest() {
		InvincibilityPotion ipPot = new InvincibilityPotion(ipPotPos);
		game.addEntity(ipPot);
		game.printGame();
		assert(game.getFirstEntity(ipPotPos) instanceof InvincibilityPotion);
	}
	
	@Test
	public void pickUp() {
		InvincibilityPotion ipPot = new InvincibilityPotion(ipPotPos);
		game.addEntity(ipPot);
		game.printGame();
		assert(!game.getPlayerInventory().contains(ipPot));
		move(DOWN, 5);
		assert(game.getPlayerInventory().contains(ipPot));
	}
	
	@Test
	public void checkNumOfTurns() {
		InvincibilityPotion ipPot = new InvincibilityPotion(new Coordinate(1, 3));
		game.addEntity(ipPot);
		game.printGame();
		assert(!game.getPlayerInventory().contains(ipPot));
		move(DOWN, 3);
		assert(game.getPlayerInventory().contains(ipPot));
		game.update();
		game.update();
		game.update();
		assert(!game.getPlayerInventory().contains(ipPot));
	}
}
