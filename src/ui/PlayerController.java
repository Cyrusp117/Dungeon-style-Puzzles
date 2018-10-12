package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PlayerController extends Controller {
	
	@FXML
	private TextArea map;
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
		map.setText(game.toString());
		// TODO Auto-generated constructor stub
	}

	
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
	
	public void moveUp () {
		
	}
	public void moveRight () {
		
	}
	public void moveLeft () {
		
	}
	public void moveDown () {
		
	}
}
