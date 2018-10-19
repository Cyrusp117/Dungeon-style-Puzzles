package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DimensionController extends Controller {
	
	@FXML
	private TextField height;
	
	@FXML
	private TextField width;
	
	@FXML
	private Button confirm;
	
	public DimensionController(Stage s) {
		super(s);
	}	
	
	public void onConfirm() {
		int gameWidth = 10;
		int gameHeight = 10;
		
		try {
			gameWidth = Integer.parseInt(width.getText());
		}catch(NumberFormatException e) {
			width.clear();
			width.setPromptText("Invalid Input: Input must be an integer");
			try {
				gameHeight = Integer.parseInt(height.getText());
			}catch(NumberFormatException f) {
				height.clear();
				height.setPromptText("Invalid Input: Input must be an integer");
				return;
			}
			return;
		}
		
		
		if( (gameWidth <= 22 && gameWidth > 0) && (gameHeight <= 15 && gameHeight > 0)) {
			Game game = new Game("Blank", gameWidth , gameHeight);
			Screen blankMap1 = new Screen(super.getS(), "Blank", "view/editorScreen.fxml");
			EditorController editor = new EditorController(super.getS(), game);
			blankMap1.start(editor);
		}else {
			if(gameWidth > 22 || gameWidth <= 0) {
				width.clear();
				width.setPromptText("Invalid Input: 0 < Width <= 22");
			}
			if(gameHeight > 22 || gameHeight <= 0) {
				height.clear();
				height.setPromptText("Invalid Input: 0 < Height <= 15");
			}
		} 
	}
	
	
}
