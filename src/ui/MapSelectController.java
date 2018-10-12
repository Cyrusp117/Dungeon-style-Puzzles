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
	
	protected boolean designer;
	public MapSelectController(Stage s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public void startMap1() {
    	Game game = new Game("Preset #1", 10, 10);
        System.out.println("First Preset Dungeon");
		Coordinate playerPos = new Coordinate(1,1); 
        Coordinate pitPos = new Coordinate(2, 2);
        Coordinate hunterPos = new Coordinate(4, 1);
        Coordinate hoverPos = new Coordinate(2, 1);
        Coordinate swordPos = new Coordinate(5, 1);
        Coordinate fsPos = new Coordinate(1, 8);
        Coordinate boulderPos = new Coordinate(5, 5);
        Coordinate arrowPos = new Coordinate(4, 4);
        Coordinate key1Pos = new Coordinate(7, 1);
        Coordinate key2Pos = new Coordinate(8, 1);
        Coordinate Door1Pos = new Coordinate(2, 7);
        Coordinate Door2Pos = new Coordinate(3, 7);
        Coordinate Bomb1Pos = new Coordinate(7, 7);
        Coordinate Bomb2Pos = new Coordinate(8, 7);
        Coordinate IPpotPos = new Coordinate(5, 4);
		game.generatePerimeter();		 //Create a series of walls around the perimeter
		if (!designer) {
	    	game.createPlayer(playerPos);
		}
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
		Stage curStage = super.getS();
		curStage.setUserData(null);
        Screen mainMenu = new Screen(curStage, "Main Menu", "view/mainmenu.fxml");
        MainMenuController mmc = new MainMenuController(curStage);
        mainMenu.start(mmc);
	}
}
