package UnitTests;

import org.junit.Before;
import org.junit.Test;

import entities.Arrow;
import entities.Coordinate;
import entities.Wall;

public class TestWall extends testSetup {

	Coordinate wallPos = new Coordinate(1*32, 4*32);
	@Test
	public void placeWall() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		Wall wall = new Wall(wallPos);
		game.addEntity(wall);
		game.printGame();
		assert(game.getEntity(wallPos) instanceof Wall);
	}
	
	@Test
	public void testWallCollision() {
		Wall wall = new Wall(wallPos);
		game.addEntity(wall);
		game.printGame();
		assert(game.getEntity(wallPos) instanceof Wall);
		move(DOWN, 3);
		Coordinate beforeWall = new Coordinate(1*32, 3*32);
		assert(player.getPosition().equals(beforeWall));
	}
}
