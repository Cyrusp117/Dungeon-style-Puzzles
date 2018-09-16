package UnitTests;

import java.util.ArrayList;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.InvincibilityPotion;

public class InvincibilityPotionTest extends testSetup {
	Coordinate ipPotPos = new Coordinate(1*32, 5*32);
	
	@Test
	public void placeInvincibilityPotionTest() {
		InvincibilityPotion ipPot = new InvincibilityPotion(ipPotPos);
		game.addEntity(ipPot);
		game.printGame();
		assert(game.getEntity(ipPotPos) instanceof InvincibilityPotion);
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
		InvincibilityPotion ipPot = new InvincibilityPotion(new Coordinate(1*32, 3*32));
		game.addEntity(ipPot);
		game.printGame();
		assert(!game.getPlayerInventory().contains(ipPot));
		move(DOWN, 3);
		assert(game.getPlayerInventory().contains(ipPot));
		game.newTurn();
		game.newTurn();
		game.newTurn();
		assert(!game.getPlayerInventory().contains(ipPot));
	}
}
