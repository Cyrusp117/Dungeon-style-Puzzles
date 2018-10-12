package ui;

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
	public PlayerController(Stage s, Game game) {
		super(s);
		this.game = game;
		// TODO Auto-generated constructor stub
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
		label1.setText(game.toString());;
	}
	public void moveRight () {
		label1.setText(game.toString());
	}
	public void moveLeft () {
        dx = -32;
        dy = 0;
        System.out.println("LEFT");
        game.newTurn();
		label1.setText(game.toString());
	}
	public void moveDown () {
		label1.setText(game.toString());
	}
}
