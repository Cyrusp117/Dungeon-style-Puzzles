package UnitTests;

import org.junit.Test;

import entities.Arrow;
import entities.Coordinate;
import entities.Key;

public class KeyTest extends testSetup {

	@Test
	public void placeKey() {
		Key.resetNumOfKeys();
		Coordinate keyPos = new Coordinate(1*32, 7*32);
		Key key = new Key(keyPos);
		game.addEntity(key);
		game.printGame();
		System.out.println(key.getKeyId());
		assert(key.getKeyId() == 0);
		assert(key.getNumOfKeys() == 1);
		assert(game.getEntity(keyPos) instanceof Key);
	}
	
	@Test
	public void pickUpKey() {
		Key.resetNumOfKeys();
		assert(!player.hasItem("Key"));
		Coordinate key1Pos = new Coordinate(1*32, 7*32);
		Key key1 = new Key(key1Pos);
		game.addEntity(key1);
		assert(!player.hasItem("Key"));
		assert(key1.getKeyId() == 0);
		assert(key1.getNumOfKeys() == 1);
		Coordinate key2Pos = new Coordinate(1*32, 7*32);
		Key key2 = new Key(key2Pos);
		game.addEntity(key2);
		assert(key2.getKeyId() == 1);
		assert(key2.getNumOfKeys() == 2);
		move(DOWN, 7);
		move(RIGHT, 7);
		assert(player.hasItem("Key"));
	}
}
