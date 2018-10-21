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
import entities.Player;
import entities.Sword;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import entities.Strategist;
import entities.TreasureGoblin;
import entities.Bone;

public class MapSelectController extends Controller {

	@FXML 
	private Button btn1;
	@FXML 
	private Button btn2;
	
	protected boolean designer;
	public MapSelectController(Stage s) {
		super(s);
		designer = (boolean)s.getUserData();
		// TODO Auto-generated constructor stub
	}

	public void startMap1() {
		play_note();
    	Game game = new Game(10, 10);
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
		//if (!designer) {
		//}
        game.addEntity(new Pit(pitPos));
        game.addEntity(new HoverPotion(hoverPos));
        game.addEntity(new Sword(swordPos));
        game.addEntity(new Hunter(hunterPos)); //test replace with strategist
        //game.addEntity(new Strategist(hunterPos));
        //game.addEntity(new TreasureGoblin(hunterPos));
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
    	game.addEntity(new Player(playerPos));
        loadMapScreen(game, "Preset #1");

	}
	
	public void startCustomMap() {
		play_note();
		//int customHeight = Integer.parseInt(height.getText());
		//int customWidth = Integer.parseInt(width.getText());
    	//Game game = new Game(customHeight, customWidth);
		//Coordinate playerPos = new Coordinate(1,1); 
    	//game.addEntity(new Player(playerPos));
    	//loadMapScreen(game, "Custom map");
        //System.out.println("Custom map");
		Screen dimensionSelect = new Screen(super.getS(), "Blank", "view/DimensionScreen.fxml");
		DimensionController dimension = new DimensionController(super.getS());
		dimensionSelect.start(dimension);
	}
	
	public void previousMenu() {
		play_note();
		Stage curStage = super.getS();
		curStage.setUserData(null);
        Screen mainMenu = new Screen(curStage, "Main Menu", "view/mainmenu.fxml");
        MainMenuController mmc = new MainMenuController(curStage);
        mainMenu.start(mmc);
	}
	
	public void loadMapScreen(Game game, String title) {
        Screen map = new Screen(super.getS(), title, "view/map.fxml");
        MapController pc = new MapController(super.getS(), game);
        map.start(pc);
	}
	
	private void play_note() {
		AudioClip note = new AudioClip(this.getClass().getResource("start_game.wav").toString());
		//note.setCycleCount(3);
		note.play();
	}
}
