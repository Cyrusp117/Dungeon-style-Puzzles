package UnitTests;

import java.util.ArrayList;

import org.junit.Test;

import entities.Boulder;
import entities.Coordinate;
import entities.Entity;
import entities.Pit;
import entities.Wall;

public class TestBoulder extends testSetup {

	Coordinate boulderPos = new Coordinate(2*32, 4*32);
	@Test
	public void placeBoulder() {
		//Coordinate wallPos = new Coordinate(1*32, 5*32);
		Boulder boulder = new Boulder(boulderPos);
		game.addEntity(boulder);
		game.printGame();
		assert(game.getEntity(boulderPos) instanceof Boulder);
	}
	
	@Test
	public void moveBoulder() {
		Boulder boulder = new Boulder(boulderPos);
		game.addEntity(boulder);
		game.printGame();
		move(RIGHT, 1);
		move(DOWN, 3);
		Coordinate newPos = new Coordinate(2*32, 5*32);
		assert(boulder.getPosition().equals(newPos));
		System.out.println("Boulder has moved down");
		//robot.delay(1000);
		
		move(LEFT, 1);
		move(DOWN, 1);
		move(RIGHT, 1);
		newPos = new Coordinate(3*32, 5*32);
		assert(boulder.getPosition().equals(newPos));
		System.out.println("Boulder has moved right");
		//robot.delay(1000);
		
		move(DOWN, 1);
		move(RIGHT, 1);
		move(UP, 1);
		newPos = new Coordinate(3*32, 4*32);
		assert(boulder.getPosition().equals(newPos));
		System.out.println("Boulder has moved up");
		//robot.delay(1000);
		
		move(RIGHT, 1);
		move(UP, 1);
		move(LEFT, 1);
		newPos = new Coordinate(2*32, 4*32);
		assert(boulder.getPosition().equals(newPos));
		System.out.println("Boulder has moved left");
		//robot.delay(1000);
	}
	
	@Test
	public void testPitInteraction() {
		Boulder boulder = new Boulder(boulderPos);
		Coordinate pitPos = new Coordinate(2*32, 5*32);
		Pit pit = new Pit(pitPos);
		game.addEntity(boulder);
		game.addEntity(pit);
		game.printGame();
		ArrayList<Entity> entities = game.getEntities();
		assert(entities.contains(boulder));
		move(RIGHT, 1);
		move(DOWN, 3);
		assert(!entities.contains(boulder));
		assert(game.getEntityExcept(pitPos, pit) == null);
	}
	
	@Test
	public void testCantPushTwo() {
		boulderPos = new Coordinate(1*32, 4*32);
		Boulder boulder = new Boulder(boulderPos);
		game.addEntity(boulder);
		boulderPos = new Coordinate(1*32, 3*32);
		boulder = new Boulder(boulderPos);
		game.addEntity(boulder);
		move(DOWN, 1);
		move(DOWN, 1);
		Coordinate expectedPos = new Coordinate(1*32, 2*32);
		assert(expectedPos.equals(player.getPosition()));
	}
}
