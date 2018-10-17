package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class MainMenuController extends Controller {

	@FXML 
	private Button playerMode;
	@FXML
	private Button designMode;
	
	public MainMenuController(Stage s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public void startGame() {
		Stage curStage = super.getS();
		curStage.setUserData(false);
        Screen mapSelect = new Screen(curStage, "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
	
	public void designGame() {
		Stage curStage = super.getS();
		curStage.setUserData(true);
        Screen mapSelect = new Screen(curStage, "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}

}
