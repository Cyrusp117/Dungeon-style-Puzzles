package UnitTests;
import entities.*;
import ui.*;
import java.util.*;

public class EnemiesTest {

	
	
	public static void main(String[] args) {
		Game game = new Game("testEnemy",320,320);
		Coordinate cur = new Coordinate(32,32);
		Hunter h = new Hunter(cur);
		game.addEntity(h);
		cur = new Coordinate(64,160);
		Hound ho = new Hound(cur,h);
		game.addEntity(ho);
		cur = new Coordinate(32,128);
		
		game.createPlayer(cur);
		
		cur = new Coordinate(128,128);
		Coward coward = new Coward(cur);
		game.addEntity(coward);
		game.newTurn();
		game.newTurn();
		game.newTurn();
		//Coordinate co = ho.move(cur, game.generateGraph());
		//System.out.println(ho.getName() + " " + co.getxPosition() + " " + co.getyPosition());
		
		for (Entity object: game.testEntities()) {
			System.out.println(object.getName() + " " +object.returnX() + " " + object.returnY());
		}
		
		

	}

}
