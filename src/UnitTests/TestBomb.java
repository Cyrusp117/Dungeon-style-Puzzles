package UnitTests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.junit.Test;

import entities.Bomb;
import entities.Boulder;
import entities.Coordinate;
import entities.Entity;
import entities.Hunter;

public class TestBomb extends testSetup {

	Coordinate bombPos = new Coordinate(2, 2);
	@Test
	public void placeBomb() {
		//Coordinate wallPos = new Coordinate(1, 5);
		Bomb bomb = new Bomb(bombPos);
		game.addEntity(bomb);
		game.printGame();
		assert(game.getEntity(bombPos) instanceof Bomb);
	}
	
	public void TestPickUp() {
		Bomb bomb = new Bomb(bombPos);
		game.addEntity(bomb);
		game.printGame();
		move(DOWN, 1);
		move(RIGHT, 1);
		assert(player.hasItem(bomb));
	}
	
	@Test
	public void TestBlowUp() {
		ArrayList<Entity> entities = game.getEntities();
		Bomb bomb = new Bomb(bombPos);
		game.addEntity(bomb);
		Hunter hunter1 = new Hunter(bombPos.moveDown());
		game.addEntity(hunter1);
		assert(entities.contains(hunter1));
		Hunter hunter2 = new Hunter(bombPos.moveUp());
		game.addEntity(hunter2);
		assert(entities.contains(hunter2));
		Boulder boulder1 = new Boulder(bombPos.moveRight());
		game.addEntity(boulder1);
		assert(entities.contains(boulder1));
		game.printGame();
		move(DOWN, 1);
		move(RIGHT, 1);
		
		
		
		// place Bomb
		move(KeyEvent.VK_V, 1);
		move(LEFT, 1);
		move(DOWN, 1);
		move(DOWN, 1);
		move(DOWN, 1);
		move(DOWN, 1);
		assert(!entities.contains(boulder1));
		assert(entities.contains(hunter2));
		assert(!entities.contains(hunter1));
	}
	
	@Test
	public void TestPlayerDeath() {			// side effect : ensures player cant pick up bomb when lit
		Bomb bomb = new Bomb(bombPos);
		game.addEntity(bomb);
		move(DOWN, 1);
		move(RIGHT, 1);
		move(KeyEvent.VK_V, 1);
		move(RIGHT, 1);
		move(RIGHT, 1);
		move(LEFT, 1);
	}

}
