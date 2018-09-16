package UnitTests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

public class ArrowTest extends testSetup {

	


	@Test
	public void pickUpArrow() {
		move(DOWN, 4);
		move(RIGHT, 4);
	}
//	public void setUp() throws Exception {
//		// TODO Auto-generated method stub
//		super.setUp();
//	}
	
//	@Test
//	public void RobotTestMove() {
//		robot.waitForIdle();
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.delay(500);
//		System.out.println("Button pressed");
//		System.out.println(player.returnX()  + " " + player.returnY());
//		assertEquals(player.returnX(), 32);
//		assertEquals(player.returnY(), 64);
//		System.out.println(player.returnX()  + " " + player.returnY());
//	}

}
