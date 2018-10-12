package ui;

import entities.Arrow;
import entities.Bomb;
import entities.Boulder;
import entities.Coordinate;
import entities.Door;
import entities.FloorSwitch;
import entities.HoverPotion;
import entities.Hunter;
import entities.InvincibilityPotion;
import entities.Key;
import entities.Pit;
import entities.Sword;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MapSelectController extends Controller {

	@FXML 
	private Button btn1;
	@FXML 
	private Button btn2;
	@FXML 
	private Button btn3;
	
	public MapSelectController(Stage s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public void startMap1() {
    	Game game = new Game("Preset #1", 10*32, 10*32);
        System.out.println("First Preset Dungeon");
		Coordinate playerPos = new Coordinate(32,32); 
        Coordinate pitPos = new Coordinate(2*32, 2*32);
        Coordinate hunterPos = new Coordinate(4*32, 1*32);
        Coordinate hoverPos = new Coordinate(2*32, 1*32);
        Coordinate swordPos = new Coordinate(5*32, 1*32);
        Coordinate fsPos = new Coordinate(1*32, 8*32);
        Coordinate boulderPos = new Coordinate(5*32, 5*32);
        Coordinate arrowPos = new Coordinate(4*32, 4*32);
        Coordinate key1Pos = new Coordinate(7*32, 1*32);
        Coordinate key2Pos = new Coordinate(8*32, 1*32);
        Coordinate Door1Pos = new Coordinate(2*32, 7*32);
        Coordinate Door2Pos = new Coordinate(3*32, 7*32);
        Coordinate Bomb1Pos = new Coordinate(7*32, 7*32);
        Coordinate Bomb2Pos = new Coordinate(8*32, 7*32);
        Coordinate IPpotPos = new Coordinate(5*32, 4*32);
		game.generatePerimeter();						 //Create a series of walls around the perimeter
		game.createPlayer(playerPos);						 //Create the player at the given Coordinate
        game.addEntity(new Pit(pitPos));
        game.addEntity(new HoverPotion(hoverPos));
        game.addEntity(new Sword(swordPos));
        game.addEntity(new Hunter(hunterPos));
        game.addEntity(new FloorSwitch(fsPos));
        game.addEntity(new Boulder(boulderPos));
        game.addEntity(new Arrow(arrowPos));
        game.addEntity(new Key(key1Pos));
        game.addEntity(new Key(key2Pos));
        game.addEntity(new Door(Door1Pos));
        game.addEntity(new Door(Door2Pos));
        game.addEntity(new Bomb(Bomb1Pos));
        game.addEntity(new Bomb(Bomb2Pos));
        game.addEntity(new InvincibilityPotion(IPpotPos));
        game.printGame();
		
        Screen map1 = new Screen(super.getS(), "Map", "view/map.fxml");
        PlayerController pc = new PlayerController(super.getS(), game);
        map1.start(pc);
	}
	
	public void startCustomMap() {
		
	}
	
	public void previousMenu() {
        Screen mainMenu = new Screen(super.getS(), "Main Menu", "view/mainmenu.fxml");
        MainMenuController mmc = new MainMenuController(super.getS());
        mainMenu.start(mmc);
	}
}
