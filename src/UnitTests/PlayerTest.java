package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Coordinate;
import entities.Player;

class PlayerTest {

	private Player player;
	private int xBoundary;
	private int yBoundary;
	@BeforeEach
	public void setUp() throws Exception {
		Coordinate position = new Coordinate(0,0);
		player = new Player(position);
		xBoundary = 320;
		yBoundary = 320;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	@Test
//	public void testMove() {
//		player.setDx(32);
//		player.move(x);
//		assertEquals(player.returnX(), 32);
//		assertEquals(player.returnY(), 0);
//	}

	@Test
	void testGetPosition() {
		
	}

	@Test
	void testIsOutOfBounds() {
		//fail("Not yet implemented");
	}

}
