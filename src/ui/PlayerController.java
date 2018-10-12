package ui;

import entities.Coordinate;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sun.print.resources.serviceui;

public class PlayerController extends Controller {
	
	@FXML
	private AnchorPane screen;
	@FXML
	private Label label1;
	@FXML 
	private Button btn1;
	@FXML 
	private Button btn2;
	@FXML 
	private Button btn3;
	@FXML 
	private Button btn4;
	@FXML
	private Button btn5;
	
	Game game;
	Player player;
	Scene scene;
	public PlayerController(Stage s, Game game) {
		super(s);
		this.game = game;
		this.player = game.getPlayer();
		this.scene = s.getScene();
	}
	
	public void initialize() {
		label1.setText(game.toString());
		
//    	EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				KeyCode key = event.getCode();
//				if (key.equals(KeyCode.W)) {
//					moveUp();
//				} else if (key.equals(KeyCode.S)) {
//					moveDown();
//				} else if (key.equals(KeyCode.A)) {
//					moveLeft();
//				} else if (key.equals(KeyCode.D)) {
//					moveRight();
//				}
//			}
//		};
//    	scene.setOnKeyPressed(ke);
	}
	
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
	
	public void keyPressed(KeyEvent ke) {
		System.out.println("HOWDY");
		KeyCode key = ke.getCode();
		if (key.equals(KeyCode.W)) {
			moveUp();
		} else if (key.equals(KeyCode.S)) {
			moveDown();
		} else if (key.equals(KeyCode.A)) {
			moveLeft();
		} else if (key.equals(KeyCode.D)) {
			moveRight();
		} else {
			moveDown();
		}
	}
	
	public void moveUp () {
		player.setDx(0);
		player.setDy(-1);
		game.update();
		label1.setText(game.toString());
	}
	public void moveRight () {
		player.setDx(1);
		player.setDy(0);
		game.update();
		label1.setText(game.toString());
	}
	
	public void moveLeft () {
		player.setDx(-1);
		player.setDy(0);
		game.update();
		label1.setText(game.toString());
	}
	public void moveDown () {
		player.setDx(0);
		player.setDy(1);
		game.update();
		label1.setText(game.toString());
	}
}
