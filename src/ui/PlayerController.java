package ui;

import entities.Coordinate;
import entities.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PlayerController extends Controller {
	
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
	public PlayerController(Stage s, Game game) {
		super(s);
		this.game = game;
		this.player = game.getPlayer();
	}
	
	public void initialize() {
		label1.setText(game.toString());
	}
	
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
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
