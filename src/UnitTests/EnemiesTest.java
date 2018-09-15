package UnitTests;
import entities.*;
import ui.*;
import java.util.*;

public class EnemiesTest {

	//private ArrayList<Enemy> eList;
	//private Game game;
	
	
	public static void main(String[] args) {
		Game game = new Game("testEnemy",320,320);
		Coordinate cur = new Coordinate(32,32);
		Hunter h = new Hunter(cur,"Hunter");
		game.addEntity(h);
		cur = new Coordinate(64,64);
		game.addEntity(new Hound(cur,h,"Hound"));
		cur = new Coordinate(128,128);
		game.createPlayer(cur);
		
		game.newTurn();
		game.newTurn();
		
		for (Entity object: game.testEntities()) {
			System.out.println(" " +object.returnX() + " " + object.returnY());
		}
		
		// TODO Auto-generated method stub

	}

}
